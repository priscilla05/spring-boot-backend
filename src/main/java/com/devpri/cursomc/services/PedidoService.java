package com.devpri.cursomc.services;

import com.devpri.cursomc.domain.Pedido;
import com.devpri.cursomc.repositories.PedidoRepository;
import com.devpri.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscar(Integer id) {
        Optional<Pedido> objeto = pedidoRepository.findById(id);
        return objeto.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id +
                ", Tipo: " + Pedido.class.getName()));
    }
}
