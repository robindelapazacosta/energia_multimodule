package com.robin.consumo_energia.domain.port.outgoing;


import com.robin.consumo_energia.domain.model.Lectura;

public interface PersistLectura {

    public void registrar(Lectura lectura);
}
