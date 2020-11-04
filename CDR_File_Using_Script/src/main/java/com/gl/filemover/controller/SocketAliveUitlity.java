package com.gl.filemover.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.filemover.configuration.CP2PropertiesReader;
import com.gl.filemover.configuration.PropertiesReader;
import com.gl.filemover.dao.CDRFileRecordsInterfaceImpl;
import com.gl.filemover.entity.CDRFileRecords;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Component
public class SocketAliveUitlity {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	PropertiesReader propertiesReader;
	@Autowired
	CP2PropertiesReader cp2PropertiesReader;
	@Autowired
	CDRFileRecordsInterfaceImpl cDRFileRecordsServiceImpl;
	@Autowired
	CDRFileRecordsInterfaceImpl dao;

	public void cp2(String operator_param, String source_param) {
// configuration parameters 
		String copyLocation = cp2PropertiesReader.copyLocation;
		String sourceDirectory = propertiesReader.targetPath.trim() + "/" + operator_param + "/" + source_param + "/";
		String targetDirectory = cp2PropertiesReader.targetPath.trim() + "/" + operator_param + "/" + source_param
				+ "/";
		final String REMOTE_TARGET_PATH = cp2PropertiesReader.remoteTargetPath.trim() + "/" + operator_param + "/"
				+ source_param + "/";
		int timeout = cp2PropertiesReader.timeout;
		final String REMOTE_HOST = cp2PropertiesReader.hostName;
		final String USERNAME = cp2PropertiesReader.userName;
		final String PASSWORD = cp2PropertiesReader.password;
		final int REMOTE_PORT = cp2PropertiesReader.serverPort;
		final int SESSION_TIMEOUT = cp2PropertiesReader.sessionTimeout;
		final int CHANNEL_TIMEOUT = cp2PropertiesReader.channelTimeout;
		log.info("cp2PropertiesReader : " + cp2PropertiesReader);

		// checking isServerUtilityAlive
		log.info("inside CP2 process method");
		//CdrRecdServer


// in case local value set in properties file				
			if ("local".equalsIgnoreCase(copyLocation)) {
				
				Optional<List<CDRFileRecords>> optional=Optional.empty();
				if("SIG1".equalsIgnoreCase(propertiesReader.cdrRecdServer)) {
					optional=cDRFileRecordsServiceImpl
					.findByOperatorAndStatusSIG1AndCdrRecdServer(operator_param, "INIT",propertiesReader.cdrRecdServer);
				}
				else if("SIG2".equalsIgnoreCase(propertiesReader.cdrRecdServer)) {
					optional=cDRFileRecordsServiceImpl
					.findByOperatorAndStatusSIG2AndCdrRecdServer(operator_param, "INIT",propertiesReader.cdrRecdServer);	
				}
				
				
				log.info(operator_param + " Copying Number of files : " + optional.get().size());
				if (optional.get().size() > 0) {
				for (CDRFileRecords result : optional.get()) {
					String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
					String destinationPath = targetDirectory + result.getFileName();
					log.info("copy file to destinationPath:" + destinationPath);
					Path temp;
					try {
			
						temp = Files.copy(Paths.get(sourceDirectory + result.getFileName()), Paths.get(destinationPath),
								StandardCopyOption.REPLACE_EXISTING);
						log.info("File copied successfully to destination path " + destinationPath);
						// updating file status
						if ("SIG1".equalsIgnoreCase(propertiesReader.cdrRecdServer)) {
							log.info("LOCAL : rec. server : "+propertiesReader.cdrRecdServer+" updating status to DONE");
							dao.modifyFileStatus("DONE", result.getStatusSIG2(), timeStamp,
									result.getSig2Utime(), result.getId());
						} else if ("SIG2".equalsIgnoreCase(propertiesReader.cdrRecdServer)) {
							log.info("LOCAL : rec. server : "+propertiesReader.cdrRecdServer+" updating status to DONE");
							dao.modifyFileStatus(result.getStatusSIG1(), "DONE", result.getSig1Utime(),
									timeStamp, result.getId());
						}

					} catch (IOException e) {
						log.info("Failed to copy the file");
						e.printStackTrace();
					} catch (Exception e) {
						log.error("oops updation failed due to reason : " + e.toString());
					}
				}
			}
			}

// in case remote value set in properties file				
			else if ("remote".equalsIgnoreCase(copyLocation)) {
				Optional<List<CDRFileRecords>> optional=Optional.empty();
				if("SIG1".equalsIgnoreCase(propertiesReader.cdrRecdServer)) {
				 optional = cDRFileRecordsServiceImpl
						.findByOperatorAndStatusSIG2AndCdrRecdServer(operator_param, "INIT",propertiesReader.cdrRecdServer);
				}
				else if("SIG2".equalsIgnoreCase(propertiesReader.cdrRecdServer)) {
					 optional = cDRFileRecordsServiceImpl
								.findByOperatorAndStatusSIG1AndCdrRecdServer(operator_param, "INIT",propertiesReader.cdrRecdServer);	
				}
				log.info(operator_param + " Copying Number of files : " + optional.get().size());
				if (optional.get().size() > 0) {
				boolean isServerUtilityAlive = isSocketAlive(REMOTE_HOST, REMOTE_PORT, timeout);
				if (isServerUtilityAlive == true) {
					Session jschSession = null;

					try {
						JSch jsch = new JSch();
						jschSession = jsch.getSession(USERNAME, REMOTE_HOST, REMOTE_PORT);
						// authenticate using password
						jschSession.setPassword(PASSWORD);
						jschSession.setConfig("StrictHostKeyChecking", "no");
						// 10 seconds session timeout
						jschSession.connect(SESSION_TIMEOUT);
						Channel sftp = jschSession.openChannel("sftp");
						// 5 seconds timeout
						sftp.connect(CHANNEL_TIMEOUT);
						ChannelSftp channelSftp = null;
						for (CDRFileRecords result : optional.get()) {
							String timeStamp_during_remote = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
							channelSftp = (ChannelSftp) sftp;
							// transfer file from local to remote server
							channelSftp.put(sourceDirectory + result.getFileName(),
									REMOTE_TARGET_PATH + result.getFileName());
							log.info("file " + result.getFileName() + " copied to server " + REMOTE_HOST);
							// download file from remote server to local
							// channelSftp.get(remoteFile, localFile);
// updating file status							

							if ("SIG1".equalsIgnoreCase(propertiesReader.cdrRecdServer)) {

								dao.modifyFileStatus(result.getStatusSIG1(), "DONE", result.getSig1Utime(),
										timeStamp_during_remote, result.getId());
								log.info("REMOTE : rec. server : "+propertiesReader.cdrRecdServer+" updating status to DONE");
							} else if ("SIG2".equalsIgnoreCase(propertiesReader.cdrRecdServer)) {
								dao.modifyFileStatus("DONE", result.getStatusSIG2(),timeStamp_during_remote,
										result.getSig2Utime(), result.getId());
								log.info("REMOTE : rec. server : "+propertiesReader.cdrRecdServer+" updating status to DONE");
							}
							

						}
						channelSftp.exit();

					} catch (JSchException | SftpException e) {
						e.printStackTrace();
						log.info("unable to eastablish connection between remote machine. " + e.toString());
					} finally {
						if (jschSession != null) {
							jschSession.disconnect();
							log.info("connection closed with hostname " + REMOTE_HOST);
						}
					}

				}
			}
			}
		
	}

	public boolean isSocketAlive(String hostName, int port, int timeout) {
		boolean isAlive = false;
		SocketAddress socketAddress = new InetSocketAddress(hostName, port);
		Socket socket = new Socket();
		try {
			socket.connect(socketAddress, timeout);
			socket.close();
			isAlive = true;
			log.info("hostname " + hostName + " is listening on port" + port);
		} catch (SocketTimeoutException exception) {
			log.info("SocketTimeoutException " + hostName + ":" + port + ". " + exception.getMessage());
		} catch (IOException exception) {
			log.info("IOException - Unable to connect to " + hostName + ":" + port + ". " + exception.getMessage());
		}
		return isAlive;
	}

}
