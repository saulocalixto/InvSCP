package com.github.saulocalixto.Invscp.servidor.negocio.bemPatrimonial;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioSala;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioSala;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumGrupoDeAcesso;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumStatusBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.negocio.sala.Sala;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;
import java.util.List;

import static com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.BaixaMap.idBem;

public class ValidacoesBemPatrimonial extends ValidadorPadrao<BemPatrimonial> {
    private IRepositorioBemPatrimonial repositorio;
    private IRepositorioSala repositorioSala;

    public List<Inconsistencia> ValideInclusao () {
        comumCadastroEAtualizacao();
        return super.ValideInclusao();
    }

    public List<Inconsistencia> ValideAtualizacao () {
        usuarioTemPermissaoParaAlterarBem();
        comumCadastroEAtualizacao();
        return super.ValideAtualizacao();
    }

    public List<Inconsistencia> ValideExclusao() {
        bemNaoPodeSerExcluido();
        return super.ValideExclusao();
    }

    public void usuarioTemPermissaoParaAlterarBem() {
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
        salaDeveExistir();

    }

    public void salaDeveExistir() {
        this.conceito("Sala")
                .validarSe(objetoValidado != null && objetoValidado.getLocalAtual() != null &&
                        !objetoValidado.getLocalAtual().isEmpty())
                .ehValidoQuando(verificaSalaExiste(objetoValidado))
                .comMensagem("Sala referenciada não existe")
                .valide();
    }

    private boolean verificaSalaExiste (BemPatrimonial objetoValidado) {
        String idSala = objetoValidado.getLocalAtual();
        Sala sala = repositorioSala().Consultar(idSala);
        return (sala.getId().equals(idSala));
    }

    private IRepositorioBemPatrimonial repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioBemPatrimonial());
    }

    private IRepositorioSala repositorioSala() {
        return repositorioSala != null ? repositorioSala : (repositorioSala = new RepositorioSala());
    }
}
