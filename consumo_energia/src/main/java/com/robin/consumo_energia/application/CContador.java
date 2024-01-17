package com.robin.consumo_energia.application;


import com.robin.consumo_energia.domain.model.Contador;
import com.robin.consumo_energia.domain.port.incoming.RegistrarContador;
import com.robin.consumo_energia.domain.port.outgoing.RetrieveContadores;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contadores")
public class CContador
{
    private final RegistrarContador registrarContador;
    private final RetrieveContadores retrieveContadores;

    public CContador(RegistrarContador registrarContador, RetrieveContadores retrieveContadores) {
        this.registrarContador = registrarContador;
        this.retrieveContadores = retrieveContadores;
    }

    @PostMapping("/registrar")
    public void registrarContador(@RequestBody Contador contador)
    {
        registrarContador.registrar(contador);
    }


    @GetMapping("listar/{idueb}")
    public List<Contador> listarContadores(@PathVariable("idueb") Integer idueb)
    {
       return  retrieveContadores.listarContadores(idueb);
    }

}
