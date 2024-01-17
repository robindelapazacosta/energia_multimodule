package com.robin.consumo_plan.controller;

import com.robin.common_dto.dto.DtoConsumoUEBMes;
import com.robin.common_dto.dto.DtoMesPlan;
import com.robin.consumo_plan.dto.DtoCumplimPlan;
import com.robin.consumo_plan.httpService.RemoteCallConsumo;
import com.robin.consumo_plan.httpService.RemoteCallPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("consumo_plan")
public class CConsumoPlan {

    @Autowired
    private RemoteCallPlan remoteCallPlan;

    @Autowired
    private RemoteCallConsumo remoteCallConsumo;

    @GetMapping("uebpormes/{anno}/{idueb}")
    public ResponseEntity<List<DtoCumplimPlan>> getCumplimPlanMensualUeb(@PathVariable("anno") int anno,
                                                                         @PathVariable("idueb") int idueb)
    {
        List<DtoCumplimPlan> lcumplim= new ArrayList<>();

        List<DtoMesPlan> lplanmensual= remoteCallPlan.getPlanMensualUeb(anno,idueb);
        List<DtoConsumoUEBMes> lconsumomensual= remoteCallConsumo.consumoPorMesesUeb(anno, idueb);

        Map<Integer, DtoConsumoUEBMes> mapconsumo= new HashMap<>();
        for (DtoConsumoUEBMes dto:
                lconsumomensual) {

            mapconsumo.put(dto.getMes(),dto);

        }


        for(DtoMesPlan dtoMesPlan: lplanmensual) {
            DtoCumplimPlan dtoCumplimPlan = new DtoCumplimPlan();
            dtoCumplimPlan.setAnno_omes(dtoMesPlan.getMes());
            dtoCumplimPlan.setPlan(dtoMesPlan.getPlan());
            dtoCumplimPlan.setPlan_ajustado(dtoMesPlan.getPlan_ajustado());

            DtoConsumoUEBMes dtoConsumo = mapconsumo.get(dtoMesPlan.getMes());

            dtoCumplimPlan.setConsumo(0l);
            if (dtoConsumo != null) {

                 if (dtoConsumo.getConsumo() != null) {
                    dtoCumplimPlan.setConsumo(dtoConsumo.getConsumo());

                }
                else
                {

                }

                lcumplim.add(dtoCumplimPlan);

            }
        }


        return  ResponseEntity.status(HttpStatus.OK).body(lcumplim);
    }

}
