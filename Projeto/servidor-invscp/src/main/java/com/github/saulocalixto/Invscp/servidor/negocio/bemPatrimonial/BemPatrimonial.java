package com.github.saulocalixto.Invscp.servidor.negocio.bemPatrimonial;

import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumGrupoDeMaterial;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumStatusBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.negocio.ModelPadrao;
import com.github.saulocalixto.Invscp.servidor.negocio.sala.Sala;

import java.util.Date;

public class BemPatrimonial extends ModelPadrao {



    private String numeroDeTombamento;

    private Sala localAtual;

    private String denominacao;

    private Date dataDeAquisicao;

    private String especificacao;

    private String garantia;

    private String marca;

    private double valorDeCompra;

    private String identificadorDaNotaFiscal;

    private EnumStatusBemPatrimonial status;

    private EnumGrupoDeMaterial grupoDeMaterial;

    public String getNumeroDeTombamento() {
        return numeroDeTombamento;
    }

    public void setNumeroDeTombamento(String numeroDeTombamento) {
        this.numeroDeTombamento = numeroDeTombamento;
    }

    public Sala getLocalAtual() {
        return localAtual;
    }

    public void setLocalAtual(Sala localAtual) {
        this.localAtual = localAtual;
    }

    public String getDenominacao() {
        return denominacao;
    }

    public void setDenominacao(String denominacao) {
        this.denominacao = denominacao;
    }

    public Date getDataDeAquisicao() {
        return dataDeAquisicao;
    }

    public void setDataDeAquisicao(Date dataDeAquisicao) {
        this.dataDeAquisicao = dataDeAquisicao;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getValorDeCompra() {
        return valorDeCompra;
    }

    public void setValorDeCompra(double valorDeCompra) {
        this.valorDeCompra = valorDeCompra;
    }

    public EnumStatusBemPatrimonial getStatus() {
        return status;
    }

    public void setStatus(EnumStatusBemPatrimonial status) {
        this.status = status;
    }

    public EnumGrupoDeMaterial getGrupoDeMaterial() {
        return grupoDeMaterial;
    }

    public void setGrupoDeMaterial(EnumGrupoDeMaterial grupoDeMaterial) {
        this.grupoDeMaterial = grupoDeMaterial;
    }

    public String getIdentificadorDaNotaFiscal() {
        return identificadorDaNotaFiscal;
    }

    public void setIdentificadorDaNotaFiscal(String identificadorDaNotaFiscal) {
        this.identificadorDaNotaFiscal = identificadorDaNotaFiscal;
    }
}
