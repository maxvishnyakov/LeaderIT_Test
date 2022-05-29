package com.internship.task.service;

import com.internship.task.entity.Sight;

import java.util.Date;
import java.util.List;

public interface SightService {

    List<Sight> readAllSights(String typeSightFilter, Boolean sortNameParam);

    List<Sight> readSightsByCity(Long cityId);

    void addSight(String name,Date date, String shortDescription, String typeSight, Long cityID);

    void editSight(Long sightId, String shortDescription);

    boolean deleteSight(Long sightId);
}
