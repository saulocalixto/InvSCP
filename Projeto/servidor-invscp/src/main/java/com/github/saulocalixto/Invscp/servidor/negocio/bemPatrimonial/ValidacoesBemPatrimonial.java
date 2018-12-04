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

    public void nTombamentoDeveSerInformado() {
        this.conceito("Numero de Tombamento")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getNumeroDeTombamento() != null &&
                        !objetoValidado.getNumeroDeTombamento().isEmpty())
                .comMensagem("Numero de Tombamento deve ser informado")
                .valide();
    }

    public void denominacaoDeveSerInformado() {
        this.conceito("Denominação")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getDenominacao() != null && !objetoValidado.getDenominacao().isEmpty())
                .comMensagem("Denominação deve ser informada")
                .valide();
    }

    public void dataDeAquisicaoDeveSerInformado() {
        this.conceito("Data De Aquisiçao")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getDataDeAquisicao() != null &&
                        !objetoValidado.getDataDeAquisicao().isEmpty())
                .comMensagem("Data De Aquisiçao deve ser informada")
                .valide();
    }

    public void especificacaoDeveSerInformado() {
        this.conceito("Especificação")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getEspecificacao() != null &&
                        !objetoValidado.getEspecificacao().isEmpty())
                .comMensagem("Especificação deve ser informada")
                .valide();
    }

    public void garantiaDeveSerInformado() {
        this.conceito("Garantia")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getGarantia() != null &&
                        !objetoValidado.getGarantia().isEmpty())
                .comMensagem("Garantia deve ser informada")
                .valide();
    }

    public void marcaDeveSerInformado() {
        this.conceito("Marca")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getMarca() != null &&
                        !objetoValidado.getMarca().isEmpty())
                .comMensagem("Marca deve ser informada")
                .valide();
    }

    public void notaFiscalDeveSerInformado() {
        this.conceito("Nota Fiscal")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getNotaFiscal() != null &&
                        !objetoValidado.getNotaFiscal().isEmpty())
                .comMensagem("Nota Fiscal deve ser informada")
                .valide();
    }

    public void statusDeveSerInformado() {
        this.conceito("Status")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getStatus() != null)
                .comMensagem("Status deve ser informado")
                .valide();
    }

    public void grupoDeMaterialDeveSerInformado() {
        this.conceito("Grupo De Material")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getGrupoDeMaterial() != null)
                .comMensagem("Grupo De Material deve ser informado")
                .valide();
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

    private void comumCadastroEAtualizacao() {
        nTombamentoDeveSerInformado();
        denominacaoDeveSerInformado();
        dataDeAquisicaoDeveSerInformado();
        especificacaoDeveSerInformado();
        garantiaDeveSerInformado();
        marcaDeveSerInformado();
        notaFiscalDeveSerInformado();
        statusDeveSerInformado();
        grupoDeMaterialDeveSerInformado();
        salaDeveExistir();
    }

    private IRepositorioBemPatrimonial repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioBemPatrimonial());
    }

    private IRepositorioSala repositorioSala() {
        return repositorioSala != null ? repositorioSala : (repositorioSala = new RepositorioSala());
    }
}
