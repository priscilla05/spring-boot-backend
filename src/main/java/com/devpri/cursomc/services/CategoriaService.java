package com.devpri.cursomc.services;

import com.devpri.cursomc.domain.Categoria;
import com.devpri.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    public Categoria buscar(Integer id){
        Optional<Categoria> objeto = categoriaRepository.findById(id);
        return objeto.orElse(null);

    }
}
