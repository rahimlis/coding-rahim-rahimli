package de.zenhomes.assignment.dao.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "village")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VillageEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "village")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<CounterEntity> counters;
}
