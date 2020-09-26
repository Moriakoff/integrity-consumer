package kronos.daas.integrity.consumer.model;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class EventRequestDtoV1 {

  private String tenantId;
  private String pageIndex;
  private String product;
  private String category;
  private String subcategory;
  private String startDate;
  private String endDate;
  private Integer pageSize;
}
