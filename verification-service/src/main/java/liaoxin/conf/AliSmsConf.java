package liaoxin.conf;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Auther: liaoxin
 * @Date: 2023/1/11
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "sms")
public class AliSmsConf {

    private String accessKeyId;

    private String accessKeySecret;

    private String endpoint;

    /**
     * 创建客户端
     */
    @Bean
    public Client createClient() throws Exception {
        Config config = new Config().setAccessKeyId(accessKeyId)
                                    .setAccessKeySecret(accessKeySecret)
                                    .setEndpoint(endpoint)
                                    .setConnectTimeout(30000);
        return new Client(config);
    }


}
