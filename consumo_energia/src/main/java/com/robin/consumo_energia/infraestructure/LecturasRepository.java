package com.robin.consumo_energia.infraestructure;

import com.robin.common_dto.dto.DtoGastoDiario;
import com.robin.common_dto.dto.DtoGastoTotalUEB;
import com.robin.common_dto.dto.DtoConsumoUEBMes;
import com.robin.consumo_energia.domain.model.Lectura;
import com.robin.consumo_energia.domain.port.outgoing.PersistLectura;
import com.robin.consumo_energia.domain.port.outgoing.RetrieveGasto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturasRepository implements PersistLectura, RetrieveGasto {


    @Autowired
    private com.robin.consumo_energia.infraestructure.JdbcLecturasRepository jdbcLecturasRepository;


    @Override
    public void registrar(Lectura lectura) {

        jdbcLecturasRepository.save(lectura);
    }


    @Override
    public List<DtoGastoDiario> listarGastoDiario(Integer idueb, Integer idcontador, int anno, int mes) {
       if(idueb==0 && idcontador==0)
       {
           return jdbcLecturasRepository.listGastoDiarioEmpresa(anno, mes); //return gasto total de la emoresa por dias
       }
       else if(idcontador!=0) // mostrar el gasto diario para ese contador
       {
           return jdbcLecturasRepository.listGastoDiarioContador(idcontador, anno,mes);
       }
       else //mostrar el gasto diario de la ueb
       {
           return jdbcLecturasRepository.listGastoDiarioUEB(idueb, anno, mes);
       }

    }

    @Override
    public Lectura getUltimaLectura(Integer idcontador) {
        return jdbcLecturasRepository.getUltimaLectura(idcontador);
    }

    @Override
    public List<DtoGastoTotalUEB> gastoPorUEB(Integer anno, Integer mes)
    {
        return jdbcLecturasRepository.listGastoTotalUeb(anno, mes);
    }

    @Override
    public List<DtoConsumoUEBMes> listConsumoMensualUeb(int anno, int idueb) {
        return jdbcLecturasRepository.listConsumoMensualUeb(anno,idueb);
    }


}
