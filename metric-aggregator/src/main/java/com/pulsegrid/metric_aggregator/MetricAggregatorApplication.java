package com.pulsegrid.metric_aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MetricAggregatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(MetricAggregatorApplication.class, args);
	}
}