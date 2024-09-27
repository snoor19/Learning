package com.spero.learn;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class FileRename {

	public static void main(String[] args) {
		File file = new File("E:\\Jira\\Requirements\\AIS-13\\RENEWAL\\CDR_WRITTEN\\RENEWAL\\3154108_20230302190000.txt");
		String filename = file.getAbsolutePath();
		filename = filename.replace("20230302190000", "noor_date");
		filename = filename.replace("." + "txt", "." + "done");
		
		System.out.println("filename::"+filename);
		try {
			FileUtils.moveFile(file, new File(filename));
		} catch (Exception e) {
			System.out.println("Unable to rename file from = {} to ={}"+ file.getName()+", ::"+ filename);
			System.out.println("Exception while rename the File:: {}"+e);
		}
	
	}

}
