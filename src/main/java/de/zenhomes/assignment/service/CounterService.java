package de.zenhomes.assignment.service;

import de.zenhomes.assignment.model.CounterInfoDto;
import de.zenhomes.assignment.model.CounterRecordDto;

public interface CounterService {

    void saveCounterRecord(CounterRecordDto dto);

    CounterInfoDto getCounterInfo(Long counterId);
}