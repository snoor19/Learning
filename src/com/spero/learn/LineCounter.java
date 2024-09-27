package com.spero.learn;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LineCounter {

	public static void main(String[] args) {
//		String rootFolderPath = "E:\\UMP\\auth\\src\\main\\java\\com\\onmobile\\auth";
//		String rootFolderPath = "E:\\Prism\\GitLab\\MonitoringAgent\\src\\main\\java\\com\\onmobile\\healthcheck";
//		String rootFolderPath = "E:\\KidsApp\\KidsBackEnd";
		String rootFolderPath = "E:\\KidsApp\\Repository\\admin-frontend-app\\node_modules";
		int totalCount = countLinesInFolder(new File(rootFolderPath));
		System.out.println("Total line count:"+totalCount);
	}

	private static int countLinesInFolder(File folder) {
		int totalCount = 0;
		if(folder.isDirectory()) {
			File[] files = folder.listFiles();
			for(File file : files) {
				if(file.isDirectory()) {
					totalCount += countLinesInFolder(file);
				} else {
					try {
                        totalCount += countLines(file);
                    } catch (Exception e) {
                        System.err.println("Error reading file: " + file.getAbsolutePath() + ", Skipping...");
                    }
				}
			}
		} else {
			System.err.println("Invalid directory path.");
		}
		return totalCount;
	}

	private static int countLines(File file) throws IOException {
		Path path = Paths.get(file.getAbsolutePath());
		int count = 0;
		try(Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
			count = (int) stream.count();
		} 
		return count;
	}
	
}
