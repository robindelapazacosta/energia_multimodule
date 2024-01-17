package com.robin.consumo_energia.domain.port.outgoing;


import com.robin.consumo_energia.domain.model.Contador;

public interface PersistContador {

    public void registrar(Contador contador);
}
