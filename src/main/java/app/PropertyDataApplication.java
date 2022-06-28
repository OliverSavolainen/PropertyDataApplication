package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PropertyDataApplication {

    private static final Logger log = LoggerFactory.getLogger(PropertyDataApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PropertyDataApplication.class);
    }

}
