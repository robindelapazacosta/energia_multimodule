package com.robin.plan.controller;


import com.robin.common_dto.dto.DtoMesPlan;
import com.robin.common_dto.dto.DtoPlanMensualUeb;
import com.robin.common_dto.dto.DtoUeb;
import com.robin.plan.exception.UebNotFoundException;
import com.robin.plan.httpService.RemoteCallService;
import com.robin.plan.modelo.PlanMensualUeb;
import com.robin.plan.service.PlanMensualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("plan/")
public class ControllerPlan {

    @Autowired
    PlanMensualService planMensualService;

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

    @PostMapping("add_planmensualueb")
    public ResponseEntity<String> adicionarPlanMensualUeb(@RequestBody PlanMensualUeb plan)  {
        //Verificar si la UEB existe
        DtoUeb dtoUeb= remoteCallService.getUeb(plan.getIdueb());
        if(dtoUeb.getIdueb()==null) // Perfeccionar esto. Ver cual es la mejor forma de tratar la excepción en Angula, etc
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UebNotFoundException().getMessage());

        }
        else //Si existe
        {
            planMensualService.AdicionarPlan(plan);
            return ResponseEntity.status(HttpStatus.CREATED).body("Se adicionó correctamente");
        }
        //Sino ...

    }

    @GetMapping("planesueb/{anno}/{mes}")
    public ResponseEntity<List<DtoPlanMensualUeb>> listPlanMesPorUEB(
            @PathVariable("anno") Integer anno,
            @PathVariable("mes") Integer mes)
    {

       List<PlanMensualUeb> lplan=   planMensualService.listPlanMesPorUEB(anno,mes);
       List<DtoPlanMensualUeb> lplanDto= new ArrayList<>();

        Map<Integer, DtoUeb> uebsMap= this.mapUebs();

       for(PlanMensualUeb plan: lplan)
       {
           String nombreueb=   uebsMap.get(plan.getIdueb()).getNombreueb();

           lplanDto.add(new DtoPlanMensualUeb(plan.getIdueb() ,nombreueb,
                   plan.getPlan(),plan.getPlan_ajustado(),plan.getDetalles())
           );
       }

        return ResponseEntity.status(HttpStatus.CREATED).body(lplanDto);
    }

    @GetMapping("planpormeses/{anno}/{idueb}")
    public List<DtoMesPlan> planUebPorMeses(@PathVariable("anno") int anno,
                                                             @PathVariable("idueb") int idueb
                                                            )
    {
        List<DtoMesPlan> lplanpormes=planMensualService.planUebPorMeses(idueb,anno);
        Map<Integer, DtoUeb> uebsMap= this.mapUebs();

        for (DtoMesPlan dto: lplanpormes
             ) {
            dto.setNombreueb(uebsMap.get(dto.getIdueb()).getNombreueb());
            //dto.setNombreueb(remoteCallService.getUeb(idueb).getNombreueb());
        }

        return lplanpormes;
        //return ResponseEntity.status(HttpStatus.OK).body(lplanpormes);
    }

    //Implementar
    /*
    @GetMapping("getplan")
    public ResponseEntity<String> getPlanPorUEB(int anno, int mes, Long idueb)
    {

         return ResponseEntity.status(HttpStatus.OK).body("Texto temp del plan");
    }*/


}
