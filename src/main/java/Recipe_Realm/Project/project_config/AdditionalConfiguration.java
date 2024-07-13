package Recipe_Realm.Project.project_config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AdditionalProperties.class)
@Data
public class AdditionalConfiguration {

    @Autowired
    private AdditionalProperties additionalProperties;

}