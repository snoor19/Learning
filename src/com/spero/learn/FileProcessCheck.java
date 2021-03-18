package com.spero.learn;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.onmobile.prism.util.helper.FileHelper;

public class FileProcessCheck {
	
	public static void main(String[] args) {
		try {
			String files[] = FileHelper.getFileNames("D:\\Jira\\Requirements\\TEFCVAS_Apple_Music_integration_SM-12121\\Testing\\internal", "*");
			 ArrayList<String> filesArrayList = new ArrayList<>();
			    System.out.println("File Count ::"+files.length);
			    for (int i = 0; i < files.length; i++) {
			    	System.out.println("FileName ::"+files[i]);
			    	File fileToProcess = new File("D:\\Jira\\Requirements\\TEFCVAS_Apple_Music_integration_SM-12121\\Testing\\internal"+ File.separator +files[i]);
			    	if(fileToProcess.length() > 0)
			    		filesArrayList.add(files[i]);
			    }
			    
			    int totalFiles = filesArrayList.size();
			    
				for (int i = 0; i < totalFiles; i++) {
					String fileName = filesArrayList.get(i);
					System.out.println("Data::"+fileName);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static long countLine(File fileName) {

	    long lines = 0;

	    try (InputStream is = new BufferedInputStream(new FileInputStream(fileName))) {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean endsWithoutNewLine = false;
	        while ((readChars = is.read(c)) != -1) {
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n')
	                    ++count;
	            }
	            endsWithoutNewLine = (c[readChars - 1] != '\n');
	        }
	        if (endsWithoutNewLine) {
	            ++count;
	        }
	        lines = count;
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return lines;
	}

}
