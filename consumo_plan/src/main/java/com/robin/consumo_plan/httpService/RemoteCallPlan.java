package com.robin.consumo_plan.httpService;

import com.robin.common_dto.dto.DtoMesPlan;
import com.robin.consumo_plan.dto.DtoCumplimPlan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(name="feignClient",url="localhost:8081/plan/")
@FeignClient(name = "plan-service")
public interface RemoteCallPlan {


    

    @GetMapping("plan/planpormeses/{anno}/{idueb}")
    public List<DtoMesPlan> getPlanMensualUeb(@PathVariable("anno") int anno,
                                       @PathVariable("idueb") int idueb);



}
