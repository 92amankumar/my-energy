package com.myenergy.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class PricePlanComparisonDto {
    private String pricePlanId;
    private Map<String, BigDecimal> pricePlanComparisons;
}
