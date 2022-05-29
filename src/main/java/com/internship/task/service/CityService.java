package com.internship.task.service;

public interface CityService {
    void addCity(String name, Long population, String metroAvailable, String country);

    Boolean editCity(Long idCity, Long population, String metroAvailable);
}
