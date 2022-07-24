package com.example.customer.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(
        name = "customer"
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence"
    )
    @Column(name = "customer_id")
    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}
