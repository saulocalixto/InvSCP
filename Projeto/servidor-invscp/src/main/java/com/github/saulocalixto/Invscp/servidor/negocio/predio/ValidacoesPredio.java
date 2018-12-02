package com.github.saulocalixto.Invscp.servidor.negocio.predio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioPredio;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioPredio;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumGrupoDeAcesso;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import java.util.List;

public class ValidacoesPredio extends ValidadorPadrao<Predio> {
    private IRepositorioPredio repositorio;

    private IRepositorioPredio repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioPredio());
    }

    public List<Inconsistencia> ValideInclusao () {
        comumCadastroEAtualizacao();
        return super.ValideInclusao();
    }

    public List<Inconsistencia> ValideAtualizacao () {
        comumCadastroEAtualizacao();
        return super.ValideAtualizacao();
    }

    public List<Inconsistencia> ValideExclusao() {
        usuarioTemPermissaoParaAlterarPredio();
        return super.ValideExclusao();
    }

    public void nomeObrigatorio() {
        this.conceito("Nome")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getNome() != null && !objetoValidado.getNome().isEmpty())
                .comMensagem("Nome do prédio é obrigatório")
                .valide();
    }

    public void usuarioTemPermissaoParaAlterarPredio() {
        this.conceito("Grupo")
                .validarSe(permissaoDoUsuario != null)
                .ehValidoQuando(permissaoDoUsuario == EnumGrupoDeAcesso.CHEFE_PATRIMONIO)
                .comMensagem("Usuário não tem permissão para alterar outro usuário")
                .valide();
    }

    public void enderecoObrigatorio() {
        this.conceito("Endereco")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getEndereco() != null)
                .comMensagem("Endereço deve ser informado")
                .valide();
    }

    public void salaObrigatoria() {
        this.conceito("Sala")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getListaDeSalas() != null && objetoValidado.getListaDeSalas().size() > 0)
                .comMensagem("Pelo menos uma sala deve ser informada")
                .valide();
    }

    private void comumCadastroEAtualizacao() {
        nomeObrigatorio();
        usuarioTemPermissaoParaAlterarPredio();
        enderecoObrigatorio();
        salaObrigatoria();
    }
}
