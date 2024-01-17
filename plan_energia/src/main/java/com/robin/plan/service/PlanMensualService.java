package com.robin.plan.service;

import com.robin.common_dto.dto.DtoMesPlan;
import com.robin.common_dto.dto.DtoPlanMensualUeb;
import com.robin.plan.modelo.PlanMensualUeb;
import com.robin.plan.repository.PlanMensualRepository;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PlanMensualService {

    @Autowired
    private PlanMensualRepository repository;

    public void AdicionarPlan(PlanMensualUeb planMensualUeb)
    {

        repository.save(planMensualUeb);
    }

    public List<DtoMesPlan> planUebPorMeses(int idueb,  int anno)
    {
        return repository.planUebPorMeses(idueb,anno);
    }

    public List<PlanMensualUeb> listPlanMesPorUEB(int anno, int mes)
    {
        return repository.listarPlanMesUebs(anno,mes);
    }


}
