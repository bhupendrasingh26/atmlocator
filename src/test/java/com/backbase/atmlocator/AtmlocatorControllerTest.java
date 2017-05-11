package com.backbase.atmlocator;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.backbase.atmlocator.model.Address;
import com.backbase.atmlocator.model.AtmLocation;
import com.backbase.atmlocator.model.Geolocation;
import com.backbase.atmlocator.services.AtmInfoService;
import com.backbase.atmlocator.services.AtmInfoServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AtmlocatorApplication.class)
@SpringBootTest
public class AtmlocatorControllerTest {

  @Test
  public final void testLocateAtms() throws Exception {
    List<AtmLocation> locations = createAtmlocationList();
    AtmInfoService mockAtmInfoService = mock(AtmInfoServiceImpl.class);
    when(mockAtmInfoService.getATMLocationsByCity("Putten")).thenReturn(locations);

    AtmlocatorController controller = new AtmlocatorController(mockAtmInfoService);
    MockMvc mockMvc = standaloneSetup(controller).build();
    mockMvc.perform(get("/atmlocator/locate?city=Putten")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$", hasSize(2)))
        .andDo(print());

  }

  @Test
  public final void testLocateAtmsUnavailableCity() throws Exception {
    List<AtmLocation> locations = createAtmlocationList();
    AtmInfoService mockAtmInfoService = mock(AtmInfoServiceImpl.class);
    when(mockAtmInfoService.getATMLocationsByCity("Putten")).thenReturn(locations);

    AtmlocatorController controller = new AtmlocatorController(mockAtmInfoService);
    MockMvc mockMvc = standaloneSetup(controller).build();
    mockMvc.perform(get("/atmlocator/locate?city=Deventer")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$", hasSize(0)))
        .andDo(print());

  }

  @Test
  public final void testLocateAtmsInvalidParameterName() throws Exception {

    AtmInfoService mockAtmInfoService = mock(AtmInfoServiceImpl.class);
    AtmlocatorController controller = new AtmlocatorController(mockAtmInfoService);
    MockMvc mockMvc = standaloneSetup(controller).build();
    mockMvc.perform(get("/atmlocator/locate?cit=Deventer")).andExpect(status().is4xxClientError()).andDo(print());

  }

  private List<AtmLocation> createAtmlocationList() {
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

    locations.add(location1);
    locations.add(location2);

    return locations;
  }
}
