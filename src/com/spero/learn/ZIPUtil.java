package com.spero.learn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.onmobile.prism.util.PrismBufferedReader;

public class ZIPUtil {
	private static void zip(File[] files, String dest) throws IOException{
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(dest)));
        for(File file : files){
            zip(file, zos);
        }
        zos.close();
    }

    private static void zip(File file, ZipOutputStream zos) throws IOException{
        byte[] buf = new byte[2048];
        @SuppressWarnings("unused")
        int bytes = 0;
        if(file.isDirectory()){
            ZipEntry entry = new ZipEntry(file.getName());
            zos.putNextEntry(entry);
            for(File subFile : file.listFiles()){
                zip(subFile, zos);
            }
            zos.closeEntry();
        }
        FileInputStream fis = new FileInputStream(file);
        System.out.println(file.getName());
        ZipEntry entry = new ZipEntry(file.getName());
        zos.putNextEntry(entry);
        while((bytes = fis.read(buf)) != -1){
            zos.write(buf);
        }
        zos.closeEntry();
        fis.close();
    }
    
    private static void readGzip(File file) {
    	String data = null;
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(file))))) {
			while (((data = br.readLine()) != null) && !data.isEmpty()) {
				System.out.println("Data::"+data);
			}
    	} catch (Exception e) {
    		System.out.println("Exception wile reading file::"+e);
    	}
    }



    public static void compress(int archiveType, File[] files, String dest){

    }

    public static void main(String[] args){
        try {
            System.out.println("gan !!!!");
//            zip(new File[]{new File("D:\\ziptest\\test.txt")},"D:\\ziptest\\prism.zip");
//            readGzip(new File("E:\\Jira\\Requirements\\AIS-13\\FTP_Test\\4492101_2023022717_SDGApp6.process"));
            String fileName = "3154108_20230301200000_1.tmp";
            fileName = fileName.replace("3154108_", ""); 
            fileName = fileName.replace(".tmp","") ;
            System.out.println("Data::"+fileName.substring(0, 15));
            if(fileName.contains("_")) {
            	
            	System.out.println("index::"+fileName.indexOf('_'));
            	System.out.println("Index for::"+fileName.substring(fileName.indexOf('_')));
            	fileName = fileName.substring(0, (fileName.indexOf('_')));
            }
            System.out.println("fileName:::"+fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
