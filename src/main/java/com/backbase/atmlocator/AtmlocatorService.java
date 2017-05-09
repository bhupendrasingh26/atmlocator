package com.backbase.atmlocator;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class exposes RESTFul end-point for the 
 *  
 * @author Bhupendra
 */

@RestController
@RequestMapping("/atmlocator")
public class AtmlocatorService {
  
  /**
   * 
   * 
   * @return 
   * @throws IOException 
   * @throws JsonMappingException 
   * @throws JsonParseException 
   * 
   */
  @RequestMapping("/locate")
  public List<AtmLocation> locateAtms(@RequestParam(value="city", defaultValue="leiden") String city) throws JsonParseException, JsonMappingException, IOException {
    
    RestTemplate restTemplate = new RestTemplate();
   
    ResponseEntity<String> response  = restTemplate.getForEntity("https://www.ing.nl/api/locator/atms/", String.class);
    
    ObjectMapper mapper = new ObjectMapper();
    AtmLocation[] forNow =  mapper.readValue(response.getBody().substring(response.getBody().indexOf("[")),  AtmLocation[].class);
    Stream<AtmLocation> locationsStream = Stream.of(forNow); 
    List<AtmLocation> atmsByCity = locationsStream.filter(location -> location.getAddress().getCity().equals(city)).collect(Collectors.toList());;
    return atmsByCity;
  }

}
class Quote{
  
  private String type;
  private Value value;

  public Quote() {
  }

  public String getType() {
      return type;
  }

  public void setType(String type) {
      this.type = type;
  }

  public Value getValue() {
      return value;
  }

  public void setValue(Value value) {
      this.value = value;
  }

  
  
}


class Value {

  private Long id;
  private String quote;

  public Value() {
  }

  public Long getId() {
      return this.id;
  }

  public String getQuote() {
      return this.quote;
  }

  public void setId(Long id) {
      this.id = id;
  }

  public void setQuote(String quote) {
      this.quote = quote;
  }
  
}