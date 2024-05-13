package br.com.alelo.consumer.consumerpat.dto.enums;

import lombok.Getter;


@Getter
public enum EstablishmentType {
    FOOD("Alimentação"),
    DRUGSTORE("Farmácia"),
    FUEL("Posto de Combustível");

    private final String description;

    EstablishmentType(String description) {
        this.description = description;
    }

}
