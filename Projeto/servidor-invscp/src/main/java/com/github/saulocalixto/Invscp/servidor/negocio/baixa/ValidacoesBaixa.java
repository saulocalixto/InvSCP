package com.github.saulocalixto.Invscp.servidor.negocio.baixa;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioBaixa;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioBaixa;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumGrupoDeAcesso;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumStatusBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.negocio.bemPatrimonial.BemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import java.util.List;

public class ValidacoesBaixa extends ValidadorPadrao<Baixa> {

    private IRepositorioBaixa repositorio;
    private IRepositorioBemPatrimonial repositorioBem;

    public List<Inconsistencia> ValideInclusao () {
        bemNaoEstaBaixado();
        comumCadastroEAtualizacao();
        return super.ValideInclusao();
    }

    public List<Inconsistencia> ValideAtualizacao () {
        comumCadastroEAtualizacao();
        return super.ValideAtualizacao();
    }

    public List<Inconsistencia> ValideExclusao() {
        usuarioSemPermissaoParaBaixarBem();
        return super.ValideExclusao();
    }

    public void bemNaoEstaBaixado() {
        this.conceito("Bem patrimonial")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getId() != null &&
                    repositorioBem().Consultar(objetoValidado.getIdBem()).getStatus() == EnumStatusBemPatrimonial.EM_USO)
                .comMensagem("Bem não pode ser baixado porque não está em uso")
                .valide();
    }

    public void usuarioSemPermissaoParaBaixarBem() {
        this.conceito("Grupo")
                .validarSe(permissaoDoUsuario != null)
                .ehValidoQuando(permissaoDoUsuario == EnumGrupoDeAcesso.ADMINISTRADOR_DEPARTAMENTO
                        || permissaoDoUsuario == EnumGrupoDeAcesso.CHEFE_PATRIMONIO)
                .comMensagem("Usuário não tem permissão para baixar bem")
                .valide();
    }

    public void bemDeveSerInformado() {
        this.conceito("Bem Patrimonial")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getIdBem() != null && !objetoValidado.getIdBem().isEmpty())
                .comMensagem("Bem Patrimonial deve ser informado")
                .valide();
    }

    public void dataDeveSerInformado() {
        this.conceito("Data")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getData() != null)
                .comMensagem("Data deve ser informada")
                .valide();
    }

    public void motivoDeveSerInformado() {
        this.conceito("Motivo")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getMotivo() != null)
                .comMensagem("Motivo deve ser informado")
                .valide();
    }

    public void bemDeveExistir() {
        this.conceito("Bem Patrimonial")
                .validarSe(objetoValidado != null && objetoValidado.getIdBem() != null && !objetoValidado.getIdBem().isEmpty())
                .ehValidoQuando(verificaBemExiste(objetoValidado))
                .comMensagem("O Bem Patrimonial refenciado não existe")
                .valide();
    }

    private boolean verificaBemExiste (Baixa objetoValidado) {
        String idBem = objetoValidado.getIdBem();
        BemPatrimonial bemPatrimonial = repositorioBem().Consultar(idBem);
        return (bemPatrimonial.getId().equals(idBem));
    }


    private void comumCadastroEAtualizacao() {
        usuarioSemPermissaoParaBaixarBem();
        bemDeveSerInformado();
        bemDeveExistir();
        dataDeveSerInformado();
        motivoDeveSerInformado();
    }

    private IRepositorioBaixa repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioBaixa());
    }

    private IRepositorioBemPatrimonial repositorioBem() {
        return repositorioBem != null ? repositorioBem : (repositorioBem = new RepositorioBemPatrimonial());
    }
}
