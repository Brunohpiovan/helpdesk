package com.bruno.helpdesk.resources;


import com.bruno.helpdesk.domain.Chamado;
import com.bruno.helpdesk.domain.dtos.ChamadoDTO;
import com.bruno.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        Chamado obj = service.findbyId(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));

    }
}
