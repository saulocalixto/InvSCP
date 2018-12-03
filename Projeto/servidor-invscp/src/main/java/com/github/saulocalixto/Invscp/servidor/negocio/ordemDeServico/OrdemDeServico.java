package com.github.saulocalixto.Invscp.servidor.negocio.ordemDeServico;

import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumSituacaoOS;
import com.github.saulocalixto.Invscp.servidor.negocio.ModelPadrao;

import java.util.Date;

public class OrdemDeServico extends ModelPadrao {

    private String identificadorDaOS;

    private String bem;

    private String motivo;

    private String observacao;

    private String dataAbertura;

    private String dataEncerramento;

    private String nomeDaPrestadora;

    private EnumSituacaoOS situacao;


    public String getIdentificadorDaOS() {
        return identificadorDaOS;
    }

    public void setIdentificadorDaOS(String identificadorDaOS) {
        this.identificadorDaOS = identificadorDaOS;
    }

    public String getBem() {
        return bem;
    }

    public void setBem(String bem) {
        this.bem = bem;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(String dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public String getNomeDaPrestadora() {
        return nomeDaPrestadora;
    }

    public void setNomeDaPrestadora(String nomeDaPrestadora) {
        this.nomeDaPrestadora = nomeDaPrestadora;
    }

    public EnumSituacaoOS getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        try {
            this.situacao = EnumSituacaoOS.valueOf(situacao);
        } catch (Exception e) {
            this.situacao = null;
        }
    }
}
