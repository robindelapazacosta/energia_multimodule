package com.robin.consumo_energia.domain.port.outgoing;


import com.robin.consumo_energia.domain.model.Contador;

import java.util.List;

public interface RetrieveContadores {

    public List<Contador> listarContadores(Integer idueb);



}
