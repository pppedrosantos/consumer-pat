package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
@Embeddable
public class Contact {

    @Column(nullable = false)
    private String mobilePhoneNumber;

    @Column
    private String residencePhoneNumber;

    @Column
    private String phoneNumber;

    @Column(nullable = false)
    @Email
    private String email;


}
