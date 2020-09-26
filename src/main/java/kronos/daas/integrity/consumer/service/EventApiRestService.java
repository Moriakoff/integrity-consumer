package kronos.daas.integrity.consumer.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kronos.daas.integrity.consumer.model.EventRequestDtoV1;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EventApiRestService {

  @Value("${event-api.rest.endpoint}")
  private String endpoint;


  private final RestTemplate restTemplate;

  @Autowired
  public EventApiRestService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
/*    List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
    messageConverters.add(converter);
    restTemplate.setMessageConverters(messageConverters);*/
  }

  public JSONObject getEventsFromEventApi(String startDate, String endDate, int pageSize) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    EventRequestDtoV1 requestDto = EventRequestDtoV1.builder()
        .tenantId("tenant1")
        .startDate(startDate)
        .endDate(endDate)
        .pageSize(pageSize)
        .build();

    HttpEntity<EventRequestDtoV1> entity = new HttpEntity<EventRequestDtoV1>(requestDto, headers);
    ResponseEntity<Resource> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, entity, Resource.class );

    JSONObject jsonObject;
    try {
      BufferedReader streamReader = new BufferedReader(new InputStreamReader(responseEntity.getBody().getInputStream(), "UTF-8"));
      StringBuilder responseStrBuilder = new StringBuilder();

      String inputStr;
      while ((inputStr = streamReader.readLine()) != null)
        responseStrBuilder.append(inputStr);

      jsonObject = new JSONObject(responseStrBuilder.toString());
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
    return jsonObject;
  }
}
