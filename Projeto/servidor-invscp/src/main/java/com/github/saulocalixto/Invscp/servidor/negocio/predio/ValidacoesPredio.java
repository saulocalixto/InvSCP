package com.github.saulocalixto.Invscp.servidor.negocio.predio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioEndereco;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioPredio;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioEndereco;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioPredio;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumGrupoDeAcesso;
import com.github.saulocalixto.Invscp.servidor.negocio.endereco.Endereco;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import java.util.List;

public class ValidacoesPredio extends ValidadorPadrao<Predio> {
    private IRepositorioPredio repositorio;
    private IRepositorioEndereco repositorioEndereco;

    private IRepositorioPredio repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioPredio());
    }

    private IRepositorioEndereco repositorioEndereco() {
        return repositorioEndereco != null ? repositorioEndereco : (repositorioEndereco = new RepositorioEndereco());
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
        try {
            this.conceito("Nome")
                    .validarSe(objetoValidado != null)
                    .ehValidoQuando(objetoValidado.getNome() != null && !objetoValidado.getNome().isEmpty())
                    .comMensagem("Nome do prédio é obrigatório")
                    .valide();
        } catch (NullPointerException e) {

        }
    }

    public void usuarioTemPermissaoParaAlterarPredio() {
        this.conceito("Grupo")
                .validarSe(permissaoDoUsuario != null)
                .ehValidoQuando(permissaoDoUsuario == EnumGrupoDeAcesso.CHEFE_PATRIMONIO)
                .comMensagem("Usuário não tem permissão para alterar ou cadastrar prédio")
                .valide();
    }

    public void enderecoObrigatorio() {
        this.conceito("Endereco")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getEndereco() != null)
                .comMensagem("Endereço deve ser informado")
                .valide();
    }

    public void EnderecoDeveExistir() {
        this.conceito("Endereço")
                .validarSe(objetoValidado != null && objetoValidado.getEndereco() != null &&
                        objetoValidado.getEndereco().getId() != null && !objetoValidado.getEndereco().getId().isEmpty())
                .ehValidoQuando(verificaEnderecoExiste(objetoValidado))
                .comMensagem("Endereço referenciado não existe")
                .valide();
    }

    private boolean verificaEnderecoExiste (Predio objetoValidado) {
        String idEndereco = objetoValidado.getEndereco().getId();
        Endereco endereco = repositorioEndereco().Consultar(idEndereco);
        return (endereco.getId().equals(idEndereco));
    }

    private void comumCadastroEAtualizacao() {
        nomeObrigatorio();
        usuarioTemPermissaoParaAlterarPredio();
        enderecoObrigatorio();
        EnderecoDeveExistir();
    }
}
