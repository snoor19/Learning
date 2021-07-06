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
		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\r\n" + 
				"<GatewayResponse>\r\n" + 
				"    <ResponseHeader status=\"pass\" result_code=\"00\" response_timestamp=\"02/07/2021.00:00:16\" additional_info=\"SUCCESS\">\r\n" + 
				"    </ResponseHeader>\r\n" + 
				"</GatewayResponse>";
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
					"/GatewayResponse/ResponseHeader/@additional_info");
			String resp = expr.evaluate(doc);
			System.out.println("Response:"+resp);
			}catch (Exception e) {
				System.out.println("Exception::"+e);
				e.printStackTrace();
			}
}
	
}
