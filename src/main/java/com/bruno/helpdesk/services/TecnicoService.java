package com.bruno.helpdesk.services;

import com.bruno.helpdesk.domain.Tecnico;
import com.bruno.helpdesk.repositories.TecnicoRepository;
import com.bruno.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: "+ id));
    }
}
