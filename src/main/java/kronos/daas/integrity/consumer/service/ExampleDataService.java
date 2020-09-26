package kronos.daas.integrity.consumer.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ExampleDataService {

  public JSONObject createTestData(String startDate, String endDate, int pageSize){
    JSONObject result = new JSONObject();
    result.put("events", new JSONArray());
    int[] markerIds = new int[]{11,12,13,14,15,16,17,18,19,20};
    int[] userIds = new int[]{11,12,13,14,15,16,17,18,19,20};
    String[] dates = new String[]{"20200901","20200902","20200903","20200904","20200905",
        "20200906","20200907","20200908","20200909","20200910"};

    int count = 0;
    int iterations = Integer.parseInt(endDate) - Integer.parseInt(startDate);

    while (iterations > 0) {
      for (int markerId : markerIds) {
        if (count >= pageSize) {
          break;
        }
        for (int userId : userIds) {
          for (String date : dates) {
            result.getJSONArray("events").put(new JSONObject()
                .put("objectId", userId)
                .put("markerId", markerId)
                .put("applyDate", date));
            count++;
          }
        }
      }
      iterations--;
    }

    return result;
  }

}
