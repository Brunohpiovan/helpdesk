package com.bruno.helpdesk.services;

import com.bruno.helpdesk.domain.Chamado;
import com.bruno.helpdesk.repositories.ChamadoRepository;
import com.bruno.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    public Chamado findbyId(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto nao encontrado! ID: "+ id));
    }
}
