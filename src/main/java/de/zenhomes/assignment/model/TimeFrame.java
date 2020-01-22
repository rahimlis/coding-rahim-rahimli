package de.zenhomes.assignment.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public enum TimeFrame {

    LAST_DAY("24H", LocalDateTime.now().minusDays(1));

    private final String slug;
    private final LocalDateTime instant;

    TimeFrame(String slug, LocalDateTime instant) {
        this.slug = slug;
        this.instant = instant;
    }

    @JsonCreator
    public static TimeFrame forValue(String value) {
        return Stream.of(values())
                .filter(it -> it.slug.equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public LocalDateTime getInstant() {
        return instant;
    }
}
