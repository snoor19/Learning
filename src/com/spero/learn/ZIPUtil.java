package com.spero.learn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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



    public static void compress(int archiveType, File[] files, String dest){

    }

    public static void main(String[] args){
        try {
            System.out.println("gan !!!!");
            zip(new File[]{new File("D:\\ziptest\\test.txt")},"D:\\ziptest\\prism.zip");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
