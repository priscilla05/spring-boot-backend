package com.devpri.cursomc.services;

import com.devpri.cursomc.domain.Cliente;
import com.devpri.cursomc.repositories.ClienteRepository;
import com.devpri.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente buscar(Integer id) {
        Optional<Cliente> objeto = repository.findById(id);
        return objeto.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id +
                ", Tipo: " + Cliente.class.getName()));
    }
}



