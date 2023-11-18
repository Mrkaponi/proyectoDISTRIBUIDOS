package com.example.distribuidos.services;

public interface SystemInfoProvider {

    /**
     * Obtiene el modelo del procesador.
     *
     * @return Modelo del procesador.
     */
    String getProcessorModel();

    /**
     * Obtiene la velocidad del procesador.
     *
     * @return Velocidad del procesador en GHz.
     */
    double getProcessorSpeed();

    /**
     * Obtiene el número de núcleos del procesador.
     *
     * @return Número de núcleos del procesador.
     */
    int getNumberOfCores();

    /**
     * Obtiene la capacidad total del disco duro en bytes.
     *
     * @return Capacidad total del disco duro en bytes.
     */
    long getTotalDiskSpace();

    /**
     * Obtiene la capacidad disponible del disco duro en bytes.
     *
     * @return Capacidad disponible del disco duro en bytes.
     */
    long getFreeDiskSpace();

    /**
     * Obtiene la versión del sistema operativo.
     *
     * @return Versión del sistema operativo.
     */
    String getOperatingSystemVersion();

    /**
     * Obtiene el porcentaje de uso del procesador.
     *
     * @return Porcentaje de uso del procesador.
     */
    double getProcessorUsage();

    /**
     * Obtiene la memoria libre del sistema.
     *
     * @return Memoria libre del sistema en bytes.
     */
    long getFreeMemory();

    /**
     * Obtiene el porcentaje de ancho de banda libre.
     *
     * @return Porcentaje de ancho de banda libre.
     */
    double getNetworkUsage();
}
