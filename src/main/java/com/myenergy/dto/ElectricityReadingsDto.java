package com.myenergy.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ElectricityReadingsDto {
    private Long time;
    private BigDecimal reading;
}
