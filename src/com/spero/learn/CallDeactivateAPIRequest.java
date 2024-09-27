package com.spero.learn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallDeactivateAPIRequest {

	public static void main(String[] args) {

		long number = 1001310007;
		for (int i = 0; i < 5000; i++) {
			try {
				
				URL url = new URL("http://localhost:8089/subscription/DeactivateSubscription?user=mmp&pass=mmp&srvkey=RBT_ACT_DEFAULT&mode=VOICE&type=P&operatorId=29&msisdn="+number);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setConnectTimeout(60000);
				con.setReadTimeout(60000);
				int status = con.getResponseCode();
				System.out.println("Response Stat::"+status);
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				System.out.println("Response::"+content.toString());
				in.close();
			} catch (IOException e) {
				System.out.println("Exception while calling rest API::");
			}
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (i % 2 == 0) {
				number++;
			}
		}
		
	
	}

}
