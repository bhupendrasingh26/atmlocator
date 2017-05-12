package com.backbase.atmlocator.services;

import static com.backbase.atmlocator.AtmLocationTestHelper.createAtmlocationDataList;
import static com.backbase.atmlocator.AtmLocationTestHelper.createAtmlocationNonINGDataList;
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
  public final void testGetATMLocationsByCityPutten() {
    List<AtmLocation> locationData = createAtmlocationDataList();
    when(atmInfoDataService.getATMLocationsData()).thenReturn(locationData);
    List<AtmLocation> locationDataByCity = atmInfoService.getATMLocationsByCity("Putten");
    assertEquals(2, locationDataByCity.size());

  }

  @Test
  public final void testGetATMLocationsByCityDeventer() {
    List<AtmLocation> locationData = createAtmlocationDataList();
    when(atmInfoDataService.getATMLocationsData()).thenReturn(locationData);
    List<AtmLocation> locationDataByCity = atmInfoService.getATMLocationsByCity("Deventer");
    assertEquals(1, locationDataByCity.size());

  }

  @Test
  public final void testGetATMLocationsByCityTypeOnlyING() {
    List<AtmLocation> locationData = createAtmlocationNonINGDataList();
    when(atmInfoDataService.getATMLocationsData()).thenReturn(locationData);
    List<AtmLocation> locationDataByCity = atmInfoService.getATMLocationsByCity("Deventer");
    assertEquals(0, locationDataByCity.size());

  }

}
