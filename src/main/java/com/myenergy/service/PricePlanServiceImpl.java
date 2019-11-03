package com.myenergy.service;

import com.myenergy.dto.PricePlanComparisonDto;
import com.myenergy.entity.ElectricityReadings;
import com.myenergy.entity.PowerSupplier;
import com.myenergy.entity.Readings;
import com.myenergy.repository.PowerSupplierRepository;
import com.myenergy.repository.ReadingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class PricePlanServiceImpl implements PricePlanService {
    @Autowired
    private ReadingsRepository readingsRepository;

    @Autowired
    private PowerSupplierRepository powerSupplierRepository;

    @Override
    public PricePlanComparisonDto getPricePlanComparisons(String smartMeterId) {
        return getPricePlanComparisonDto(smartMeterId);
    }

    private PricePlanComparisonDto getPricePlanComparisonDto(String smartMeterId){
        Optional<Readings> readingsOptional = readingsRepository.findById(smartMeterId);
        if(readingsOptional.isPresent()){
            Readings readings = readingsOptional.get();
            PricePlanComparisonDto pricePlanComparisonDto = new PricePlanComparisonDto();
            Map<String, BigDecimal> pricePlanComparisons = new HashMap<>();
            pricePlanComparisonDto.setPricePlanId(readings.getPricePlanId().getId());
            Set<ElectricityReadings> electricityReadingsSet = readings.getElectricityReadings();
            ElectricityReadings max = electricityReadingsSet.stream().max(Comparator.comparing(ElectricityReadings::getTime)).get();
            ElectricityReadings min = electricityReadingsSet.stream().min(Comparator.comparing(ElectricityReadings::getTime)).get();
            BigDecimal totalUnits = max.getReading().subtract(min.getReading());
            Iterable<PowerSupplier> powerSuppliers = powerSupplierRepository.findAll();
            powerSuppliers.forEach(e -> pricePlanComparisons.put(e.getId(), e.getPrice().multiply(totalUnits)));
            pricePlanComparisonDto.setPricePlanComparisons(pricePlanComparisons);
            return pricePlanComparisonDto;
        }
        return null;
    }

    @Override
    public List<Map.Entry<String, BigDecimal>> getRecommendedPricePlans(String smartMeterId) {
        PricePlanComparisonDto pricePlanComparisonDto= getPricePlanComparisonDto(smartMeterId);
        if(pricePlanComparisonDto==null)
            return null;
        List<Map.Entry<String,BigDecimal>> recommendations = new ArrayList<>(pricePlanComparisonDto.getPricePlanComparisons().entrySet());
        recommendations.sort(Comparator.comparing(Map.Entry::getValue));
        return recommendations;
    }
}
