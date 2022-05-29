package com.internship.task.controller;

import com.internship.task.entity.Sight;
import com.internship.task.service.SightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/sights")
public class SightController {

    private final SightService sightService;

    @Autowired
    public SightController(SightService sightService) {
        this.sightService = sightService;
    }

    @GetMapping
    public ResponseEntity<List<Sight>> readSights(@RequestParam(value = "filterTypeParam", required = false)String filterParam,
                                                  @RequestParam(value = "sortByNameParam", required = false)Boolean sortParam) {
        final List<Sight> sights = sightService.readAllSights(filterParam, sortParam);
        return sights != null && !sights.isEmpty()
                ? new ResponseEntity<>(sights, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/{cityId}")
    public ResponseEntity<List<Sight>> readSightsByCity(@PathVariable(name = "cityId") Long cityId) {
        final List<Sight> sights = sightService.readSightsByCity(cityId);
        return sights != null && !sights.isEmpty()
                ? new ResponseEntity<>(sights, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addSight(@RequestParam(value = "name", required = false)String name,
                                      @RequestParam(value = "consctructionDate", required = false)String constructionDate,
                                      @RequestParam(value = "shortDescription", required = false)String shortDescription,
                                      @RequestParam(value = "typeSight", required = false)String typeSight,
                                      @RequestParam(value = "cityId", required = false) Long cityId) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(constructionDate);
        sightService.addSight(name,date,shortDescription, typeSight, cityId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/edit/{sightId}")
    public ResponseEntity<?> editSight(@PathVariable(name = "sightId") Long sightId,
                                       @RequestParam(value = "shortDescription", required = false)String shortDescription) {
        sightService.editSight(sightId, shortDescription);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{sightID}")
    public ResponseEntity<?> deleteSight(@PathVariable(name = "sightID") Long sightID) {
        final boolean deleted = sightService.deleteSight(sightID);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
