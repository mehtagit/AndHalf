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

	/*
	 * @Override public List<Request> read() { List<Request> list = new
	 * ArrayList<>();
	 * list.add(stringToRequest("8821921361,213231312312335,FILE_1"));
	 * list.add(stringToRequest("8821921362,213231312312335,FILE_1"));
	 * list.add(stringToRequest("8821921367,213231312312335,FILE_1"));
	 * list.add(stringToRequest("8821921368,213231312312335,FILE_1"));
	 * list.add(stringToRequest("8821921369,213231312312335,FILE_1")); return list;
	 * }
	 */

	private List<Request> getRequests() {
		List<Request> requests = new ArrayList<>();
		try {
			Stream<String> lines = Files.lines(Paths.get(file.getAbsolutePath()));
			lines.forEach(line -> {
				Request request = stringToRequest(line, file.getName());
				if (request.getImei() != null && request.getMsisdn() != null)
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
			request.setMsisdn(Long.parseLong(data[1]));
			request.setImei(Long.parseLong(data[0]));
			request.setFilename(filename);
		} catch (Exception e) {
			logger.warn(
					"Excluded Number FileName(" + filename + ") , Record(" + record + ") Exception:" + e.getMessage());
		}
		return request;
	}

	@Override
	public List<Request> read(int count) {
		// TODO Auto-generated method stub
		return null;
	}

	// private static void fileStreamUsingFiles(String fileName) {
	// try {
	// Stream<String> lines = Files.lines(Paths.get(fileName));
	// System.out.println("<!-----Read all lines as a Stream-----!>");
	// lines.forEach(System.out::println);
	// lines.close();
	// } catch (IOException io) {
	// io.printStackTrace();
	// }
	// }
	//
	// // Method #2
	// private static void filterFileData(String fileName) {
	// try {
	// Stream<String> lines = Files.lines(Paths.get(fileName)).filter(line ->
	// line.startsWith("s"));
	// System.out.println("<!-----Filtering the file data using Java8
	// filtering-----!>");
	// lines.forEach(System.out::println);
	// lines.close();
	// } catch (IOException io) {
	// io.printStackTrace();
	// }
	// }
	//
	// // Method #3
	// private static void fileStreamUsingBufferedReader(String fileName) {
	// try {
	// BufferedReader br = Files.newBufferedReader(Paths.get(fileName));
	// Stream<String> lines = br.lines().map(str -> str.toUpperCase());
	// System.out.println("<!-----Read all lines by using BufferedReader-----!>");
	// lines.forEach(System.out::println);
	// lines.close();
	// } catch (IOException io) {
	// io.printStackTrace();
	// }
	// }
}