package com.github.saulocalixto.Invscp.servidor.negocio.sala;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioSala;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioSala;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import java.util.List;

public class ValidacoesSala extends ValidadorPadrao<Sala> {

    private IRepositorioSala repositorio;

    public ValidacoesSala(Sala objetoValidado) {
        super(objetoValidado);
    }

    public List<Inconsistencia> ValideInclusao () {
        comumCadastroEAtualizacao();
        return super.ValideInclusao();
    }

    public List<Inconsistencia> ValideAtualizacao () {
        salaVinculadaADepartamento();
        comumCadastroEAtualizacao();
        salaVinculadaAPredio();
        return super.ValideAtualizacao();
    }

    public List<Inconsistencia> ValideExclusao() {
        return super.ValideExclusao();
    }

    public void numeroUnico() {
        this.conceito("Numero Sala")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(repositorio().numeroDaSalaNaoSeRepeteNoPredio(objetoValidado.
                                                                    getNumeroSala(),objetoValidado.getIdPredio()))
                .comMensagem("O número da sala não pode se repetir dentro do mesmo prédio.")
                .valide();
    }

    public void salaVinculadaADepartamento() {
        this.conceito("Departamento")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(repositorio().Consultar(objetoValidado.getId()).getIdDepartamento() == null
                || repositorio().Consultar(objetoValidado.getId())
                        .getIdDepartamento().equals(objetoValidado.getIdDepartamento()))
                .comMensagem("Sala já vinculada a um departamento.")
                .valide();
    }

    public void salaVinculadaAPredio() {
        this.conceito("Prédio")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(repositorio().Consultar(objetoValidado.getId()).getIdPredio() == null
                        || repositorio().Consultar(objetoValidado.getId())
                        .getIdPredio().equals(objetoValidado.getIdPredio()))
                .comMensagem("Sala já vinculada a um prédio.")
                .valide();
    }

    private void comumCadastroEAtualizacao() {
        numeroUnico();
    }

    private IRepositorioSala repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioSala());
    }
}
