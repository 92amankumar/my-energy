package com.myenergy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString(exclude = {"readings"})
@Entity
@Table(name = "electricityreadings")
public class ElectricityReadings {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "readings", nullable = false)
    @JsonIgnore
    private Readings readings;

    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @Column(name = "reading")
    private BigDecimal reading;

    public ElectricityReadings(){}

    public ElectricityReadings(Readings readings, Date time, BigDecimal reading) {
        this.readings = readings;
        this.time = time;
        this.reading = reading;
    }
}
