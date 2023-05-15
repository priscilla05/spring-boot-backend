package com.devpri.cursomc.dto;

import com.devpri.cursomc.domain.Categoria;

import java.io.Serializable;

public class CategoriaDTO  implements Serializable {
    private static final long serialVersionUID= 1L;

    private Integer id;
    private String nome;

    public CategoriaDTO(){

    }

    public CategoriaDTO(Categoria objeto){
        id= objeto.getId();
        nome= objeto.getNome();
    }

    public CategoriaDTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
