package app.admin.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KeyValueDescription {
  private String key;
  private Object value;
  private String description;
}
