package de.zenhomes.assignment.dao.entities;

import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "counter", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RecordEntity> records;

    @ManyToOne
    @JoinColumn(name = "village_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private VillageEntity village;

}
