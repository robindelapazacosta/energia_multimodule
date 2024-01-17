package com.robin.consumo_energia.domain.service;

import com.robin.consumo_energia.domain.model.Lectura;
import com.robin.consumo_energia.domain.port.incoming.RegistrarLectura;
import com.robin.consumo_energia.domain.port.outgoing.PersistLectura;
import com.robin.consumo_energia.domain.port.outgoing.RetrieveGasto;
import org.springframework.stereotype.Service;

@Service
public class LecturasCommandService implements RegistrarLectura {


    private PersistLectura persistLectura;

    private RetrieveGasto retrieveGasto;

    public LecturasCommandService(PersistLectura persistLectura, RetrieveGasto retrieveGasto) {
        this.persistLectura = persistLectura;
        this.retrieveGasto= retrieveGasto;
    }

    @Override
    public void registrar(Lectura lectura) throws Exception{

        /*Validar la lectura que sea la ultima y o haya mas ninguna el mismo dia*/
        Lectura ultimaLectura=   retrieveGasto.getUltimaLectura(lectura.getIdcontador());

        if(lectura.getFecha().after(ultimaLectura.getFecha())) {
            persistLectura.registrar(lectura);
        }
        else throw new Exception("La lectura no puede ser anterior a la última del contador");



    }
}
