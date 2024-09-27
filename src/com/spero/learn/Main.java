package com.spero.learn;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class Main {

	public static void main(String[] args) {
		try {
			String programName = (args.length == 0) ? "Java" : args[0];

			// Initialize the object
			SystemStatus systemStatus = new SystemStatus(programName);

			// Register the object in the MBeanServer
			MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
			ObjectName objectName = new ObjectName("com.sysdig.app:name=SystemStatusExample");
			platformMBeanServer.registerMBean(systemStatus, objectName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
