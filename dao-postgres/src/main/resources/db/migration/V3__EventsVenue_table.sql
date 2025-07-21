CREATE TABLE events_venues
(
    event_id BIGINT       NOT NULL,
    venue_id VARCHAR(255) NOT NULL,

    PRIMARY KEY (event_id, venue_id),
    FOREIGN KEY (event_id) REFERENCES event (id),
    FOREIGN KEY (venue_id) REFERENCES venue (reference_id)
);
