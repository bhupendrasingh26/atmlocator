package com.backbase.atmlocator.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.backbase.atmlocator.model.AtmLocation;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implementation Class for AtmInfoDataService.
 *
 */
@Component
public class AtmInfoDataServiceImpl implements AtmInfoDataService {

  @Value("${url}")
  private String serviceURL;

  @Autowired
  private RestTemplate restTemplate;

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.backbase.atmlocator.services.AtmInfoDataService#getATMLocationsData()
   */
  @Override
  public List<AtmLocation> getATMLocationsData() {
    List<AtmLocation> atmLocations = new ArrayList<AtmLocation>();
    try {
      ResponseEntity<String> response = restTemplate.getForEntity(serviceURL, String.class);
      String responseBody = sanatizeResponse(response.getBody());
      ObjectMapper mapper = new ObjectMapper();
      atmLocations = Arrays.asList(mapper.readValue(responseBody, AtmLocation[].class));

    } catch (IOException e) {
      e.printStackTrace();
    }
    return atmLocations;
  }

  /**
   * Method skips unwanted initial characters from the JSON-Response and return
   * a well formed JSON as String.
   * 
   * @param responseBody
   * @return Well form JSON as string.
   */
  private String sanatizeResponse(String responseBody) {
    return responseBody.substring(responseBody.indexOf("["));
  }
}
