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

import com.gl.ceir.config.controller.BlackListController;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.config.AppConfig;
import com.gl.ceir.evaluator.services.InputRepository;

public class FileInputReader implements InputRepository {

	private static final Logger logger = LogManager.getLogger(BlackListController.class);
	private AppConfig appConfig;

	@Override
	public List<Request> read() {
		List<Request> requests = null;
		try {
			File folder = new File(appConfig.getInputRepositoryDirectory());
			File[] listOfFiles = folder.listFiles();

			for (File file : listOfFiles) {
				if (file.isFile()) {
					logger.info("File " + file.getName());
					requests = getRequests(file.getName());

					String destinationPath = appConfig.getCompletedDirectory() + file.getName();
					file.renameTo(new File(destinationPath));
					logger.info("File " + file.getName() + ", Moved to " + destinationPath);
					break;
				} else if (file.isDirectory()) {
					logger.info("Directory " + file.getName());
				}
			}
		} catch (Exception e) {
			logger.error("Exception while reading files" + e.getMessage(), e);
		}

		return requests;
	}

	private List<Request> getRequests(String fileName) {
		List<Request> requests = new ArrayList<>();
		try {
			Stream<String> lines = Files.lines(Paths.get(fileName));
			lines.forEach(line -> requests.add(stringToRequest(line)));
			lines.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
		return requests;
	}

	private Request stringToRequest(String record) {
		Request request = new Request();
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