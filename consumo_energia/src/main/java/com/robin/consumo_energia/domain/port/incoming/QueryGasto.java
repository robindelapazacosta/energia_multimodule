package com.robin.consumo_energia.domain.port.incoming;


import com.robin.common_dto.dto.DtoGastoDiario;
import com.robin.common_dto.dto.DtoGastoTotalUEB;
import com.robin.common_dto.dto.DtoConsumoUEBMes;

import java.util.List;

public interface QueryGasto {

    public List<DtoGastoDiario> listGastoDiario(Integer idueb, Integer idcontador, Integer anno, Integer mes);
    public List<DtoGastoTotalUEB> listGastoTotalUeb(Integer anno, Integer mes);
    public List<DtoConsumoUEBMes> listConsumoMensualUeb(int anno, int idueb);


}
