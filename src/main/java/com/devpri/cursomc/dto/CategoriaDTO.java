package com.devpri.cursomc.dto;

import com.devpri.cursomc.domain.Categoria;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public class CategoriaDTO  implements Serializable {
    private static final long serialVersionUID= 1L;

    private Integer id;

    @NotEmpty(message= "Preenchimento obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
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
