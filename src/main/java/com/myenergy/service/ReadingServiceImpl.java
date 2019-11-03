package com.myenergy.service;

import com.myenergy.dto.GetElectricityReadingsDto;
import com.myenergy.dto.ReadingsDto;
import com.myenergy.entity.ElectricityReadings;
import com.myenergy.entity.Readings;
import com.myenergy.repository.ElectricityReadingsRepository;
import com.myenergy.repository.PowerSupplierRepository;
import com.myenergy.repository.ReadingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@Service
public class ReadingServiceImpl implements ReadingService {

    @Autowired
    private ReadingsRepository readingsRepository;

    @Autowired
    private ElectricityReadingsRepository electricityReadingsRepository;

    @Autowired
    private PowerSupplierRepository powerSupplierRepository;

    @Override
    public void saveReading(ReadingsDto readingsDto) {
        Optional<Readings> readingsOptional = readingsRepository.findById(readingsDto.getSmartMeterId());
        Readings readings = null;
        Set<ElectricityReadings> electricityReadingsSet = null;
        if(readingsOptional.isPresent()){
            readings = readingsOptional.get();
            electricityReadingsSet = readings.getElectricityReadings();
        } else {
            readings = new Readings(readingsDto.getSmartMeterId());
            electricityReadingsSet = new HashSet<>();
        }
        Readings finalReadings = readings;
        Set<ElectricityReadings> finalElectricityReadingsSet = electricityReadingsSet;
        readingsDto.getElectricityReadings().forEach(element -> {
            ElectricityReadings electricityReadings = new ElectricityReadings();
            electricityReadings.setReadings(finalReadings);
            electricityReadings.setReading(element.getReading());
            electricityReadings.setTime(new Date(element.getTime()));
            finalElectricityReadingsSet.add(electricityReadings);
        });
        readings.setElectricityReadings(electricityReadingsSet);
        readingsRepository.save(readings);
    }

    @Override
    public List<GetElectricityReadingsDto> getAllReadings(String smartMeterId) {
        Optional<List<ElectricityReadings>> optionalElectricityReadingsSet = electricityReadingsRepository.findAllByReadings(new Readings(smartMeterId));
        if(optionalElectricityReadingsSet.isPresent()){
            List<ElectricityReadings> electricityReadingsSet = optionalElectricityReadingsSet.get();
            List<GetElectricityReadingsDto> electricityReadingsDtos = new ArrayList<>();
            final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
            electricityReadingsSet.forEach(element -> electricityReadingsDtos.add(new GetElectricityReadingsDto(dateFormat.format(element.getTime()), element.getReading())));
            return electricityReadingsDtos;
        } else {
            return null;
        }
    }
}
