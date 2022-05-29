package com.internship.task.service;

import com.internship.task.entity.City;
import com.internship.task.entity.Sight;
import com.internship.task.entity.TypeSight;
import com.internship.task.repository.CityRepository;
import com.internship.task.repository.SightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class SightServiceImpl implements SightService{

    @Autowired
    SightRepository sightRepository;

    @Autowired
    CityRepository cityRepository;

    @Override
    public List<Sight> readAllSights(String typeSight, Boolean sortNameParam) {
        List<Sight> sights = sightRepository.findAll();
        if(sortNameParam != null && sortNameParam == true) {
            String sort = "name";
            sights = sightRepository.findAll(Sort.by(sort).ascending());
        }
        if(typeSight != null) {
            TypeSight typeForFilter = TypeSight.valueOf(typeSight);
            sights = sights.stream().filter(s->s.getType_sight().equals(typeForFilter)).collect(Collectors.toList());
               }
            return sights;
    }

    @Override
    public List<Sight> readSightsByCity(Long cityId) {
        List<Sight> tmpList = sightRepository.findAll();
        List<Sight> resultList = new ArrayList<>();
        for(int i = 0; i < tmpList.size(); ++i) {
            if(tmpList.get(i).getCity().getId() == cityId) {
                resultList.add(tmpList.get(i));
            }
        }
        return resultList;
    }

    @Override
    public void addSight(String name, Date date, String shortDescription, String typeSight, Long cityID) {
        City city = cityRepository.findById(cityID).get();
        TypeSight type = TypeSight.valueOf(typeSight);
        Sight newSight = new Sight(name, date, shortDescription, type, city);
        sightRepository.save(newSight);
    }

    @Override
    public void editSight(Long sightId, String shortDescription) {
        Sight sightToEdit = sightRepository.findById(sightId).get();
        sightToEdit.setShort_description(shortDescription);
        sightRepository.save(sightToEdit);
    }

    @Override
    public boolean deleteSight(Long sightId) {
        if(sightRepository.existsById(sightId)) {
            sightRepository.deleteById(sightId);
            return true;
        }
        return false;
    }
}
