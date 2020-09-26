package kronos.daas.integrity.consumer.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

  @Autowired
  private EventApiRestService eventApiService;

  @Autowired
  private ExampleDataService exampleService;


  public boolean validateEvent(String startDate, String endDate, int pageSize){
    JSONArray events = eventApiService.getEventsFromEventApi(startDate,endDate,pageSize).getJSONArray("events");
    JSONArray testData = exampleService.createTestData(startDate,endDate ,pageSize ).getJSONArray("events");
    boolean equals;
    String result;

    try(PrintWriter printWriter = new PrintWriter(new File("test_result.txt"))){
      printWriter.println("events_from_bigtable|test_data|result");
      for (int i = 0; i < events.length(); i++) {
        equals=events.get(i).toString().equals(testData.get(i));
        result = (events.get(i) + "|" + testData.get(i) +"|" + (equals?"✓":"x"));
        printWriter.println(result);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }


    return events.toString().equals(testData.toString());

  }

}
