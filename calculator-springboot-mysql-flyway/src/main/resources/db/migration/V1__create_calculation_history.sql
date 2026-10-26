CREATE TABLE calculation_history (
    id BIGINT NOT NULL AUTO_INCREMENT,
    expression VARCHAR(500) NOT NULL,
    result DECIMAL(30, 10) NOT NULL,
    calculated_at VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);