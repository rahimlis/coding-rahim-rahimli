package de.zenhomes.assignment.dao.repos;

import de.zenhomes.assignment.dao.entities.RecordEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RecordRepository extends CrudRepository<RecordEntity, Long> {
    List<RecordEntity> findAllByCreatedAtAfter(LocalDateTime startingDate);
}
