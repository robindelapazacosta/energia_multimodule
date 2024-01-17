package com.robin.plan.httpService;

import com.robin.common_dto.dto.DtoUeb;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="feignClient",url="localhost:8082/uebs/")
public interface RemoteCallService {

    @GetMapping("listar")
    public List<DtoUeb> getListUeb();


    @GetMapping("get/{idueb}")
    public DtoUeb getUeb(@PathVariable("idueb") Integer idueb);
}
