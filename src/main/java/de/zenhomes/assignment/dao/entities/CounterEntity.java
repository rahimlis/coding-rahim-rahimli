package de.zenhomes.assignment.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "counter")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CounterEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "counter")
    private Set<RecordEntity> records;

    @ManyToOne
    @JoinColumn(name = "village_id", nullable = false)
    private VillageEntity village;

}
