package com.myenergy.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class PowerSupplier {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    public PowerSupplier() {
    }

    public PowerSupplier(String id, String description, BigDecimal price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }
}
