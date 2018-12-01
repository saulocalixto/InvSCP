package com.github.saulocalixto.Invscp.servidor.negocio.endereco;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioEndereco;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioEndereco;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import java.util.List;

public class ValidacoesEndereco extends ValidadorPadrao<Endereco> {

    private IRepositorioEndereco repositorio;

    public List<Inconsistencia> ValideInclusao () {
        comumCadastroEAtualizacao();
        return super.ValideInclusao();
    }

    public List<Inconsistencia> ValideAtualizacao () {
        comumCadastroEAtualizacao();
        return super.ValideAtualizacao();
    }

    public List<Inconsistencia> ValideExclusao() {
        return super.ValideExclusao();
    }

    public void bairroDeveSerInformado() {
        this.conceito("Bairro")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getBairro() != null && !objetoValidado.getBairro().isEmpty())
                .comMensagem("Bairro deve ser informado")
                .valide();
    }

    public void cidadeDeveSerInformado() {
        this.conceito("Cidade")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getCidade() != null && !objetoValidado.getCidade().isEmpty())
                .comMensagem("Cidade deve ser informado")
                .valide();
    }

    public void cepDeveSerInformado() {
        this.conceito("CEP")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getCep() != null && !objetoValidado.getCep().isEmpty())
                .comMensagem("Cidade deve ser informado")
                .valide();
    }

    public void ruaDeveSerInformado() {
        this.conceito("CEP")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getRua() != null && !objetoValidado.getRua().isEmpty())
                .comMensagem("Rua deve ser informada")
                .valide();
    }

    private void comumCadastroEAtualizacao() {
        bairroDeveSerInformado();
        ruaDeveSerInformado();
        cepDeveSerInformado();
        cidadeDeveSerInformado();
    }

    private IRepositorioEndereco repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioEndereco());
    }
}
