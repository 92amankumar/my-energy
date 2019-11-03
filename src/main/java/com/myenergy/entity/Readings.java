package com.myenergy.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "readings")
public class Readings {

    @Id
    @Column(name = "smartmeterid")
    private String smartMeterId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "priceplanid", referencedColumnName = "id")
    private PowerSupplier pricePlanId;

    @OneToMany(mappedBy = "readings", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ElectricityReadings> electricityReadings;

    public Readings(){}

    public Readings(String smartMeterId) {
        this.smartMeterId = smartMeterId;
    }
}
