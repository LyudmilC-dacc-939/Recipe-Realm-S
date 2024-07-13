package Recipe_Realm.Project.project_config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "additional")
@Data
public class AdditionalProperties {

    private String unit;
    private int max;

    // standard getters and setters
}