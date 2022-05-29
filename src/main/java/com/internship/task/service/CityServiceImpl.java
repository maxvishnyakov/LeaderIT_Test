package com.internship.task.service;

import com.internship.task.entity.City;
import com.internship.task.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    CityRepository cityRepository;

    @Override
    public void addCity(String name, Long population, String metroAvailable, String country) {
        Boolean metroExist = Boolean.valueOf(metroAvailable);
        City city = new City(name, population, metroExist, country);
        cityRepository.save(city);
    }

    @Override
    public Boolean editCity(Long idCity, Long populationUpdate, String metroAvailable) {
        if(cityRepository.existsById(idCity)) {
            City city = cityRepository.findById(idCity).get();
            if(populationUpdate != null){
                city.setPopulation(populationUpdate);
            }
            if(metroAvailable != null) {
                Boolean metroAvailableUpdate = Boolean.valueOf(metroAvailable);
                city.setMetro_available(metroAvailableUpdate);
            }
            cityRepository.save(city);
            return true;
        }
        return false;
    }
}
