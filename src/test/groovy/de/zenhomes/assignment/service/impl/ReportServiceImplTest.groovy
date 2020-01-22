package de.zenhomes.assignment.service.impl

import de.zenhomes.assignment.dao.entities.CounterEntity
import de.zenhomes.assignment.dao.entities.RecordEntity
import de.zenhomes.assignment.dao.entities.VillageEntity
import de.zenhomes.assignment.dao.repos.RecordRepository
import de.zenhomes.assignment.model.TimeFrame
import de.zenhomes.assignment.model.VillageConsumptionDto
import de.zenhomes.assignment.service.ReportService
import spock.lang.Specification

import java.time.LocalDateTime

class ReportServiceImplTest extends Specification {
    private ReportService reportService
    private RecordRepository recordRepository

    void setup() {
        recordRepository = Mock()
        reportService = new ReportServiceImpl(recordRepository)
    }

    def "test GenerateConsumptionReport"() {
        given:
        def village1 = new VillageEntity(1L, "First Village", new HashSet<>());
        def village2 = new VillageEntity(2L, "Second Village", new HashSet<>());
        def counter1 = new CounterEntity(1L, new HashSet<>(), village1);
        def counter2 = new CounterEntity(2L, new HashSet<>(), village2);
        def counter1record1 = new RecordEntity(1L, 200D, counter1, LocalDateTime.now());
        def counter1Record2 = new RecordEntity(2L, 500D, counter1, LocalDateTime.now());
        def counter2Record1 = new RecordEntity(3L, 100D, counter2, LocalDateTime.now());
        def counter2Record2 = new RecordEntity(4L, 300D, counter2, LocalDateTime.now());

        def records = [counter1record1, counter1Record2, counter2Record1, counter2Record2].toList()
        recordRepository.findAllByCreatedAtAfter(_ as LocalDateTime) >> records
        when:
        def result = reportService.generateConsumptionReport(TimeFrame.LAST_DAY)

        then:
        result.villages.containsAll([new VillageConsumptionDto("First Village", 700D),
                                     new VillageConsumptionDto("Second Village", 400D),].toList())
    }


    def "test GenerateConsumptionReport empty records case"() {
        given:
        def records = [].toList()
        recordRepository.findAllByCreatedAtAfter(_ as LocalDateTime) >> records
        when:
        def result = reportService.generateConsumptionReport(TimeFrame.LAST_DAY)

        then:
        result.villages.isEmpty()
    }

    def "test GenerateConsumptionReport many counters case"() {
        given:
        def village1 = new VillageEntity(1L, "First Village", new HashSet<>());
        def village2 = new VillageEntity(2L, "Second Village", new HashSet<>());
        def counter1 = new CounterEntity(1L, new HashSet<>(), village1);
        def counter2 = new CounterEntity(2L, new HashSet<>(), village2);
        def counter3 = new CounterEntity(3L, new HashSet<>(), village2);
        def counter1record1 = new RecordEntity(1L, 200D, counter1, LocalDateTime.now());
        def counter1Record2 = new RecordEntity(2L, 500D, counter1, LocalDateTime.now());
        def counter2Record1 = new RecordEntity(3L, 100D, counter2, LocalDateTime.now());
        def counter2Record2 = new RecordEntity(4L, 300D, counter2, LocalDateTime.now());
        def counter3Record1 = new RecordEntity(5L, 150D, counter3, LocalDateTime.now());

        def records = [counter1record1, counter1Record2, counter2Record1, counter2Record2, counter3Record1].toList()

        recordRepository.findAllByCreatedAtAfter(_ as LocalDateTime) >> records
        when:
        def result = reportService.generateConsumptionReport(TimeFrame.LAST_DAY)

        then:
        result.villages.containsAll([new VillageConsumptionDto("First Village", 700D),
                                     new VillageConsumptionDto("Second Village", 550D),].toList())
    }
}
