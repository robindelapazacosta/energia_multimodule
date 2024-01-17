package com.robin.common_dto.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DtoPlanMensualUeb {

    Integer idueb;
    String nombreueb;
    Long plan;
    Long plan_ajustado;
    String detalles;


}
