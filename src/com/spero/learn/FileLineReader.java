package com.spero.learn;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;

public class FileLineReader {

	public static void main(String[] args) {
		 LineNumberReader lr = null;
		 String fileName = "C:\\Users\\noor.shaik\\Downloads\\test.txt";
		 File file = new File(fileName);
         try {
             lr = new LineNumberReader(new FileReader(file));
             lr.skip(file.length());
             System.out.println("Line count::"+lr.getLineNumber());
				/*
				 * PrintWriter writer = new PrintWriter(file); writer.print(""); writer.close();
				 * lr.skip(file.length());
				 * System.out.println("Line count::"+lr.getLineNumber());
				 */
             int lineCount = 11;
             System.out.println("Result::"+((lr.getLineNumber()) < lineCount));
             StringBuilder sb = new StringBuilder("Helo \n Noor");
             System.out.println("SB before ::"+sb.toString());
             sb.setLength(0);
             System.out.println("SB after::"+sb.toString());
         } catch (Exception e) {
        	 System.out.println("Exception::"+e);
		}
	}

}
