package com.spero.learn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallErrorAPIResponse {

	public static void main(String[] args) {
		long number = 1001320009;
		for (int i = 0; i < 1000; i++) {
			try {
				
				URL url = new URL("http://localhost:8089//subscription/ActivateSubscription?srvkey=RBT_ACT_DEFAULT_OLD&info=abc%3Ahi%7Cvariant%3Adev%7Csongname%3ATERE+BINA%7Ccampaignid%3Asource%3DTest_Partner%2Cmedium%3Dtest+partner+company%2Ccampaign%3DDIALOGSL_CA_testing%2Ccampaignsessionid%3D123%2Cstoresessionid%3Dc6931647910740558c2f9952287c9c29%2Cnetwork-type%3DDATA%2Cmode%3DSM%2Cstatickey1%3Dstaticvalue%7CCONTENT_ID%3Acontentid%3Dcontent12345%2Ccatid%3Dct12345%2Ccampaign%3Donmobile%2Ccampaignsessionid%3Donmobile%7CcmpId%3Aonmobile%7CAPP_SUBSC_ID%3A136401%7CCIRCLE_NAME%3AALL%7CAPP_NAME%3ADIALOGSL%7Cvariant%3AtqcMinor%7Ccatname%3Acat12345%7Cplatform%3Aplatform12345%7CaccountCode%3Aacc12345%7CcustomerType%3Acus12345%7Csegment%3Aseg12345&refid=54f6b038614e1-498b-8d95-af4ef9955_base_act&mode=SMS&user=mmp&pass=mmp&type=P&amount=&siteid=&language=&operatorId=29&msisdn="+number);
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
				Thread.sleep(45000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			number++;
		}
		
	}

}
