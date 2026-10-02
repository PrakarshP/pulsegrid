package com.pulsegrid.metricemitter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Random;

@Component
public class MetricEmitter {

    private final Random random = new Random();
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    private static final String SQS_QUEUE_URL =
            "http://localstack:4566/000000000000/metrics-queue";

    private final SqsClient sqsClient = SqsClient.builder()
            .endpointOverride(URI.create("http://localstack:4566"))
            .region(Region.US_EAST_1)
            .credentialsProvider(StaticCredentialsProvider.create(
                    AwsBasicCredentials.create("test", "test")))
            .build();

    @Scheduled(fixedRate = 5000)
    public void emitMetric() {
        try {
            MetricPayload payload = buildPayload();
            String json = objectMapper.writeValueAsString(payload);
            sendToSqs(json);
        } catch (Exception e) {
            System.err.println("Error emitting metric: " + e.getMessage());
        }
    }

    private MetricPayload buildPayload() {
        MetricPayload payload = new MetricPayload();
        payload.setServiceId("sap-connector-01");
        payload.setServiceName("SAP Connector Service");
        payload.setCpuUsage(20 + random.nextDouble() * 60);
        payload.setMemoryUsage(30 + random.nextDouble() * 50);
        payload.setLatencyMs(50 + random.nextInt(500));
        payload.setErrorCount(random.nextInt(5));
        payload.setTimestamp(LocalDateTime.now());
        return payload;
    }

    private void sendToSqs(String messageBody) {
        SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(SQS_QUEUE_URL)
                .messageBody(messageBody)
                .build();
        sqsClient.sendMessage(request);
        System.out.println("Sent to SQS: " + messageBody);
    }
}