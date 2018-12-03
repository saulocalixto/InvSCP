package com.github.saulocalixto.Invscp.servidor.negocio.baixa;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioBaixa;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioBaixa;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumGrupoDeAcesso;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumStatusBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import java.util.List;

public class ValidacoesBaixa extends ValidadorPadrao<Baixa> {

    private IRepositorioBaixa repositorio;
    private IRepositorioBemPatrimonial repositorioBem;

    public List<Inconsistencia> ValideInclusao () {
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
                .comMensagem("Bem não pode ser baixado porque seu status está diferente de: 'Em uso'")
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

    private void comumCadastroEAtualizacao() {
        bemNaoEstaBaixado();
        usuarioSemPermissaoParaBaixarBem();
    }

    private IRepositorioBaixa repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioBaixa());
    }

    private IRepositorioBemPatrimonial repositorioBem() {
        return repositorioBem != null ? repositorioBem : (repositorioBem = new RepositorioBemPatrimonial());
    }
}
