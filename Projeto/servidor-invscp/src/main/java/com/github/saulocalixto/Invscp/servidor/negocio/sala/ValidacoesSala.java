package com.github.saulocalixto.Invscp.servidor.negocio.sala;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioDepartamento;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioPredio;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioSala;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioDepartamento;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioPredio;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioSala;
import com.github.saulocalixto.Invscp.servidor.negocio.departamento.Departamento;
import com.github.saulocalixto.Invscp.servidor.negocio.predio.Predio;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import java.util.List;

public class ValidacoesSala extends ValidadorPadrao<Sala> {

    private IRepositorioSala repositorio;
    private IRepositorioPredio repositorioPredio;
    private IRepositorioDepartamento repositorioDepartamento;

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


    public void PredioDeveExistir() {
        this.conceito("Predio")
                .validarSe(objetoValidado != null && objetoValidado.getIdPredio() != null &&
                        !objetoValidado.getIdPredio().isEmpty())
                .ehValidoQuando(verificaPredioExiste(objetoValidado))
                .comMensagem("Predio referenciado não existe")
                .valide();
    }

    private boolean verificaPredioExiste (Sala objetoValidado) {
        String idPredio = objetoValidado.getIdPredio();
        Predio predio = repositorioPredio().Consultar(idPredio);
        return (predio.getId().equals(idPredio));
    }

    public void DepartamentoDeveExistir() {
        this.conceito("Departamento")
                .validarSe(objetoValidado != null && objetoValidado.getIdDepartamento() != null &&
                        !objetoValidado.getIdDepartamento().isEmpty())
                .ehValidoQuando(verificaDepartamentoExiste(objetoValidado))
                .comMensagem("Departamento referenciado não existe")
                .valide();
    }

    private boolean verificaDepartamentoExiste (Sala objetoValidado) {
        String idDepartamento = objetoValidado.getIdDepartamento();
        Departamento departamento = repositorioDepartamento().Consultar(idDepartamento);
        return (departamento.getId().equals(idDepartamento));
    }

    private void comumCadastroEAtualizacao() {
        numeroUnico();
        PredioDeveExistir();
        DepartamentoDeveExistir();
    }

    private IRepositorioSala repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioSala());
    }

    private IRepositorioPredio repositorioPredio() {
        return repositorioPredio != null ? repositorioPredio : (repositorioPredio = new RepositorioPredio());
    }

    private IRepositorioDepartamento repositorioDepartamento() {
        return repositorioDepartamento != null ? repositorioDepartamento :
                (repositorioDepartamento = new RepositorioDepartamento());
    }
}
