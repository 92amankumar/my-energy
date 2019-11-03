package com.myenergy.service;

import com.myenergy.dto.GetElectricityReadingsDto;
import com.myenergy.dto.ReadingsDto;

import java.util.List;

public interface ReadingService {
    void saveReading(ReadingsDto readingsDto);
    List<GetElectricityReadingsDto> getAllReadings(String smartMeterId);

}
