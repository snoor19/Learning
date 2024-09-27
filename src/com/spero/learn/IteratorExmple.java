package com.spero.learn;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IteratorExmple {
	public static List<String> REDIS_CACHE_KEYS = new CopyOnWriteArrayList<>();
	public static void main(String[] args) {
		REDIS_CACHE_KEYS = Stream.of("String1", "String2", "String3", "String4").collect(Collectors.toList());
		int cacheSize = REDIS_CACHE_KEYS.size();
		for (int i = 0; i < REDIS_CACHE_KEYS.size();) {
			System.out.print("Index-"+i);
			System.out.println(", Value::"+REDIS_CACHE_KEYS.get(i));
			REDIS_CACHE_KEYS.remove(i);
		}
	}

}
