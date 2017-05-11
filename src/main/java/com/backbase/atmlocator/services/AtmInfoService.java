package com.backbase.atmlocator.services;

import java.util.List;

import com.backbase.atmlocator.model.AtmLocation;

/**
 * @author Bhupendra
 *
 */
public interface AtmInfoService {

  public abstract List<AtmLocation> getATMLocationsByCity(String city);
}
