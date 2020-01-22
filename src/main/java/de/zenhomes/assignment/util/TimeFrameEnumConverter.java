package de.zenhomes.assignment.util;

import de.zenhomes.assignment.model.TimeFrame;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TimeFrameEnumConverter implements Converter<String, TimeFrame> {
    @Override
    public TimeFrame convert(String value) {
        return TimeFrame.forValue(value);
    }
}