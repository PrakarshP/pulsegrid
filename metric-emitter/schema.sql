CREATE DATABASE IF NOT EXISTS pulsegrid;

USE pulsegrid;

CREATE TABLE IF NOT EXISTS service_metrics (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    service_id    VARCHAR(100)   NOT NULL,
    service_name  VARCHAR(100)   NOT NULL,
    cpu_usage     DOUBLE         NOT NULL,
    memory_usage  DOUBLE         NOT NULL,
    latency_ms    BIGINT         NOT NULL,
    error_count   INT            NOT NULL,
    timestamp     DATETIME       NOT NULL,
    created_at    DATETIME       DEFAULT CURRENT_TIMESTAMP
);