package com.devpri.cursomc.domain;

import com.devpri.cursomc.domain.enums.EstadoPagamento;
import jakarta.persistence.Entity;


@Entity
public class PagamentoComCartao extends Pagamento{

    private Integer numeroDeParcelas;

    public PagamentoComCartao(){

    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estadoPagamento, pedido);
        this.numeroDeParcelas = numeroDeParcelas;


    }
}
