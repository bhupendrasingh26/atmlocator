package com.backbase.atmlocator;

import java.util.ArrayList;
import java.util.List;

import com.backbase.atmlocator.model.Address;
import com.backbase.atmlocator.model.AtmLocation;
import com.backbase.atmlocator.model.Geolocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Helper class for test data generation.
 *
 */
public class AtmLocationTestHelper {

  public static List<AtmLocation> createAtmlocationDataList(int count, String type, String city) {
    List<AtmLocation> locations = new ArrayList<AtmLocation>();
    for (int i = 1; i <= count; i++) {
      Geolocation glocaton = new Geolocation();
      glocaton.setLat("lat-" + i);
      glocaton.setLng("lng-" + i);
      Address address = new Address();
      address.setGeoLocation(glocaton);
      address.setCity(city);
      address.setHousenumber("House No " + i);
      address.setStreet("Street " + i);
      address.setPostalcode("Postal Code" + i);
      AtmLocation location = new AtmLocation();
      location.setAddress(address);
      location.setDistance("0");
      location.setType(type);
      locations.add(location);
    }
    return locations;
  }

  public static String getJSONResponseData() {
    String jsonLocationsInString = null;
    List<AtmLocation> data = createAtmlocationDataList(100, "Any Type", "Any Dutch City");
    AtmLocation[] locations = data.toArray(new AtmLocation[data.size()]);
    ObjectMapper mapper = new ObjectMapper();
    try {
      jsonLocationsInString = mapper.writeValueAsString(locations);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return jsonLocationsInString;
  }

}
