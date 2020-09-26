package kronos.daas.integrity.consumer.controller;

import kronos.daas.integrity.consumer.service.ValidationService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

  private final ValidationService validationService;

  @Autowired
  public EventController(ValidationService validationService) {
    this.validationService = validationService;
  }

  @GetMapping("/validate_default_events")
  public boolean validateEvent(@RequestParam("startDate") String startDate,
      @RequestParam("endDate") String endDate,
      @RequestParam("pageSize") int pageSize){
    return validationService.validateEvent(startDate,endDate,pageSize);
  }




}
