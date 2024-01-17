package com.robin.consumo_energia.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contador {


    private Integer idcontador;
    private String nombre_area;

    private Integer idueb;

}
