package com.robin.consumo_plan.httpService;

import com.robin.common_dto.dto.DtoConsumoUEBMes;
import com.robin.common_dto.dto.DtoPlanMensualUeb;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(name = "feignClient1", url = "http://localhost:8084/lecturas/")
@FeignClient(name="consumo-service")
public interface RemoteCallConsumo {

    /*Devuelve el consumo por mese de la UEB*/


    @GetMapping("lecturas/consumo_mensual/{anno}/{idueb}")
    public List<DtoConsumoUEBMes> consumoPorMesesUeb(@PathVariable("anno") int anno,
                                                     @PathVariable("idueb") int mes);

}
