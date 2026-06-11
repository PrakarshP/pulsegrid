package com.pulsegrid.metricemitter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class MetricEmitter {

    private final Random random = new Random();
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Scheduled(fixedRate = 5000)
    public void emitMetric() {
        try {
            MetricPayload payload = new MetricPayload();
            payload.setServiceId("sap-connector-01");
            payload.setServiceName("SAP Connector Service");
            payload.setCpuUsage(20 + random.nextDouble() * 60);
            payload.setMemoryUsage(30 + random.nextDouble() * 50);
            payload.setLatencyMs(50 + random.nextInt(500));
            payload.setErrorCount(random.nextInt(5));
            payload.setTimestamp(LocalDateTime.now());

            String json = objectMapper.writeValueAsString(payload);
            System.out.println("Emitting metric: " + json);
        } catch (Exception e) {
            System.err.println("Error emitting metric: " + e.getMessage());
        }
    }
}
