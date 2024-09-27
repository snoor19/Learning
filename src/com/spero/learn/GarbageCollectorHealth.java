package com.spero.learn;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class GarbageCollectorHealth {
    public static void main(String[] args) {
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();

        for (GarbageCollectorMXBean gcMXBean : garbageCollectorMXBeans) {
            System.out.println("Garbage Collector Name: " + gcMXBean.getName());
            System.out.println("Collection Count: " + gcMXBean.getCollectionCount());
            System.out.println("Collection Time (ms): " + gcMXBean.getCollectionTime());
            System.out.println("Memory Pools: " + String.join(", ", gcMXBean.getMemoryPoolNames()));
            System.out.println();
        }
    }
}

