package com.robin.common_dto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DtoConsumoUEBMes {

    private int idueb;
    private int mes;
    private Long consumo;
}
