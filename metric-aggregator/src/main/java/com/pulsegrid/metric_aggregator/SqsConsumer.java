package com.pulsegrid.metric_aggregator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.net.URI;
import java.util.List;

@Component
public class SqsConsumer {

    private final MetricRepository repository;

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

    public SqsConsumer(MetricRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedRate = 5000)
    public void consume() {
        ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                .queueUrl(SQS_QUEUE_URL)
                .maxNumberOfMessages(10)
                .build();

        List<Message> messages = sqsClient.receiveMessage(request).messages();

        for (Message message : messages) {
            try {
                MetricPayload payload = objectMapper
                        .readValue(message.body(), MetricPayload.class);

                ServiceMetric metric = new ServiceMetric();
                metric.setServiceId(payload.getServiceId());
                metric.setServiceName(payload.getServiceName());
                metric.setCpuUsage(payload.getCpuUsage());
                metric.setMemoryUsage(payload.getMemoryUsage());
                metric.setLatencyMs(payload.getLatencyMs());
                metric.setErrorCount(payload.getErrorCount());
                metric.setTimestamp(payload.getTimestamp());

                repository.save(metric);
                System.out.println("Saved to DB: " + payload.getServiceId());

                sqsClient.deleteMessage(DeleteMessageRequest.builder()
                        .queueUrl(SQS_QUEUE_URL)
                        .receiptHandle(message.receiptHandle())
                        .build());

            } catch (Exception e) {
                System.err.println("Failed to process message: " + e.getMessage());
            }
        }
    }
}