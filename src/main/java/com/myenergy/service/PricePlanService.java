package com.myenergy.service;

import com.myenergy.dto.PricePlanComparisonDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PricePlanService {
    PricePlanComparisonDto getPricePlanComparisons(String smartMeterId);
    List<Map.Entry<String, BigDecimal>> getRecommendedPricePlans(String smartMeterId);
}
