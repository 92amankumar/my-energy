package com.myenergy.controller;

import com.myenergy.dto.PricePlanComparisonDto;
import com.myenergy.service.PricePlanService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/price-plan")
public class PricePlanController {
    @Autowired
    private PricePlanService service;

    @ApiOperation("Compare Price Plans")
    @GetMapping("/compare/{smart-meter-id}")
    public ResponseEntity<PricePlanComparisonDto> compare(@PathVariable("smart-meter-id") String smartMeterId){
        PricePlanComparisonDto pricePlanComparisonDto = service.getPricePlanComparisons(smartMeterId);
        if(pricePlanComparisonDto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(pricePlanComparisonDto);
    }

    @ApiOperation("Recommend Price Plans")
    @GetMapping("/recommendations/{smart-meter-id}")
    public ResponseEntity<List<Map.Entry<String, BigDecimal>>> recommendations(@PathVariable("smart-meter-id") String smartMeterId){
        List<Map.Entry<String, BigDecimal>> recommendations = service.getRecommendedPricePlans(smartMeterId);
        if(recommendations == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(recommendations);
    }
}
