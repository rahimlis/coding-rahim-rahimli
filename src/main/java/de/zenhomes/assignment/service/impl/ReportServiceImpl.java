package de.zenhomes.assignment.service.impl;

import de.zenhomes.assignment.dao.entities.RecordEntity;
import de.zenhomes.assignment.dao.repos.RecordRepository;
import de.zenhomes.assignment.model.ConsumptionReportDto;
import de.zenhomes.assignment.model.TimeFrame;
import de.zenhomes.assignment.model.VillageConsumptionDto;
import de.zenhomes.assignment.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final RecordRepository recordRepository;

    public ReportServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public ConsumptionReportDto generateConsumptionReport(TimeFrame timeFrame) {
        var allRecords = recordRepository.findAllByCreatedAtAfter(timeFrame.getInstant());
        var allRecords2 = recordRepository.findAll();

        var villageSumMap = allRecords.stream()
                .collect(Collectors.groupingBy(r -> r.getCounter().getVillage(),
                        Collectors.summingDouble(RecordEntity::getAmount)));

        var villages = villageSumMap.entrySet()
                .stream()
                .map(e -> new VillageConsumptionDto(e.getKey().getName(), e.getValue()))
                .collect(Collectors.toList());

        return new ConsumptionReportDto(villages);
    }
}
