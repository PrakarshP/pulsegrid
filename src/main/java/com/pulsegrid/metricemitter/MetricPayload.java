package com.pulsegrid.metricemitter;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MetricPayload {
    private String serviceId;
    private String serviceName;
    private double cpuUsage;
    private double memoryUsage;
    private long latencyMs;
    private int errorCount;
    private LocalDateTime timestamp;
}