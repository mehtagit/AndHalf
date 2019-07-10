package com.gl.ceir.evaluator.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.config.PolicyEvaluatorConfig;
import com.gl.ceir.evaluator.services.InputRepository;

@Service
public class FileInputReader implements InputRepository {

	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private PolicyEvaluatorConfig appConfig;

	private File file;

	public FileInputReader() {
		System.out.println("I am registered FileInputReader");
	}

	@Override
	public List<Request> read() {
		List<Request> requests = null;
		try {
			File folder = new File(appConfig.getInputRepositoryDirectory());
			File[] listOfFiles = folder.listFiles();
			if (listOfFiles.length > 0) {
				this.file = listOfFiles[0];
				logger.info("Start Reading File :" + file.getName());
				if (file.isFile()) {
					logger.info("File " + file.getName());
					requests = getRequests();

				} else if (file.isDirectory()) {
					logger.info("Directory " + file.getName());
				}
				logger.info("Completed Reading File :" + file.getName() + ", Size:"
						+ (requests == null ? 0 : requests.size()));
			}

		} catch (Exception e) {
			logger.error("Exception while reading files" + e.getMessage(), e);
			e.printStackTrace();
		}

		return requests;
	}

	public void moveFile() {
		String destinationPath = appConfig.getCompletedDirectory() + "/" + file.getName();
		if (file.renameTo(new File(destinationPath))) {
			logger.info("File " + file.getName() + ", Moved to " + destinationPath);
		} else {
			file.renameTo(new File(destinationPath + "_error"));
		}

	}

	private List<Request> getRequests() {
		List<Request> requests = new ArrayList<>();
		try {
			Stream<String> lines = Files.lines(Paths.get(file.getAbsolutePath()));
			lines.forEach(line -> {
				Request request = stringToRequest(line, file.getName());
				if (request.getMsisdn() != null)
					requests.add(request);
			});
			lines.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
		return requests;
	}

	private Request stringToRequest(String record, String filename) {
		Request request = new Request();
		String[] data = record.split(",");
		try {
			request.setImei(Long.parseLong(data[0]));
		} catch (Exception e) {
			request.setImei(0L);
		}
		try {
			request.setMsisdn(Long.parseLong(data[2]));
		} catch (Exception e) {
			request.setMsisdn(null);
			logger.warn(
					"Excluded Number FileName(" + filename + ") , Record(" + record + ") Exception:" + e.getMessage());
		}
		try {
			request.setImsi(Long.parseLong(data[1]));
		} catch (Exception e) {
			request.setImsi(0L);
		}
		request.setFilename(data[3]);
		return request;
	}

	@Override
	public List<Request> read(int count) {
		// TODO Auto-generated method stub
		return null;
	}

}