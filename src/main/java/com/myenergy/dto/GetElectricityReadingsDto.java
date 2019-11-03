package com.myenergy.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetElectricityReadingsDto {
    private String time;
    private BigDecimal reading;

    public GetElectricityReadingsDto(String time, BigDecimal reading) {
        this.time = time;
        this.reading = reading;
    }
}
