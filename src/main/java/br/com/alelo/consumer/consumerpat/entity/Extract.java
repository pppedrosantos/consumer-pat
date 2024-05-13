package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private int establishmentNameId;

    @Column
    private String establishmentName;

    @Column
    private String productDescription;

    @Column
    private Date dateBuy;

    @Column
    private String cardNumber;

    @Column
    private double amount;

}
