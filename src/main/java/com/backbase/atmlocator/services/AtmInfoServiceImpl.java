package com.backbase.atmlocator.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backbase.atmlocator.model.AtmLocation;

@Component
public class AtmInfoServiceImpl implements AtmInfoService {

  private AtmInfoDataService atmInfoDataService;

  @Autowired
  public AtmInfoServiceImpl(AtmInfoDataService atmInfoDataService) {
    this.atmInfoDataService = atmInfoDataService;
  }

  @Override
  public List<AtmLocation> getATMLocationsByCity(String city) {
    List<AtmLocation> atmLocationsData = atmInfoDataService.getATMLocationsData();
    Stream<AtmLocation> locationsStream = atmLocationsData.stream();
    List<AtmLocation> atmsByCity = locationsStream.filter(location -> location.getAddress().getCity().equals(city))
        .collect(Collectors.toList());
    return atmsByCity;
  }

}
