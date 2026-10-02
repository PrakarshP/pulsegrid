package com.pulsegrid.metricemitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MetricEmitterApplication {
	public static void main(String[] args) {
		SpringApplication.run(MetricEmitterApplication.class, args);
	}
}