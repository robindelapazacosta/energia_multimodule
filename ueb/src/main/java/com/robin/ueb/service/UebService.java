package com.robin.ueb.service;


import com.robin.ueb.entidades.Ueb;
import com.robin.ueb.repository.UebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UebService {

    @Autowired
    private UebRepository uebRepository;


    public void addOrUpdateUeb(Ueb ueb)
    {
        uebRepository.save(ueb);
    }

    //Qué pasa si por ejemplo hay un fallo en la BD
    public List<Ueb> listarUebs()
    {
        return  uebRepository.findAll();
    }




    public Ueb getUeb(Long id)
    {
        return uebRepository.findById(id).get();
    }

    public void eliminarUeb(Long id) throws Exception
    {
        if(uebRepository.findById(id).isPresent()) {
            uebRepository.deleteById(id);
        }
        else
        {
            throw new Exception("La Ueb no existe");
        }
    }


}
