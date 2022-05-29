package com.internship.task.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sights")
public class Sight {

    @Id
    @SequenceGenerator(name = "sequence_sights", sequenceName = "sights_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence_sights")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "construction_date")
    @Temporal(TemporalType.DATE)
    private Date construction_date;

    @Column(name = "short_description")
    private String short_description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_sight")
    private TypeSight type_sight;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonBackReference
    private City city;

    public Sight() {

    }

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

    public Date getConstruction_date() {
        return construction_date;
    }

    public void setConstruction_date(Date constructionDate) {
        this.construction_date = constructionDate;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String shortDescription) {
        this.short_description = shortDescription;
    }

    public TypeSight getType_sight() {
        return type_sight;
    }

    public void setType_sight(TypeSight typeSight) {
        this.type_sight = typeSight;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city_id) {
        this.city = city_id;
    }

    public Sight(String name, Date constructionDate, String shortDescription, TypeSight typeSight, City city_id) {
        this.name = name;
        this.construction_date = constructionDate;
        this.short_description = shortDescription;
        this.type_sight = typeSight;
        this.city = city_id;
    }
}
