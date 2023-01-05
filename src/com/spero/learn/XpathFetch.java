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
		String str = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
				"   <SOAP-ENV:Body>\r\n" + 
				"      <ns0:MiscPaymentResponse xmlns:ns0=\"http://www.etisalat.ae/Middleware/GenericPaymentService/MiscPaymentResponse.xsd\">\r\n" + 
				"         <ns0:ResponseData>\r\n" + 
				"            <ns0:TransactionID>555666</ns0:TransactionID>\r\n" + 
				"         </ns0:ResponseData>\r\n" + 
				"         <ns1:AckMessage xmlns:ns1=\"http://www.etisalat.ae/Middleware/SharedResources/Common/AckMessage.xsd\">\r\n" + 
				"            <ns1:Status>SUCCESS</ns1:Status>\r\n" + 
				"         </ns1:AckMessage>\r\n" + 
				"      </ns0:MiscPaymentResponse>\r\n" + 
				"   </SOAP-ENV:Body>\r\n" + 
				"</SOAP-ENV:Envelope>";
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
					"/*[local-name()='Envelope']/*[local-name()='Body']/*[local-name()='MiscPaymentResponse']/*[local-name()='AckMessage']/*[local-name()='Status']");
			String resp = expr.evaluate(doc);
			System.out.println("Response:"+resp);
			}catch (Exception e) {
				System.out.println("Exception::"+e);
				e.printStackTrace();
			}
}
	
}
