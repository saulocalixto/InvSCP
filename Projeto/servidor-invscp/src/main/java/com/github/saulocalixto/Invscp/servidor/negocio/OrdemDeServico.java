package com.github.saulocalixto.Invscp.servidor.negocio;

import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumSituacaoOS;
import com.github.saulocalixto.Invscp.servidor.negocio.bemPatrimonial.BemPatrimonial;

import java.util.Date;

public class OrdemDeServico extends ModelPadrao {

    private String identificadorDaOS;

    private BemPatrimonial bem;

    private String motivo;

    private String observacao;

    private Date dataAbertura;

    private Date dataEncerramento;

    private String nomeDaPrestadora;

    private EnumSituacaoOS situacao;


    public String getIdentificadorDaOS() {
        return identificadorDaOS;
    }

    public void setIdentificadorDaOS(String identificadorDaOS) {
        this.identificadorDaOS = identificadorDaOS;
    }

    public BemPatrimonial getBem() {
        return bem;
    }

    public void setBem(BemPatrimonial bem) {
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

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(Date dataEncerramento) {
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

    public void setSituacao(EnumSituacaoOS situacao) {
        this.situacao = situacao;
    }
}
