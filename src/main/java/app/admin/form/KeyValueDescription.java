package app.admin.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KeyValueDescription {
  private String key;
  private Object value;
  private String description;
  // 0表示文本，1表示选项框
  private Integer type;
  private String[] info;
}
