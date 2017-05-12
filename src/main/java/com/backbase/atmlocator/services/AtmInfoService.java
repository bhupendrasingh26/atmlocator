package com.backbase.atmlocator.services;

import java.util.List;

import com.backbase.atmlocator.model.AtmLocation;

/**
 * 
 * Service exposes various methods for retrieving ATMLocation based on specified
 * criteria.
 * 
 *
 */
public interface AtmInfoService {

  /**
   * Filters the ATM Location by city name and returns a list.
   * By default method only select ATM Owned by "ING".
   * @param city
   * @return ATM Location List.
   */
  public abstract List<AtmLocation> getATMLocationsByCity(String city);
}
