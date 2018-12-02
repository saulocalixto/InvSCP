package com.github.saulocalixto.Invscp.servidor.negocio.baixa;

import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumMotivoBaixa;
import com.github.saulocalixto.Invscp.servidor.negocio.ModelPadrao;

import java.util.Date;

public class Baixa extends ModelPadrao {

    private String idBem;

    private Date data;

    private String observacao;

    private EnumMotivoBaixa motivo;

    public String getIdBem() {
        return idBem;
    }

    public void setIdBem(String bem) {
        this.idBem = bem;
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

    public void setMotivo(String motivo) {
        try {
            this.motivo = EnumMotivoBaixa.valueOf(motivo);
        } catch (Exception e) {
            this.motivo = null;
        }
    }
}
