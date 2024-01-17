package com.robin.consumo_energia.domain.port.incoming;


import com.robin.consumo_energia.domain.model.Contador;

import java.util.List;

public interface QueryContadores {

    /*Si no se especifica el id de la UEb se muestran todos los contadores*/
    public List<Contador> listarContadores(Integer idueb);
}
