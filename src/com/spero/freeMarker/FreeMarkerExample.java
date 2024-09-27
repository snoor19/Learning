package com.spero.freeMarker;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author noor.shaik
 *
 */
public class FreeMarkerExample {

	public static void main(String[] args) {
		try {
			// Initialize FreeMarker configuration
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);

			File templateFolder = new File("E:\\UMP\\auth\\src\\main\\resources");
			cfg.setDirectoryForTemplateLoading(templateFolder);
			// cfg.setClassForTemplateLoading(FreeMarkerExample.class, "/templates");
			// Some additional configuration options
			cfg.setDefaultEncoding("UTF-8");
//			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			cfg.setLogTemplateExceptions(false);

			// Load the FreeMarker template
			Template template = cfg.getTemplate("template.xml.access-token.content.ftl");

			// Create a data model (can be any Java object, here we use a simple Map)
			Map<String, Object> data = new HashMap<>();
			data.put("transRecord", 'A'); // Set your string value here
			data.put("macroName", "test");
			data.put("user", "noor");
			data.put("pass", "grant");
			data.put("password", "zan");

			// Process the template
			StringWriter output = new StringWriter();
			template.process(data, output);

			// Print the output
			System.out.println(output.toString());

		} catch (Exception e) {
			System.out.println("Excpetion::" + e.getMessage());
			e.printStackTrace();
		}
	}
}

