package com.robin.common_dto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DtoMesPlan {

    private int idueb;
    private String nombreueb;
    private int mes;
    private Long plan;
    private Long plan_ajustado;

    public DtoMesPlan(int idueb, int mes, Long plan, Long plan_ajustado) {
        this.idueb = idueb;
        this.mes = mes;
        this.plan = plan;
        this.plan_ajustado = plan_ajustado;
    }
}
