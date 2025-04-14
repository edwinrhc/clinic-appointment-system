package com.edwin.clinic;

import com.edwin.clinic.configuration.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class ClinicAppointmentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicAppointmentSystemApplication.class, args);
    }

}
