package com.robin.consumo_energia.application;

import com.robin.common_dto.dto.DtoUeb;
import com.robin.consumo_energia.domain.service.RemoteCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CLecturastemp {


    @Autowired
   private RemoteCallService remoteCallService;


    private Map<Integer, DtoUeb> mapUebs()
    {
        List<DtoUeb> listUeb= remoteCallService.getListUeb();

        //Convertirla a la map con clave el id
        Map<Integer,DtoUeb> mapUebs= new HashMap<>();
        for(DtoUeb dtoUeb:listUeb)
        {
            mapUebs.put(dtoUeb.getIdueb(),dtoUeb);

        }

        return mapUebs;

    }




}
