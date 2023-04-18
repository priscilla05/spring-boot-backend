package com.devpri.cursomc.domain.enums;



public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa fisica"),
    PESSOAJURIDICA(2, "Pessoa juridica");

    private int codigo;
    private String descricao;

    TipoCliente(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }


    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer codigo){
        if(codigo==null){
            return null;
        }
        for (TipoCliente tipoCliente:TipoCliente.values()) {
            if (codigo.equals(tipoCliente.getCodigo())){
                return tipoCliente;
            }

        }
        throw new IllegalArgumentException("Id invalido"+ codigo);
    }


}
