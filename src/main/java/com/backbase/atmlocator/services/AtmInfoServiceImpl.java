package com.backbase.atmlocator.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backbase.atmlocator.model.AtmLocation;

@Component
public class AtmInfoServiceImpl implements AtmInfoService {

  private AtmInfoDataService atmInfoDataService;
  private List<AtmLocation> atmLocationsData;

  @Autowired
  public AtmInfoServiceImpl(AtmInfoDataService atmInfoDataService) {
    this.atmLocationsData = new ArrayList<AtmLocation>();
    this.atmInfoDataService = atmInfoDataService;
  }

  @Override
  public List<AtmLocation> getATMLocationsByCity(String city) {

    List<AtmLocation> atmsByCity = new ArrayList<AtmLocation>();

    if (atmLocationsData.isEmpty())
      atmLocationsData = atmInfoDataService.getATMLocationsData();

    Stream<AtmLocation> ingAtmsStream = atmLocationsData.stream().filter(l -> l.getType().equalsIgnoreCase("ING"));

    if (city != null && city.length() != 0)
      atmsByCity = ingAtmsStream.filter(l -> l.getAddress().getCity().equalsIgnoreCase(city))
          .collect(Collectors.toList());

    return atmsByCity;
  }

}
