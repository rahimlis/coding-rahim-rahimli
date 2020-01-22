package de.zenhomes.assignment.dao.repos;

import de.zenhomes.assignment.dao.entities.CounterEntity;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<CounterEntity, Long> {
}
