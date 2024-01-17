package com.robin.consumo_energia.domain.port.incoming;


import com.robin.consumo_energia.domain.model.Contador;

public interface RegistrarContador {

    public void registrar(Contador contador);
}
