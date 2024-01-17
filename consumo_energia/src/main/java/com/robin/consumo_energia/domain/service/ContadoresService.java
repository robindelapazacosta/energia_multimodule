package com.robin.consumo_energia.domain.service;

import com.robin.consumo_energia.domain.model.Contador;
import com.robin.consumo_energia.domain.port.incoming.QueryContadores;
import com.robin.consumo_energia.domain.port.incoming.RegistrarContador;
import com.robin.consumo_energia.domain.port.outgoing.PersistContador;
import com.robin.consumo_energia.domain.port.outgoing.RetrieveContadores;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContadoresService implements RegistrarContador, QueryContadores {


    private PersistContador persistContador;
    private RetrieveContadores retrieveContadores;

    public ContadoresService(PersistContador persistContador, RetrieveContadores retrieveContadores) {
        this.persistContador = persistContador;
        this.retrieveContadores = retrieveContadores;
    }

    @Override
    public void registrar(Contador contador) {

        //Verificar si la UEB existe (con SAGAS y sin Sagas)
        persistContador.registrar(contador);
    }

    @Override
    public List<Contador> listarContadores(Integer idueb) {
        return retrieveContadores.listarContadores(idueb);
    }
}
