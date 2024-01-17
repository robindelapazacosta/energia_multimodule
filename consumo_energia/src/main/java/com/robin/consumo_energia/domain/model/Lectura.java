package com.robin.consumo_energia.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lectura {
    private Integer idcontador;
    private Date fecha;

    private  Integer pico;
    private Integer dia;
    private Integer madrugada;
    private Integer general;


}
