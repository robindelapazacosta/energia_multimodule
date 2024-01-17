package com.robin.consumo_energia.domain.port.incoming;


import com.robin.consumo_energia.domain.model.Lectura;

public interface RegistrarLectura {

    public void registrar(Lectura lectura) throws Exception;
}
