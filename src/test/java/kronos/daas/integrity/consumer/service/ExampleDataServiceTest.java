package kronos.daas.integrity.consumer.service;

import org.json.JSONArray;
import org.json.JSONObject;


class ExampleDataServiceTest {

  public static void main(String[] args) {
    JSONObject result = new JSONObject();
    result.put("events", new JSONArray());
    int[] markerIds = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int[] userIds = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    String[] dates = new String[]{"2020-09-01", "2020-09-02", "2020-09-03", "2020-09-04",
        "2020-09-05",
        "2020-09-06", "2020-09-07", "2020-09-08", "2020-09-09", "2020-09-10"};

    for (int markerId : markerIds) {
      for (int userId : userIds) {
        for (String date : dates) {
          result.getJSONArray("events").put(new JSONObject()
              .put("objectId", userId)
              .put("markerId", markerId)
              .put("applyDate", date));
        }
      }
    }

    System.out.println(result.getJSONArray("events").length());
    System.out.println(result.getJSONArray("events"));
  }

}
