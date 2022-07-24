package com.example.fraud.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "fraud"
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Fraud {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fraud_id_sequence"
    )
    @SequenceGenerator(
            name = "fraud_id_sequence",
            sequenceName = "fraud_id_sequence"
    )
    @Column(name = "fraud_id")
    private Long id;

    private Long customerId;

    private Boolean isFraudster;

    private LocalDateTime createdAt;
}
