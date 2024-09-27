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
		String str = "<response>\r\n" + 
				"<act>\r\n" + 
				"<status>SUCCESS</status>\r\n" + 
				"<reason>SUCCESS</reason>\r\n" + 
				"<params type=\"NORMAL\" subscriber_type=\"P\" next_bill_date=\"2024-08-22 12:23:11.234\" validity_days=\"1\" grace_count=\"\" balance=\"30.0\" amount=\"30.0\" new_srv_key=\"\"/>\r\n" + 
				"</act>\r\n" + 
				"</response>";
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
					"//reason");
			String resp = expr.evaluate(doc);
			System.out.println("Response:"+resp);
			}catch (Exception e) {
				System.out.println("Exception::"+e);
				e.printStackTrace();
			}
}
	
}
