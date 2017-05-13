package com.backbase.atmlocator.services;

import static com.backbase.atmlocator.AtmLocationTestHelper.createAtmlocationDataList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.backbase.atmlocator.model.AtmLocation;

@RunWith(SpringJUnit4ClassRunner.class)
public class AtmInfoServiceImplTest {

  @Mock
  private AtmInfoDataService atmInfoDataService;

  @InjectMocks
  private AtmInfoServiceImpl atmInfoService;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public final void testGetATMLocationsByCity() {
    List<AtmLocation> locationData = createAtmlocationDataList(10, "ING", "Putten");
    locationData.addAll(createAtmlocationDataList(20, "ING", "Deventer"));
    locationData.addAll(createAtmlocationDataList(10, "HSBC", "Putten"));
    when(atmInfoDataService.getATMLocationsData()).thenReturn(locationData);
    List<AtmLocation> locationDataByCity = atmInfoService.getATMLocationsByCity("Putten");
    assertEquals(10, locationDataByCity.size());
    assertEquals("Putten", locationDataByCity.get(0).getAddress().getCity());
  }

  @Test
  public final void testGetATMLocationsByCityForCityNameAndType() {
    List<AtmLocation> locationData = createAtmlocationDataList(10, "ING", "Putten");
    locationData.addAll(createAtmlocationDataList(20, "ING", "Deventer"));
    locationData.addAll(createAtmlocationDataList(10, "HSBC", "Putten"));
    when(atmInfoDataService.getATMLocationsData()).thenReturn(locationData);
    List<AtmLocation> locationDataByCity = atmInfoService.getATMLocationsByCity("Putten");
    assertEquals(10, locationDataByCity.size());
    assertEquals("Putten", locationDataByCity.get(0).getAddress().getCity());
    assertEquals("ING", locationDataByCity.get(0).getType());
  }

  @Test
  public final void testGetATMLocationsByCityDeventer() {
    List<AtmLocation> locationData = createAtmlocationDataList(10, "ING", "Putten");
    locationData.addAll(createAtmlocationDataList(10, "ING", "Putten"));
    locationData.addAll(createAtmlocationDataList(20, "ING", "Deventer"));
    locationData.addAll(createAtmlocationDataList(10, "HSBC", "Putten"));
    when(atmInfoDataService.getATMLocationsData()).thenReturn(locationData);
    List<AtmLocation> locationDataByCity = atmInfoService.getATMLocationsByCity("Deventer");
    assertEquals(20, locationDataByCity.size());
    assertEquals("Deventer", locationDataByCity.get(0).getAddress().getCity());
  }

  @Test
  public final void testGetATMLocationsByCityTypeOnlyING() {
    List<AtmLocation> locationData = createAtmlocationDataList(10, "HSBC", "Putten");
    when(atmInfoDataService.getATMLocationsData()).thenReturn(locationData);
    List<AtmLocation> locationDataByCity = atmInfoService.getATMLocationsByCity("Putten");
    assertEquals(0, locationDataByCity.size());
  }

  @Test
  public final void testGetATMLocationsByCityCityNameEmpty() {
    List<AtmLocation> locationData = createAtmlocationDataList(10, "ING", "Putten");
    when(atmInfoDataService.getATMLocationsData()).thenReturn(locationData);
    List<AtmLocation> locationDataByCity = atmInfoService.getATMLocationsByCity("Deventer");
    assertEquals(0, locationDataByCity.size());
  }

  @Test
  public final void testGetATMLocationsByCityCityNameNull() {
    List<AtmLocation> locationData = createAtmlocationDataList(10, "ING", "Putten");
    when(atmInfoDataService.getATMLocationsData()).thenReturn(locationData);
    List<AtmLocation> locationDataByCity = atmInfoService.getATMLocationsByCity(null);
    assertEquals(0, locationDataByCity.size());
  }
}
