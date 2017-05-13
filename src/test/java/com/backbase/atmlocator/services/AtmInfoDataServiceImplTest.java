package com.backbase.atmlocator.services;

import static com.backbase.atmlocator.AtmLocationTestHelper.getJSONResponseData;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.backbase.atmlocator.AtmlocatorApplication;
import com.backbase.atmlocator.model.AtmLocation;

@RunWith(SpringJUnit4ClassRunner.class)
public class AtmInfoDataServiceImplTest {

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private AtmInfoDataServiceImpl atmDataInfoService;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetATMLocationsDataWorkingURL() {
    ReflectionTestUtils.setField(atmDataInfoService, "serviceURL", "test_url");
    ResponseEntity<String> mockResponseEntity = mock(ResponseEntity.class);
    when(restTemplate.getForEntity("test_url", String.class)).thenReturn(mockResponseEntity);
    when(mockResponseEntity.getBody()).thenReturn(getJSONResponseData());

    List<AtmLocation> locationDataSuperSet = atmDataInfoService.getATMLocationsData();
    assertEquals(100, locationDataSuperSet.size());
  }

  @Test
  public void testGetATMLocationsDataNotWellFormedResponseReturned() {
    ReflectionTestUtils.setField(atmDataInfoService, "serviceURL", "test_url");
    ResponseEntity<String> mockResponseEntity = mock(ResponseEntity.class);
    when(restTemplate.getForEntity("test_url", String.class)).thenReturn(mockResponseEntity);
    when(mockResponseEntity.getBody()).thenReturn("Bad Json},." + getJSONResponseData());

    List<AtmLocation> locationDataSuperSet = atmDataInfoService.getATMLocationsData();
    assertEquals(100, locationDataSuperSet.size());
  }

  @Test
  public void testGetATMLocationsDataBadResponse() {
    ReflectionTestUtils.setField(atmDataInfoService, "serviceURL", "test_url");
    ResponseEntity<String> mockResponseEntity = mock(ResponseEntity.class);
    when(restTemplate.getForEntity("test_url", String.class)).thenReturn(mockResponseEntity);
    when(mockResponseEntity.getBody()).thenReturn("[{,[}]");

    List<AtmLocation> locationDataSuperSet = atmDataInfoService.getATMLocationsData();
    assertEquals(0, locationDataSuperSet.size());
  }
}
