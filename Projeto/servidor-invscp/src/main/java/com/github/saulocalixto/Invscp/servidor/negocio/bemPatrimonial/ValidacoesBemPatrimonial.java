package com.github.saulocalixto.Invscp.servidor.negocio.bemPatrimonial;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumGrupoDeAcesso;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumStatusBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import java.util.List;

public class ValidacoesBemPatrimonial extends ValidadorPadrao<BemPatrimonial> {
    private IRepositorioBemPatrimonial repositorio;

    public List<Inconsistencia> ValideInclusao () {
        comumCadastroEAtualizacao();
        return super.ValideInclusao();
    }

    public List<Inconsistencia> ValideAtualizacao () {
        usuarioTemPermissaoParaAlterarUsuario();
        comumCadastroEAtualizacao();
        return super.ValideAtualizacao();
    }

    public List<Inconsistencia> ValideExclusao() {
        bemNaoPodeSerExcluido();
        return super.ValideExclusao();
    }

    public void usuarioTemPermissaoParaAlterarUsuario() {
        this.conceito("Grupo")
                .validarSe(permissaoDoUsuario != null)
                .ehValidoQuando(permissaoDoUsuario == EnumGrupoDeAcesso.ADMINISTRADOR_DEPARTAMENTO
                        || permissaoDoUsuario == EnumGrupoDeAcesso.CHEFE_PATRIMONIO)
                .comMensagem("Usuário não tem permissão para alterar outro bem patrimonial")
                .valide();
    }

    public void bemNaoPodeSerExcluido() {
        this.conceito("Status")
                .validarSe(objetoValidado != null && objetoValidado.getStatus() != null)
                .ehValidoQuando(objetoValidado.getStatus() == EnumStatusBemPatrimonial.EM_USO)
                .comMensagem("Bem patrimonial não pode ser excluído")
                .valide();
    }

    private void comumCadastroEAtualizacao() {
    }

    private IRepositorioBemPatrimonial repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioBemPatrimonial());
    }
}
