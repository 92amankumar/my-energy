package com.myenergy.repository;

import com.myenergy.entity.Readings;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReadingsRepository extends CrudRepository<Readings, String> {
    Optional<Readings> findBySmartMeterId(String smartMeterId);
}
