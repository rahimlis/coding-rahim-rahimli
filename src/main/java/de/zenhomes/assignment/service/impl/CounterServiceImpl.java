package de.zenhomes.assignment.service.impl;

import de.zenhomes.assignment.dao.entities.RecordEntity;
import de.zenhomes.assignment.dao.repos.CounterRepository;
import de.zenhomes.assignment.model.CounterInfoDto;
import de.zenhomes.assignment.model.CounterRecordDto;
import de.zenhomes.assignment.service.CounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class CounterServiceImpl implements CounterService {

    private static final Logger logger = LoggerFactory.getLogger(CounterService.class);
    private final CounterRepository counterRepository;

    public CounterServiceImpl(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Override
    @Transactional
    public void saveCounterRecord(CounterRecordDto dto) {
        logger.debug("CounterServiceImpl.saveCounterRecord.start");
        var counter = counterRepository.findById(dto.getCounterId())
                .orElseThrow(NoSuchElementException::new);
        var record = new RecordEntity();
        record.setAmount(dto.getAmount());
        record.setCreatedAt(LocalDateTime.now());
        record.setCounter(counter);
        counter.getRecords().add(record);
        counterRepository.save(counter);

        logger.debug("CounterServiceImpl.saveCounterRecord.end");
    }

    @Override
    public CounterInfoDto getCounterInfo(Long counterId) {
        logger.debug("CounterServiceImpl.getCounterInfo.start");
        var counter = counterRepository.findById(counterId)
                .orElseThrow(NoSuchElementException::new);
        var counterInfo = new CounterInfoDto(counterId, counter.getVillage().getName());
        logger.debug("CounterServiceImpl.getCounterInfo.end");
        return counterInfo;
    }
}
