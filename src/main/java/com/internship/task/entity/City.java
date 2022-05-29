package com.internship.task.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "city")
public class City {
    @Id
    @SequenceGenerator(name = "sequence_city", sequenceName = "city_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence_city")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "population")
    private Long population;

    @Column(name = "metro_available")
    private Boolean metro_available;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Sight> sights = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Sight> getSights() {
        return sights;
    }

    public void setSights(List<Sight> sights) {
        this.sights = sights;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Boolean getMetro_available() {
        return metro_available;
    }

    public void setMetro_available(Boolean metroAvailable) {
        this.metro_available = metroAvailable;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public City() {

    }

    public City(String name, Long population, Boolean metroAvailable, String country) {
        this.name = name;
        this.population = population;
        this.metro_available = metroAvailable;
        this.country = country;
    }
}
