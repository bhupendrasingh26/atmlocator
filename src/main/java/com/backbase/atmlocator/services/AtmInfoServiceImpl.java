package com.backbase.atmlocator.services;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backbase.atmlocator.model.AtmLocation;

/**
 * Implementation Class for AtmInfoService.
 *
 */
@Component
public class AtmInfoServiceImpl implements AtmInfoService {

  private AtmInfoDataService atmInfoDataService;
  private List<AtmLocation> atmLocationsData;

  @Autowired
  public AtmInfoServiceImpl(AtmInfoDataService atmInfoDataService) {
    this.atmLocationsData = new ArrayList<AtmLocation>();
    this.atmInfoDataService = atmInfoDataService;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.backbase.atmlocator.services.AtmInfoService#getATMLocationsByCity(java.
   * lang.String)
   */
  @Override
  public List<AtmLocation> getATMLocationsByCity(String city) {

    List<AtmLocation> atmsByCity = new ArrayList<AtmLocation>();
    
    if (atmLocationsData.isEmpty())
      atmLocationsData = atmInfoDataService.getATMLocationsData();
    
    if (city != null && city.length() != 0) {
      Stream<AtmLocation> atmsStreams = atmLocationsData.stream()
          .filter(l -> l.getType().equalsIgnoreCase("ING") && l.getAddress().getCity().equalsIgnoreCase(city));
      atmsByCity = atmsStreams.collect(toList());
    }
    return atmsByCity;
  }

}
