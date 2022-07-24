package com.example.notification.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "notification"
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notification {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_id_sequence"
    )
    @SequenceGenerator(
            name = "notification_id_sequence",
            sequenceName = "notification_id_sequence"
    )
    @Column(name = "notification_id")
    private Long id;

    private Long toCustomerId;

    private String toCustomerEmail;

    private String message;

    private String sender;

    private LocalDateTime sentAt;
}
