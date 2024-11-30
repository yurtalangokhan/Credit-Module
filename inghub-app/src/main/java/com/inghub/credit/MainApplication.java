package com.inghub.credit;


import com.inghub.credit.configuration.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author gyurtalan
 */
@EnableFeignClients
@SpringBootApplication(scanBasePackageClasses = {ApplicationConfig.class})
public class MainApplication {

    public static void main(String[] args) {
        System.out.println("-Main Application-");
        SpringApplication.run(new Class[]{MainApplication.class}, args);
    }
}


