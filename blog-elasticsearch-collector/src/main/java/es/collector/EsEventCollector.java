package es.collector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication(scanBasePackages = { "es.collector", "com.blog.app"})
@IntegrationComponentScan
public class EsEventCollector {

    public static void main(String[] args) {
        SpringApplication.run(EsEventCollector.class, args);
    }

}
