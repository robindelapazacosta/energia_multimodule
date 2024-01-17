package com.robin.common_dto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DtoGastoDiario {
    public String diadelasema;
    public String fecha;
    public Integer lecturapico;
    public Integer gastopico;
    public Integer lecturadia;
    public Integer gastodia;
    public Integer lecturamadrugada;
    public Integer gastomadrugada;
    public Integer lecturagral;
    public Integer consumototal;



}
