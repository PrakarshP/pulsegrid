package com.pulsegrid.metric_aggregator;

import java.time.LocalDateTime;

public class MetricPayload {
    private String serviceId;
    private String serviceName;
    private double cpuUsage;
    private double memoryUsage;
    private long latencyMs;
    private int errorCount;
    private LocalDateTime timestamp;

    public String getServiceId() { return serviceId; }
    public String getServiceName() { return serviceName; }
    public double getCpuUsage() { return cpuUsage; }
    public double getMemoryUsage() { return memoryUsage; }
    public long getLatencyMs() { return latencyMs; }
    public int getErrorCount() { return errorCount; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public void setServiceId(String serviceId) { this.serviceId = serviceId; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    public void setCpuUsage(double cpuUsage) { this.cpuUsage = cpuUsage; }
    public void setMemoryUsage(double memoryUsage) { this.memoryUsage = memoryUsage; }
    public void setLatencyMs(long latencyMs) { this.latencyMs = latencyMs; }
    public void setErrorCount(int errorCount) { this.errorCount = errorCount; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}