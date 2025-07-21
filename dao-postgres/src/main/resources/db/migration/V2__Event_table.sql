CREATE TABLE IF NOT EXISTS event
(
    id         SERIAL       NOT NULL,
    name       VARCHAR(255) NOT NULL,
    start_time TIMESTAMP    NOT NULL,
    end_time   TIMESTAMP    NOT NULL,
    PRIMARY KEY (id)
);
