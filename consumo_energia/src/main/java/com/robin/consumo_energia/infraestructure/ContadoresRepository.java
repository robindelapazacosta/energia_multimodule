package com.robin.consumo_energia.infraestructure;

import com.robin.consumo_energia.domain.model.Contador;
import com.robin.consumo_energia.domain.port.outgoing.PersistContador;
import com.robin.consumo_energia.domain.port.outgoing.RetrieveContadores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContadoresRepository implements PersistContador, RetrieveContadores {

    @Autowired
    JdbcContadoresRepository jdbcContadoresRepository;

    @Override
    public void registrar(Contador contador) {
      jdbcContadoresRepository.save(contador);
    }

    @Override
    public List<Contador> listarContadores(Integer idueb) {

        return jdbcContadoresRepository.listContadores(idueb);
    }
}
