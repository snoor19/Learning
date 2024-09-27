package com.spero.learn;

public class SubStringTest {
	
	private static final String SVC_ID = "SVC_ID";
	private static final String COUNTRY_CODE = "27";
	private static final String SUB_ID = "7795349007";
	private static final String REF_ID = "afnaklsdfds";
	private static final String CHANNEL = "channel";
	private static final String DOI_CHANNEL = "doi_channel";
	
	public static void main(String[] args) {
		String data = "BEGIN$DATE$\nSERVICENAME:NewService\nPRICE:30\nTYPE:test\nCCT:10\nSENDER:test\nURL:xzy:8080\nip:1234567\nport:8080\nsubject:heyyy\nT:weertyuui";
		
		System.out.println("Data::"+data);
		
		String body = "{\"msisdn\":"+COUNTRY_CODE +SUB_ID+ ",\"svc_id\":"+SVC_ID+",\"ext_ref\":"
				+'"'+REF_ID+'"'+",\"channel\":\""+ CHANNEL+"\",\"doi_channel\":\""+DOI_CHANNEL+"\"}";
		System.out.println("Body from Signature Builder Class==={}"+ body);
		
		String[] str = new String[0];
		
		System.out.println("Size::"+str.length);
	}

}
