package com.devpri.cursomc.services;

import com.devpri.cursomc.domain.Categoria;
import com.devpri.cursomc.dto.CategoriaDTO;
import com.devpri.cursomc.repositories.CategoriaRepository;
import com.devpri.cursomc.services.exceptions.DataIntegrityException;
import com.devpri.cursomc.services.exceptions.ObjectNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id) {
        Optional<Categoria> objeto = categoriaRepository.findById(id);
        return objeto.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id +
                ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj){
        obj.setId(null);
        return categoriaRepository.save(obj);
    }

    public Categoria update(Categoria obj){
        find(obj.getId());
        return categoriaRepository.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            categoriaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e ){
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");

        }

    }

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();

    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction) ,orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO ojbDto){ // método auxiliar q instancia uma categoria a partir de um dto
        return new Categoria (ojbDto.getId(), ojbDto.getNome());
    }
}

