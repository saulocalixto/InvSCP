package com.github.saulocalixto.Invscp.servidor.negocio.bemPatrimonial;

import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumGrupoDeMaterial;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumStatusBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.negocio.ModelPadrao;

public class BemPatrimonial extends ModelPadrao {

    private String numeroDeTombamento;

    private String localAtual;

    private String denominacao;

    private String dataDeAquisicao;

    private String especificacao;

    private String garantia;

    private String marca;

    private String valorDeCompra;

    private String notaFiscal;

    private String ordemDeServico;

    private EnumStatusBemPatrimonial status;

    private EnumGrupoDeMaterial grupoDeMaterial;

    public String getOrdemDeServico() {
        return ordemDeServico;
    }

    public void setOrdemDeServico(String ordemDeServico) {
        this.ordemDeServico = ordemDeServico;
    }

    public String getNumeroDeTombamento() {
        return numeroDeTombamento;
    }

    public void setNumeroDeTombamento(String numeroDeTombamento) {
        this.numeroDeTombamento = numeroDeTombamento;
    }

    public String getLocalAtual() {
        return localAtual;
    }

    public void setLocalAtual(String localAtual) {
        this.localAtual = localAtual;
    }

    public String getDenominacao() {
        return denominacao;
    }

    public void setDenominacao(String denominacao) {
        this.denominacao = denominacao;
    }

    public String getDataDeAquisicao() {
        return dataDeAquisicao;
    }

    public void setDataDeAquisicao(String dataDeAquisicao) {
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
        return Double.parseDouble(valorDeCompra);
    }

    public void setValorDeCompra(double valorDeCompra) {
        this.valorDeCompra = String.valueOf(valorDeCompra);
    }

    public EnumStatusBemPatrimonial getStatus() {
        return status;
    }

    public void setStatus(EnumStatusBemPatrimonial status) {
        this.status = status;
    }

    public void setStatus(String status) {
        try {
            this.status = EnumStatusBemPatrimonial.valueOf(status);
        } catch (Exception e) {
            this.status = null;
        }
    }

    public EnumGrupoDeMaterial getGrupoDeMaterial() {
        return grupoDeMaterial;
    }

    public void setGrupoDeMaterial(EnumGrupoDeMaterial grupoDeMaterial) {
        this.grupoDeMaterial = grupoDeMaterial;
    }

    public void setGrupoDeMaterial(String grupoDeMaterial) {
        try {
            this.grupoDeMaterial = EnumGrupoDeMaterial.valueOf(grupoDeMaterial);
        } catch (Exception e) {
            this.grupoDeMaterial = null;
        }
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }
}
