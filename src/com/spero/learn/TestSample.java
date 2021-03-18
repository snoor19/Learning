package com.spero.learn;

public class TestSample {

	public static void main(String[] args) {
		
		byte[] timeZone = {-127,00};
		String str = new String(timeZone);
		System.out.println("Str Data ::"+hexStringToByteArray("8100"));
	}
	
	public static byte[] hexStringToByteArray(String s) {
	    byte[] b = new byte[s.length() / 2];
	    for (int i = 0; i < b.length; i++) {
	      int index = i * 2;
	      int v = Integer.parseInt(s.substring(index, index + 2), 16);
	      b[i] = (byte) v;
	    }
	    return b;
	  }
}
