package com.spero.learn;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class XpathFetch {

	public static void main(String[] args) {
		String str = "<?xml version = \"1.0\" encoding = \"UTF-8\"?>\r\n" + 
				"<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope\">\r\n" + 
				"<S:Body>\r\n" + 
				"<uidmsisdnResponse xmlns=\"http://bouyguestelecom.fr/Seryices/uidmsisdn.xsd\">\r\n" + 
				"<result>200</result>\r\n" + 
				"<resultMessage>SUCCESS</resultMessage>\r\n" + 
				"<requestId>1c0653cd-e897-41af-bd30.55f3a195ff33</requestId>\r\n" + 
				"<subscriptionInfo>\r\n" + 
				"<ci>01234012345</ci>\r\n" + 
				"<subscription>\r\n" + 
				"<uid>W1KRVQI0WHK</uid>\r\n" + 
				"<cpeid>848e796f6134605b5e1013b08981</cpeid>\r\n" + 
				"<transactionId>BBAA99887766554433221100</transactionId>\r\n" + 
				"<status>Confirmed</status>\r\n" + 
				"<currency>EUR</currency>\r\n" + 
				"<price>1.5</price>\r\n" + 
				"<tax>0.2</tax>\r\n" + 
				"<total>1.7</total>\r\n" + 
				"<charge_date>20200727</charge_date>\r\n" + 
				"<refundCode />\r\n" + 
				"<status_update_date>20200721</status_update_date>\r\n" + 
				"</subscription>\r\n" + 
				"</subscriptionInfo>\r\n" + 
				"<remainingAttempts>49</remainingAttempts>\r\n" + 
				"</uidmsisdnResponse>\r\n" + 
				"</S:Body>\r\n" + 
				"</S:Envelope>";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		try {
			builder = factory.newDocumentBuilder();
			ByteArrayInputStream bis = new ByteArrayInputStream(str.getBytes());
			doc = builder.parse(bis);

			// Create XPathFactory object
			XPathFactory xpathFactory = XPathFactory.newInstance();

			// Create XPath object
			XPath xpath = xpathFactory.newXPath();

			XPathExpression expr = xpath.compile(
					"/*[local-name()='Envelope']/*[local-name()='Body']/*[local-name()='uidmsisdnResponse']/*[local-name()='subscriptionInfo']/*[local-name()='subscription']/*[local-name()='uid']");
			String resp = expr.evaluate(doc);
			System.out.println("Response:"+resp);
			}catch (Exception e) {
				System.out.println("Exception::"+e);
				e.printStackTrace();
			}
}
	
}
