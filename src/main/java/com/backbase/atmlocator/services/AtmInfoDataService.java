package com.backbase.atmlocator.services;

import java.util.List;

import com.backbase.atmlocator.model.AtmLocation;

public interface AtmInfoDataService {
  
  public abstract List<AtmLocation> getATMLocationsData();
}
