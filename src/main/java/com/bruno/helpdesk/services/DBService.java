package com.bruno.helpdesk.services;

import com.bruno.helpdesk.domain.Chamado;
import com.bruno.helpdesk.domain.Cliente;
import com.bruno.helpdesk.domain.Tecnico;
import com.bruno.helpdesk.domain.enums.Perfil;
import com.bruno.helpdesk.domain.enums.Prioridade;
import com.bruno.helpdesk.domain.enums.Status;
import com.bruno.helpdesk.repositories.ChamadoRepository;
import com.bruno.helpdesk.repositories.ClienteRepository;
import com.bruno.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Bean
    public void instanciaDB(){
        Tecnico tec1 = new Tecnico(null,"Valdir cezar","545.974.290-00","Valdir@gmail.com","123456");
        tec1.addPerfil(Perfil.ADMIN);
        Tecnico tec2 = new Tecnico(null,"Pedro silva","682.025.280-38","Pedro@gmail.com","1q2w3e");
        tec1.addPerfil(Perfil.ADMIN);
        Tecnico tec3 = new Tecnico(null,"Luan souza","620.137.020-00","Luan@gmail.com","918201");
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null,"Linos torvo","667.367.440-75","linos@mail.com","654321");

        Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO,"Chamado 01","Primeiro chamado",tec1,cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1,tec2,tec3));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(ch1));

    }

}
