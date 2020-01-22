package de.zenhomes.assignment.service.impl

import de.zenhomes.assignment.dao.entities.CounterEntity
import de.zenhomes.assignment.dao.entities.VillageEntity
import de.zenhomes.assignment.dao.repos.CounterRepository
import de.zenhomes.assignment.model.CounterRecordDto
import spock.lang.Specification

class CounterServiceImplTest extends Specification {

    private CounterRepository counterRepository
    private CounterServiceImpl counterService

    void setup() {
        counterRepository = Mock()
        counterService = new CounterServiceImpl(counterRepository)
    }

    def "test that saveCounterRecord saves proper entity"() {
        given:
        def dto = new CounterRecordDto(1, 200)
        def village = new VillageEntity(1, "Village", Set.of())
        def existingCounter = new CounterEntity(1, [].toSet(), village)
        counterRepository.findById(1) >> Optional.of(existingCounter)

        when:
        counterService.saveCounterRecord(dto)

        then:
        1 * counterRepository.save({ counter ->
            counter.id == 1
            counter.records[0].amount == 200
        } as CounterEntity)
    }

    def "test that saveCounterRecord throws exception if record not found"() {
        given:
        def dto = new CounterRecordDto(1, 200)
        counterRepository.findById(1) >> Optional.empty()

        when:
        counterService.saveCounterRecord(dto)

        then:
        thrown(NoSuchElementException)
    }

    def "test that GetCounterInfo returns proper model"() {
        given:
        def village = new VillageEntity(1, "Village", Set.of())
        def existingCounter = new CounterEntity(1, [].toSet(), village)
        counterRepository.findById(1) >> Optional.of(existingCounter)

        when:
        def counterInfo = counterService.getCounterInfo(1)

        then:
        counterInfo.counterId == existingCounter.id
        counterInfo.villageName == existingCounter.village.name
    }


    def "test that GetCounterInfo throws exception if record not found"() {
        given:
        counterRepository.findById(1) >> Optional.empty()

        when:
        counterService.getCounterInfo(1)

        then:
        thrown(NoSuchElementException)
    }
}
