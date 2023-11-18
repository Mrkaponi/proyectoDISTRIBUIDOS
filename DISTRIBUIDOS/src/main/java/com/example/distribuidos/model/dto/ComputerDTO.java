package com.example.distribuidos.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComputerDTO{
    private Long id;
    private boolean status;
    private String processorModel;
    private String processorSpeed;
    private int numberOfCores;
    private long diskCapacity;
    private String operatingSystemVersion;

    // Constructors can be added as needed

    public ComputerDTO() {
    }

    // Optional: Convert method from Entity to DTO


    // Optional: Convert method from DTO to Entity
    public com.example.distribuidos.model.Computer toEntity() {
        com.example.distribuidos.model.Computer computerEntity = new com.example.distribuidos.model.Computer();
        computerEntity.setId(this.id);
        computerEntity.setStatus(this.status);
        computerEntity.setProcessorModel(this.processorModel);
        computerEntity.setProcessorSpeed(this.processorSpeed);
        computerEntity.setNumberOfCores(this.numberOfCores);
        computerEntity.setDiskCapacity(this.diskCapacity);
        computerEntity.setOperatingSystemVersion(this.operatingSystemVersion);
        return computerEntity;
    }
}
