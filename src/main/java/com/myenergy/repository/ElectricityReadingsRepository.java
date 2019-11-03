package com.myenergy.repository;

import com.myenergy.entity.ElectricityReadings;
import com.myenergy.entity.Readings;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ElectricityReadingsRepository extends CrudRepository<ElectricityReadings, Long> {
    Optional<List<ElectricityReadings>> findAllByReadings(Readings readings);
}
