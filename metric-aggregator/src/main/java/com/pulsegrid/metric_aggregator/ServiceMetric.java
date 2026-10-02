package com.pulsegrid.metric_aggregator;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_metrics")
public class ServiceMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceId;
    private String serviceName;
    private double cpuUsage;
    private double memoryUsage;
    private long latencyMs;
    private int errorCount;
    private LocalDateTime timestamp;
    private LocalDateTime createdAt = LocalDateTime.now();

    public void setServiceId(String serviceId) { this.serviceId = serviceId; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    public void setCpuUsage(double cpuUsage) { this.cpuUsage = cpuUsage; }
    public void setMemoryUsage(double memoryUsage) { this.memoryUsage = memoryUsage; }
    public void setLatencyMs(long latencyMs) { this.latencyMs = latencyMs; }
    public void setErrorCount(int errorCount) { this.errorCount = errorCount; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public Long getId() { return id; }
    public String getServiceId() { return serviceId; }
    public String getServiceName() { return serviceName; }
    public double getCpuUsage() { return cpuUsage; }
    public double getMemoryUsage() { return memoryUsage; }
    public long getLatencyMs() { return latencyMs; }
    public int getErrorCount() { return errorCount; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}