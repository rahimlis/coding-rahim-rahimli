package de.zenhomes.assignment.dao.repos;

import com.google.common.collect.Lists;
import de.zenhomes.assignment.dao.entities.CounterEntity;
import de.zenhomes.assignment.dao.entities.RecordEntity;
import de.zenhomes.assignment.dao.entities.VillageEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecordRepositoryTest {

    @Autowired
    private RecordRepository repository;

    @Test
    public void findAllByCreatedAtAfter() {
        //setup
        var village = new VillageEntity(1L, "name", new HashSet<>());
        var counter = new CounterEntity(1L, new HashSet<>(), village);
        var recordNow = new RecordEntity(1L, 200D, counter, LocalDateTime.now());
        var recordYesterday = new RecordEntity(2L, 200D, counter, LocalDateTime.now().minusDays(1));
        var record59MinutesAgo = new RecordEntity(3L, 200D, counter, LocalDateTime.now().minusMinutes(59));
        var record60MinutesAgo = new RecordEntity(4L, 200D, counter, LocalDateTime.now().minusMinutes(60));
        var expectedAllRecords = List.of(recordNow, recordYesterday, record59MinutesAgo, record60MinutesAgo);
        repository.saveAll(expectedAllRecords);

        //when:
        var result = repository.findAllByCreatedAtAfter(LocalDateTime.now().minusHours(1));
        var actualAllRecords = Lists.newArrayList(repository.findAll());

        //then:
        assertEquals(2, result.size());
        assertEquals(4, actualAllRecords.size());
        assertTrue(expectedAllRecords.containsAll(actualAllRecords));
        assertTrue(result.containsAll(List.of(record59MinutesAgo, recordNow)));
    }
}