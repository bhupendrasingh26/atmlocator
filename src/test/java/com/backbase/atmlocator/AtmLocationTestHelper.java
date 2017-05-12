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

  public static List<AtmLocation> createAtmlocationNonINGDataList() {
    List<AtmLocation> locations = new ArrayList<AtmLocation>();

    Geolocation glocaton4 = new Geolocation();
    glocaton4.setLat("52.260025");
    glocaton4.setLng("5.609934");

    Address address4 = new Address();
    address4.setGeoLocation(glocaton4);
    address4.setCity("Putten");
    address4.setHousenumber("2");
    address4.setStreet("Voorthuizerstraat");
    address4.setPostalcode("3881 SH");

    AtmLocation location4 = new AtmLocation();
    location4.setAddress(address4);
    location4.setDistance("0");
    location4.setType("HSBC");

    locations.add(location4);

    return locations;
  }

  public static List<AtmLocation> createAtmlocationDataList() {
    List<AtmLocation> locations = new ArrayList<AtmLocation>();

    Geolocation glocaton1 = new Geolocation();
    glocaton1.setLat("52.2605422");
    glocaton1.setLng("5.6051248");

    Address address1 = new Address();
    address1.setGeoLocation(glocaton1);
    address1.setCity("Putten");
    address1.setHousenumber("34");
    address1.setStreet("Dorpsstraat");
    address1.setPostalcode("3881 BD");

    AtmLocation location1 = new AtmLocation();
    location1.setAddress(address1);
    location1.setDistance("0");
    location1.setType("ING");

    Geolocation glocaton2 = new Geolocation();
    glocaton2.setLat("52.260025");
    glocaton2.setLng("5.609934");

    Address address2 = new Address();
    address2.setGeoLocation(glocaton2);
    address2.setCity("Putten");
    address2.setHousenumber("2");
    address2.setStreet("Voorthuizerstraat");
    address2.setPostalcode("3881 SH");

    AtmLocation location2 = new AtmLocation();
    location2.setAddress(address2);
    location2.setDistance("0");
    location2.setType("ING");

    Geolocation glocaton3 = new Geolocation();
    glocaton3.setLat("52.260025");
    glocaton3.setLng("5.609934");

    Address address3 = new Address();
    address3.setGeoLocation(glocaton3);
    address3.setCity("Deventer");
    address3.setHousenumber("2");
    address3.setStreet("Voorthuizerstraat");
    address3.setPostalcode("3881 SH");

    AtmLocation location3 = new AtmLocation();
    location3.setAddress(address3);
    location3.setDistance("0");
    location3.setType("ING");

    Geolocation glocaton4 = new Geolocation();
    glocaton4.setLat("52.260025");
    glocaton4.setLng("5.609934");

    Address address4 = new Address();
    address4.setGeoLocation(glocaton4);
    address4.setCity("Putten");
    address4.setHousenumber("2");
    address4.setStreet("Voorthuizerstraat");
    address4.setPostalcode("3881 SH");

    AtmLocation location4 = new AtmLocation();
    location4.setAddress(address4);
    location4.setDistance("0");
    location4.setType("HSBC");

    locations.add(location1);
    locations.add(location2);
    locations.add(location3);
    locations.add(location4);

    return locations;
  }

  public static String getJSONResponseData() {
    String jsonLocationsInString = null;
    List<AtmLocation> data = createAtmlocationDataList();
    AtmLocation[] locations = createAtmlocationDataList().toArray(new AtmLocation[data.size()]);
    ObjectMapper mapper = new ObjectMapper();
    try {
      jsonLocationsInString = mapper.writeValueAsString(locations);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return jsonLocationsInString;
  }

}
