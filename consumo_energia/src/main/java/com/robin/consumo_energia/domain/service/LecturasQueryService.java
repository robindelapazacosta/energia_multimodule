package com.robin.consumo_energia.domain.service;


import com.robin.common_dto.dto.DtoGastoDiario;
import com.robin.common_dto.dto.DtoGastoTotalUEB;
import com.robin.common_dto.dto.DtoConsumoUEBMes;
import com.robin.consumo_energia.domain.port.incoming.QueryGasto;
import com.robin.consumo_energia.domain.port.outgoing.RetrieveGasto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturasQueryService implements QueryGasto {

    private RetrieveGasto retrieveGasto;

    public LecturasQueryService(RetrieveGasto retrieveGasto) {
        this.retrieveGasto = retrieveGasto;
    }

    @Override
    public List<DtoGastoDiario> listGastoDiario(Integer idueb, Integer idcontador, Integer anno, Integer mes) {
        return retrieveGasto.listarGastoDiario(idueb,idcontador, anno, mes);
    }

    @Override
    public List<DtoGastoTotalUEB> listGastoTotalUeb(Integer anno, Integer mes) {
        return retrieveGasto.gastoPorUEB(anno,mes);
    }

    @Override
    public List<DtoConsumoUEBMes> listConsumoMensualUeb(int anno, int idueb) {
        return retrieveGasto.listConsumoMensualUeb(anno, idueb);
    }


}
