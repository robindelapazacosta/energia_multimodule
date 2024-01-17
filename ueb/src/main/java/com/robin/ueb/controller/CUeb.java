package com.robin.ueb.controller;

import com.robin.ueb.entidades.Ueb;
import com.robin.ueb.service.UebService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin("http://localhost:8090")
@RestController
@RequestMapping("uebs/")
public class CUeb {

    @Autowired
    private UebService uebService;

    //@CrossOrigin("http://localhost:4200")
    @PostMapping("adicionar")
    public void AdicionarUeb(@RequestBody Ueb ueb)
    {
        uebService.addOrUpdateUeb(ueb);
    }


    //@CrossOrigin("http://localhost:4200")
    @PostMapping("actualizar")
    public void ActualizarUeb(@RequestBody Ueb ueb)
    {
        uebService.addOrUpdateUeb(ueb);
    }


    @CircuitBreaker(name = "uebsCB",fallbackMethod = "listarUebsFallBack")
    //@CrossOrigin("http://localhost:4200")
    @GetMapping("listar")
    public ResponseEntity<List<Ueb>>  listarUebs()
    {
        return   ResponseEntity.status(HttpStatus.OK).body(uebService.listarUebs());
    }


    public ResponseEntity<List<Ueb>>  listarUebsFallBack(RuntimeException ex)
    {
        List<Ueb> luebs= new ArrayList<Ueb>();
        Ueb ueb= new Ueb(-1l,"Ueb no encontrada");
        luebs.add(ueb);

        return   ResponseEntity.status(HttpStatus.OK).body(luebs);
    }


    @CircuitBreaker(name = "uebsCB",fallbackMethod = "getUebFallBack")
    //@CrossOrigin("http://localhost:4200")
    @GetMapping("get/{idueb}")
    public ResponseEntity<Ueb>  getUeb(@PathVariable("idueb") Long idueb)
    {
        return ResponseEntity.status(HttpStatus.OK).body(uebService.getUeb(idueb));
    }


    public ResponseEntity<Ueb>  getUebFallBack(@PathVariable("idueb") Long idueb,RuntimeException ex) {

       Ueb ueb= new Ueb();
       ueb.setNombreueb("Ueb no encontrada");
       return ResponseEntity.status(HttpStatus.OK).body(ueb);
    }

    //@CrossOrigin("http://localhost:4200")
    @DeleteMapping("eliminar/{idueb}")
    public ResponseEntity<String> eliminarUeb(@PathVariable("idueb") Long idueb)
    {
        try {
            //Aqui necesito un orquestador para o eliminar hasta que se hallan quitado
            //los planes, los gasto y la UEB


            uebService.eliminarUeb(idueb);
            return ResponseEntity.status(HttpStatus.OK).body("Se eliminó correctamente");
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

    }
}
