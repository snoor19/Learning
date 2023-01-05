package com.spero.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubList {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <=300; i++) {
			list.add(i);
		}
		List<Integer> subList1 = list.subList(0, 99);
		List<Integer> subList2 = list.subList(100, 199);
		List<Integer> subList3 = list.subList(200, 299);
		System.out.println("subList1::"+subList1);
		System.out.println("subList2::"+subList2);
		System.out.println("subList3::"+subList3);
		ArrayList<String> srvKeys = new ArrayList<String>();
		srvKeys.addAll(Arrays.asList("*".split(",")));
		System.out.println("List::"+srvKeys);
		String fileName = "BILLING_1";
		String logType = "BILLING";
		System.out.println("fileName.split(\"_\").length::"+fileName.split("_").length);
		System.out.println("(logType.split(\"_\").length + 1)::"+(logType.split("_").length + 1));
		if (fileName.startsWith(logType)
				&& (fileName.split("_").length == (logType.split("_").length + 1)) && (!fileName.equalsIgnoreCase("BILLING_REALTIME") && !fileName.equalsIgnoreCase("BILLING")))
			System.out.println("True");
		else
			System.out.println("False");
	}

}
