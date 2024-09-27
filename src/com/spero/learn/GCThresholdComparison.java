package com.spero.learn;

public class GCThresholdComparison {
    public static void main(String[] args) {
        long initialMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Initial Memory Usage (bytes): " + initialMemory);
        System.out.println("Runtime.getRuntime().totalMemory()::"+Runtime.getRuntime().totalMemory());
        for (int i = 0; i < 10000; i++) {
            // Create a large array to consume memory
            int[] largeArray = new int[10000];

            if (i % 100 == 0) {
                System.gc(); // Suggest to run garbage collection
                long currentMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                System.out.println("Memory Usage after " + i + " iterations (bytes): " + currentMemory);
            }
        }

        long finalMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Final Memory Usage (bytes): " + finalMemory);

        long gcThreshold = finalMemory - initialMemory;
        System.out.println("Garbage Collection Threshold (bytes): " + gcThreshold);
    }
}

