package com.creditas.util;

import java.time.LocalDate;
import java.time.Period;

public class Utils {

    public static int calcularIdade(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            throw new IllegalArgumentException("A data de nascimento não pode ser nula.");
        }

        LocalDate dataAtual = LocalDate.now();

        Period periodo = Period.between(dataNascimento, dataAtual);

        return periodo.getYears();
    }

}
