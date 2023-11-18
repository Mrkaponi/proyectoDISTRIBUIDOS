package com.example.distribuidos.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;


@Service
public class SystemInfoProviderImp implements SystemInfoProvider{
    private final MemoryMXBean memoryMXBean;

    public SystemInfoProviderImp() {
        this.memoryMXBean = ManagementFactory.getMemoryMXBean();
    }
    @Override
    public String getProcessorModel() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        return operatingSystemMXBean.getArch();
    }

    @Override
    public double getProcessorSpeed() {

        return 0;
    }

    @Override
    public int getNumberOfCores() {
        return Runtime.getRuntime().availableProcessors();
    }

    @Override
    public long getTotalDiskSpace() {
        File[] roots = File.listRoots();
        long totalSpace = 0;

        for (File root : roots) {
            totalSpace += root.getTotalSpace();
        }

        return totalSpace;
    }

    @Override
    public long getFreeDiskSpace() {
        File[] roots = File.listRoots();
        long freeSpace = 0;

        for (File root : roots) {
            freeSpace += root.getFreeSpace();
        }

        return freeSpace;
    }

    @Override
    public String getOperatingSystemVersion() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        return operatingSystemMXBean.getVersion();
    }

    @Override
    public double getProcessorUsage() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        return operatingSystemMXBean.getSystemLoadAverage(); // Este método devuelve un valor entre 0 y 1, donde 1 significa carga completa.


    }

    @Override
    public long getFreeMemory() {
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

        long freeHeapMemory = heapMemoryUsage.getMax() - heapMemoryUsage.getUsed();
        long freeNonHeapMemory = nonHeapMemoryUsage.getMax() - nonHeapMemoryUsage.getUsed();

        // Sumar la memoria libre de la heap y la no heap
        return freeHeapMemory + freeNonHeapMemory;

    }

    @Override
    public double getNetworkUsage() {
        return 0;
    }
}
