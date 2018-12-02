package com.github.saulocalixto.Invscp.servidor.negocio;

import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumMotivoBaixa;
import com.github.saulocalixto.Invscp.servidor.negocio.bemPatrimonial.BemPatrimonial;

public class Baixa extends ModelPadrao {

    private String idBem;

    private String data;

    private String observacao;

    private EnumMotivoBaixa motivo;

    public String getIdBem() {
        return idBem;
    }

    public void setIdBem(String bem) {
        this.idBem = bem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
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
