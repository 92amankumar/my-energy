package com.myenergy.repository;

import com.myenergy.entity.PowerSupplier;
import org.springframework.data.repository.CrudRepository;

public interface PowerSupplierRepository extends CrudRepository<PowerSupplier, String> {
}
