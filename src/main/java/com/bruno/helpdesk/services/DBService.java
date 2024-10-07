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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Bean
    public void instanciaDB(){
        Tecnico tec1 = new Tecnico(null,"Valdir cezar","545.974.290-00","Valdir@gmail.com",encoder.encode("123456"));
        tec1.addPerfil(Perfil.ADMIN);
        Tecnico tec2 = new Tecnico(null,"Pedro silva","682.025.280-38","Pedro@gmail.com",encoder.encode("123456"));
        tec1.addPerfil(Perfil.ADMIN);
        Tecnico tec3 = new Tecnico(null,"Luan souza","620.137.020-00","Luan@gmail.com",encoder.encode("123456"));
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null,"Linos torvo","667.367.440-75","linos@mail.com",encoder.encode("123456"));
        Cliente cli2 = new Cliente(null,"Mario araujo","206.711.750-50","Mario@mail.com",encoder.encode("123456"));
        Cliente cli3 = new Cliente(null,"Rogerio oliveira","749.600.380-80","Rogerio@mail.com",encoder.encode("123456"));
        Cliente cli4 = new Cliente(null,"Maria de Lurder","785.259.550-27","Lurder@mail.com",encoder.encode("123456"));

        Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO,"Chamado 01","Primeiro chamado",tec1,cli1);
        Chamado ch2 = new Chamado(null, Prioridade.ALTA, Status.ENCERRADO,"Chamado 02","Segundo chamado",tec2,cli2);
        Chamado ch3 = new Chamado(null, Prioridade.ALTA, Status.ABERTO,"Chamado 03","Terceiro chamado",tec2,cli2);
        Chamado ch4 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO,"Chamado 04","Quarto chamado",tec1,cli3);
        Chamado ch5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO,"Chamado 05","Quinto chamado",tec1,cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1,tec2,tec3));
        clienteRepository.saveAll(Arrays.asList(cli1,cli2,cli3,cli4));
        chamadoRepository.saveAll(Arrays.asList(ch1,ch2,ch3,ch4,ch5));

    }

}
