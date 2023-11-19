package com.example.distribuidos.services;

import org.springframework.stereotype.Service;

import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.SpeedTestSocket;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.util.concurrent.CompletableFuture;


@Service
public class SystemInfoProviderImp implements SystemInfoProvider{
    private final MemoryMXBean memoryMXBean;

    public SystemInfoProviderImp()
    {
        this.memoryMXBean = ManagementFactory.getMemoryMXBean();
    }
    @Override
    public String getProcessorModel() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        return operatingSystemMXBean.getArch();
    }

    @Override
    public double getProcessorSpeed() 
    {
    	SystemInfo systemInfo = new SystemInfo();
    	CentralProcessor procesador = systemInfo.getHardware().getProcessor();
    	double velProcesador = procesador.getProcessorIdentifier().getVendorFreq() / 1e6; // MHz
        return velProcesador;
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
    public CompletableFuture<Double> getNetworkUsage() 
    {
        SpeedTestSocket speedTestSocket = new SpeedTestSocket();
        CompletableFuture<Double> futuro = new CompletableFuture<>();
		 speedTestSocket.addSpeedTestListener(new ISpeedTestListener()	
		 
					{
					@Override
				    public void onCompletion(SpeedTestReport report) {
				        // called when download/upload is complete
						 double anchoBanda = report.getTransferRateBit().doubleValue() / 1000000;
						 futuro.complete(anchoBanda);
				        System.out.println("[COMPLETED] rate in bit/s   : " + report.getTransferRateBit());
				    }
				    @Override
				    public void onError(SpeedTestError speedTestError, String errorMessage) {
				    	System.out.println("Hubo un error en la prueba de la velocidad de descarga"+ errorMessage);
				    	System.out.println(speedTestError);
				    	 
				    }

				    @Override
				    public void onProgress(float percent, SpeedTestReport report) {
				    	
				    }
					});	
			speedTestSocket.startDownload("https://autogestion.metrotel.com.ar/speedtest/archivo10MB.zip",1000);
        return futuro;
    }
}

