package com.backbase.atmlocator.services;

import java.util.List;

import com.backbase.atmlocator.model.AtmLocation;

/**
 * Data service which access a data source or Web Service.
 *
 */
public interface AtmInfoDataService {

  /**
   * Communicates with a Web Service and return all the available ATM Locations.
   * 
   * @return List of all the ATM Locations available.
   */
  public abstract List<AtmLocation> getATMLocationsData();
}
