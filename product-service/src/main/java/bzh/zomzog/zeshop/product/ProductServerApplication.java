package bzh.zomzog.zeshop.product;

import bzh.zomzog.zeshop.product.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableDiscoveryClient
@SpringBootApplication
@EnableResourceServer
@EnableConfigurationProperties(StorageProperties.class)
public class ProductServerApplication extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(ProductServerApplication.class, args);
    }

}