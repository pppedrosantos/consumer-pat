package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class CardType {

    @Column(unique = true)
    private String foodCardNumber;

    @Column
    private double foodCardBalance;

    @Column(unique = true)
    private String fuelCardNumber;

    @Column
    private double fuelCardBalance;

    @Column(unique = true)
    private String drugstoreNumber;

    @Column
    private double drugstoreCardBalance;

}
