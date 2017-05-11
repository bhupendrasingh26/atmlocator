package com.backbase.atmlocator.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.backbase.atmlocator.model.AtmLocation;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AtmInfoDataServiceImpl implements AtmInfoDataService {

  @Value("${url}")
  private String serviceURL;

  @Override
  public List<AtmLocation> getATMLocationsData() {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity(serviceURL, String.class);

    ObjectMapper mapper = new ObjectMapper();
    AtmLocation[] forNow = null;
    try {
      forNow = mapper.readValue(response.getBody().substring(response.getBody().indexOf("[")), AtmLocation[].class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Arrays.asList(forNow);
  }

}
