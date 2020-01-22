package de.zenhomes.assignment.dao.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "record")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "counter_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CounterEntity counter;


    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}
