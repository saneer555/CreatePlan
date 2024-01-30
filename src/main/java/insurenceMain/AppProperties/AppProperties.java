package insurenceMain.AppProperties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "planapi")
@Configuration
@Data
public class AppProperties {
	
	public Map<String, String> messages = new HashMap<>();

}
