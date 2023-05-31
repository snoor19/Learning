package com.spero.learn;

import java.util.HashMap;
import java.util.Map;

public class StreamMapTest {

	public static void main(String[] args) {
		Map<String, Double> map = new HashMap<>();
		map.put("10-ABCD-01", 15.75);
		map.put("10-XYZ-05", 12.13);
		map.put("10-ABCD;-06", 11.06);
		map.put("10-ABCD-04", 10.76);
		map.put("10-XYZ-03", 07.62);
		String fileId = "ABCD;";
		System.out.println("Contains::"+map.containsKey(null));
		System.out.println(map.get(null));
		String key = map.entrySet().stream().filter(e -> e.getKey().contains(fileId)).findFirst()
				.map(Map.Entry::getKey).get();
		System.out.println("Key/value::" + key);
		if (key != null && map.containsKey(key))
			System.out.println("Data::" + map.get(key));
		else
			System.out.println("No Data::" + key);
		
		System.out.println("Null String ::"+ (String)null);
	}

}
