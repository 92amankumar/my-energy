package com.myenergy.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReadingsDto {
    private String smartMeterId;
    private List<ElectricityReadingsDto> electricityReadings;
}
