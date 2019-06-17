package com.gl.ceir.evaluator.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.config.PolicyEvaluatorConfig;
import com.gl.ceir.evaluator.services.InputRepository;

@Service
public class FileInputReader implements InputRepository {

	private Logger logger = LogManager.getLogger(this.getClass());
	private PolicyEvaluatorConfig appConfig;

	public FileInputReader() {
		System.out.println("I am registered FileInputReader");
	}
	/*
	 * @Override public List<Request> read() { List<Request> requests = null; try {
	 * File folder = new File(appConfig.getInputRepositoryDirectory()); File[]
	 * listOfFiles = folder.listFiles();
	 * 
	 * for (File file : listOfFiles) { if (file.isFile()) { logger.info("File " +
	 * file.getName()); requests = getRequests(file.getName());
	 * 
	 * String destinationPath = appConfig.getCompletedDirectory() + file.getName();
	 * file.renameTo(new File(destinationPath)); logger.info("File " +
	 * file.getName() + ", Moved to " + destinationPath); break; } else if
	 * (file.isDirectory()) { logger.info("Directory " + file.getName()); } } }
	 * catch (Exception e) { logger.error("Exception while reading files" +
	 * e.getMessage(), e); }
	 * 
	 * return requests; }
	 */

	@Override
	public List<Request> read() {
		List<Request> list = new ArrayList<>();
		list.add(stringToRequest("882192121,132313123123,FILE_1"));
		list.add(stringToRequest("882192122,132313123124,FILE_1"));
		list.add(stringToRequest("882192123,132313123125,FILE_1"));
		list.add(stringToRequest("882192124,132313123126,FILE_1"));
		return list;
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
		String[] data = record.split(",");
		request.setMsisdn(Long.parseLong(data[0]));
		request.setImei(Long.parseLong(data[1]));
		request.setFilename(data[2]);
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