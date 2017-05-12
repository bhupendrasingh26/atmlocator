package com.backbase.atmlocator;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backbase.atmlocator.model.AtmLocation;
import com.backbase.atmlocator.services.AtmInfoService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * This class exposes RESTFul end-point for locating ATM by city.
 */

@RestController

public class AtmlocatorController {

  private final AtmInfoService atmInfoService;

  @Autowired
  public AtmlocatorController(AtmInfoService atmInfoService) {
    this.atmInfoService = atmInfoService;
  }

  @RequestMapping("/locate")
  public List<AtmLocation> locateAtms(@RequestParam(value = "city") String city)
      throws JsonParseException, JsonMappingException, IOException {
    return atmInfoService.getATMLocationsByCity(city);
  }

}