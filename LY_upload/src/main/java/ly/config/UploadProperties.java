package ly.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
/*
* 用来获取配置文件中定义好的文件类型和域名
* */
@Data
@ConfigurationProperties(prefix = "ly.upload")
public class UploadProperties {
    String baseUrl;
    List<String> allowTypes;
}
