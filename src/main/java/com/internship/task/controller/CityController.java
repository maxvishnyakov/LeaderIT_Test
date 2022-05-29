package com.internship.task.controller;

import com.internship.task.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/city")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addCity(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "population", required = false) Long population,
                                     @RequestParam(value = "metroAvailable", required = false) String metroAvailable,
                                     @RequestParam(value = "country", required = false) String country) {
        cityService.addCity(name, population, metroAvailable, country);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/edit/{idCity}")
    public ResponseEntity<?> editCity(@PathVariable(name = "idCity") Long idCity,
                                      @RequestParam(name = "population", required = false) Long population,
                                      @RequestParam(name = "metroAvailable", required = false) String metroAvailable) {
        final boolean updated = cityService.editCity(idCity, population, metroAvailable);

       return updated
               ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
