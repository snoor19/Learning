package com.spero.learn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileTestDir {
	
	public static void main(String[] args) {
		String directory = "E:\\Prism\\Deamon\\bin\\LOGS\\TLOG\\BILLING";
		List<Path> pathList = new ArrayList<>();

		try {
			directory = directory.substring(0, directory.lastIndexOf("BILLING"));
			System.out.println("Directory::"+directory);
			try (Stream<Path> stream = Files.walk(Paths.get(directory))) {
			  pathList = stream.map(Path::normalize)
			        .filter(Files::isDirectory)
			        .collect(Collectors.toList());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		pathList.forEach(i->{
//			System.out.println("Index::"+i.toString().lastIndexOf("_"));
			String fileName = i.getFileName().toString();
			try {
				System.out.println("FileName::"+fileName);
				System.out.println(fileName.split("_").length+"="+("BILLING".split("_").length+1));
				if(fileName.startsWith("BILLING") && (fileName.split("_").length == ("BILLING".split("_").length+1))) {
					String tmpPath = i.toString().substring(0, i.toString().lastIndexOf("_"));
					System.out.println("TmpPath::"+tmpPath);
					System.out.println(i.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}
