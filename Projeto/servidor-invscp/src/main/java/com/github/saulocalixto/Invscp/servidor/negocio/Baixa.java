package com.github.saulocalixto.Invscp.servidor.negocio;

import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumMotivoBaixa;
import com.github.saulocalixto.Invscp.servidor.negocio.bemPatrimonial.BemPatrimonial;

import java.util.Date;

public class Baixa extends ModelPadrao {

    private BemPatrimonial bem;

    private Date data;

    private String observacao;

    private EnumMotivoBaixa motivo;

    public BemPatrimonial getBem() {
        return bem;
    }

    public void setBem(BemPatrimonial bem) {
        this.bem = bem;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public EnumMotivoBaixa getMotivo() {
        return motivo;
    }

    public void setMotivo(EnumMotivoBaixa motivo) {
        this.motivo = motivo;
    }
}
