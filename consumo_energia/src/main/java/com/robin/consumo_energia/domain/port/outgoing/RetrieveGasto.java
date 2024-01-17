package com.robin.consumo_energia.domain.port.outgoing;


import com.robin.common_dto.dto.DtoGastoDiario;
import com.robin.common_dto.dto.DtoGastoTotalUEB;
import com.robin.common_dto.dto.DtoConsumoUEBMes;
import com.robin.consumo_energia.domain.model.Lectura;

import java.util.List;

public interface RetrieveGasto {

    public List<DtoGastoDiario> listarGastoDiario(Integer idueb, Integer idcontador, int anno, int mes);
    public Lectura getUltimaLectura(Integer idcontador);

    public List<DtoGastoTotalUEB> gastoPorUEB(Integer anno, Integer mes);
    public List<DtoConsumoUEBMes>   listConsumoMensualUeb(int anno, int idueb);

}
