package com.myenergy.controller;

import com.myenergy.dto.GetElectricityReadingsDto;
import com.myenergy.dto.ReadingsDto;
import com.myenergy.service.ReadingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/readings")
public class ReadingsController {
    @Autowired
    private ReadingService service;

    @ApiOperation("Save Readings")
    @PostMapping
    public ResponseEntity<Void> saveReadings(@RequestBody ReadingsDto readingsDto){
        if(StringUtils.isEmpty(readingsDto.getSmartMeterId()))
            return ResponseEntity.badRequest().build();
        if(readingsDto.getElectricityReadings() == null || readingsDto.getElectricityReadings().isEmpty())
            return ResponseEntity.badRequest().build();
        if(readingsDto.getElectricityReadings().stream().anyMatch(e -> (e.getReading()== null || e.getTime()==null || e.getReading().compareTo(BigDecimal.ZERO) < 1)))
            return ResponseEntity.badRequest().build();
        service.saveReading(readingsDto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Find All Electricity Readings")
    @GetMapping("/{smart-meter-id}")
    public ResponseEntity<List<GetElectricityReadingsDto>> getAllReadingsFromSmartMeterId(@PathVariable("smart-meter-id") String smartMeterId){
        if(StringUtils.isEmpty(smartMeterId))
            return ResponseEntity.badRequest().build();
        List<GetElectricityReadingsDto> getElectricityReadingsDtos = service.getAllReadings(smartMeterId);
        if(getElectricityReadingsDtos==null || service.getAllReadings(smartMeterId).isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(getElectricityReadingsDtos);
        }
    }
}
