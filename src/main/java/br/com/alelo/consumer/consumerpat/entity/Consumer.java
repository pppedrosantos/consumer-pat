package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String documentNumber;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Valid
    @Embedded
    private Address address;

    @Valid
    @Embedded
    private Contact contact;

    @Valid
    @Embedded
    private CardType cardType;

}
