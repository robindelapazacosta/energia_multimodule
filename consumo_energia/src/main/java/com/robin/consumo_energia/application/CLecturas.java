package com.robin.consumo_energia.application;


import com.robin.common_dto.dto.DtoGastoDiario;
import com.robin.common_dto.dto.DtoGastoTotalUEB;
import com.robin.common_dto.dto.DtoUeb;
import com.robin.consumo_energia.domain.constantes.Message;
import com.robin.common_dto.dto.DtoConsumoUEBMes;
import com.robin.consumo_energia.domain.model.Lectura;
import com.robin.consumo_energia.domain.port.incoming.QueryGasto;
import com.robin.consumo_energia.domain.port.incoming.RegistrarLectura;
import com.robin.consumo_energia.domain.service.RemoteCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("consumo")
public class CLecturas {
    private final RegistrarLectura registrarLectura;
    private final QueryGasto queryGasto;

    @Autowired
    RemoteCallService remoteCallService;

    public CLecturas(RegistrarLectura registrarLectura, QueryGasto queryGasto) {
        this.registrarLectura = registrarLectura;
        this.queryGasto = queryGasto;
    }

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

    //Completar
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarLectura(@RequestBody Lectura lectura)
    {
        try {
            registrarLectura.registrar(lectura);
            return  ResponseEntity.status(HttpStatus.CREATED).body(Message.CreatedOK);
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }


    /*Lecturas y gasto diario dia, pico y madrugada de la empresa, uen o contador específico */
    /*Si no se quiere especificar el idcontador o idueb se pone valor 0*/
    @GetMapping("/gastodiario/{anno}/{mes}/{idueb}/{idcontador}")
    public List<DtoGastoDiario> listGastoDiario(@PathVariable("anno") Integer anno,
                                                @PathVariable("mes") Integer mes,
                                                @PathVariable("idueb") Integer idueb,
                                                @PathVariable("idcontador") Integer idcontador)
    {


        return queryGasto.listGastoDiario(idueb,idcontador, anno,mes);
    }



    /*Gasto total por UEB dado el mes y el año*/
    @GetMapping("/gastodelmes_porueb/{anno}/{mes}")
    public List<DtoGastoTotalUEB> listGastoPorUeb(@PathVariable("anno") Integer anno,
                                                  @PathVariable("mes") Integer mes) throws IOException {
        List<DtoGastoTotalUEB> lgastoueb= queryGasto.listGastoTotalUeb(anno,mes);

        Map<Integer, DtoUeb> mapUebs=this.mapUebs();

        /*Concatenar */
        for(DtoGastoTotalUEB gasto:lgastoueb)
        {
            gasto.setNombreueb(mapUebs.get(gasto.getIdueb()).getNombreueb());
        }

        return lgastoueb;
    }

    /*Gasto mensual UEB dado el año*/
    @GetMapping("/gasto_mensual/{anno}/{idueb}")
    public ResponseEntity<List<DtoConsumoUEBMes>> listConsumoMensualUeb(@PathVariable("anno") int anno,
                                                                        @PathVariable("idueb") int idueb)
    {
        List<DtoConsumoUEBMes> lconsumo= queryGasto.listConsumoMensualUeb(anno,idueb);

        return ResponseEntity.status(HttpStatus.OK).body(lconsumo);
    }



}
