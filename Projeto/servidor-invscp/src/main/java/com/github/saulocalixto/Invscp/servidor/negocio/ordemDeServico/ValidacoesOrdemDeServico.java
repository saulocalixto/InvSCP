package com.github.saulocalixto.Invscp.servidor.negocio.ordemDeServico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioOrdemDeServico;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioOrdemDeServico;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumSituacaoOS;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumStatusBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.negocio.bemPatrimonial.BemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;
import java.util.List;

public class ValidacoesOrdemDeServico extends ValidadorPadrao<OrdemDeServico> {

    private IRepositorioOrdemDeServico repositorioOrdemDeServico;
    private IRepositorioBemPatrimonial repositorioBemPatrimonial;

    public List<Inconsistencia> ValideInclusao () {
        validacoesRegistroDeOS();
        return super.ValideInclusao();
    }

    public List<Inconsistencia> ValideAtualizacao () {
        validacoesAtualizacaodeOS();
        return super.ValideAtualizacao();
    }

    public List<Inconsistencia> ValideExclusao() {
        return super.ValideExclusao();
    }

    public void identificadorDaOSDeveSerInformado() {
        this.conceito("IdentificadorDaOS")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getIdentificadorDaOS() != null && !objetoValidado.getIdentificadorDaOS().isEmpty())
                .comMensagem("Identificador Da O.S. deve ser informado")
                .valide();
    }

    public void bemDeveSerInformado() {
        this.conceito("BemPatrimonial")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getBem() != null && !objetoValidado.getBem().isEmpty())
                .comMensagem("Bem Patrimonial deve ser informado")
                .valide();
    }

    public void motivoDeveSerInformado() {
        this.conceito("Motivo")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getMotivo() != null && !objetoValidado.getMotivo().isEmpty())
                .comMensagem("Motivo deve ser informado")
                .valide();
    }

    public void dataAberturaDeveSerInformado() {
        this.conceito("Data Abertura")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getDataAbertura() != null && !objetoValidado.getDataAbertura().isEmpty())
                .comMensagem("Data de abertura deve ser informada")
                .valide();
    }

    public void dataEncerramentoDeveSerInformado() {
        this.conceito("Data Encerramento")
                .validarSe(objetoValidado != null && objetoValidado.getSituacao() != null &&
                        (objetoValidado.getSituacao() == EnumSituacaoOS.ENCERRADA ||
                        objetoValidado.getSituacao() == EnumSituacaoOS.CANCELADA))
                .ehValidoQuando(objetoValidado.getDataEncerramento() != null && !objetoValidado.getDataEncerramento().isEmpty())
                .comMensagem("Data de encerramento deve ser informada")
                .valide();
    }

    public void nomeDaPrestadoraDeveSerInformado() {
        this.conceito("Nome Da Prestador")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getNomeDaPrestadora() != null && !objetoValidado.getNomeDaPrestadora().isEmpty())
                .comMensagem("Nome da Prestador deve ser informado")
                .valide();
    }

    public void situacaoDeveSerInformado() {
        this.conceito("Situação")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getSituacao() != null)
                .comMensagem("Situação deve ser informada")
                .valide();
    }
    // Não se pode criar uma OS encerrada ou cancelada direto, ela deve estar EM_ANDAMENTO ou AGUARDANDO_RETIRADA
    public void situacoesPossiveis() {
        this.conceito("Situação")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getSituacao() != null &&
                        (objetoValidado.getSituacao() == EnumSituacaoOS.AGUARDANDO_RETIRADA ||
                        objetoValidado.getSituacao() == EnumSituacaoOS.EM_ANDAMENTO))
                .comMensagem("Uma OS só pode ser criada aguardando retirada ou em andamento")
                .valide();
    }

    public void bemDeveEstarEmUso() {
        this.conceito("Bem Patrimonial")
                .validarSe(objetoValidado != null && objetoValidado.getBem() != null && !objetoValidado.getBem().isEmpty())
                .ehValidoQuando(verificaBemEmUso(objetoValidado))
                .comMensagem("O Bem Patrimonial não está em uso")
                .valide();
    }

    private boolean verificaBemEmUso (OrdemDeServico objetoValidado) {
        String idBem = objetoValidado.getBem();
        BemPatrimonial bemPatrimonial = repositorioBemPatrimonial().Consultar(idBem);
        return (bemPatrimonial.getStatus() == EnumStatusBemPatrimonial.EM_USO);
    }

    public void osDeveEstarEmAndamento() {
        this.conceito("Ordem De Servico")
                .validarSe(objetoValidado != null && objetoValidado.getSituacao() != null &&
                        (objetoValidado.getSituacao() == EnumSituacaoOS.ENCERRADA ||
                        objetoValidado.getSituacao() == EnumSituacaoOS.CANCELADA))
                .ehValidoQuando(verificaOSEmAndamento(objetoValidado))
                .comMensagem("Ordem de serviço não está em andamento")
                .valide();
    }

    public void osNaoPodeEstarEncerrada() {
        this.conceito("Ordem De Servico")
                .validarSe(objetoValidado != null && objetoValidado.getId() != null && !objetoValidado.getId().isEmpty())
                .ehValidoQuando(!verificaOSEncerrada(objetoValidado))
                .comMensagem("Ordem de serviço já foi encerrada")
                .valide();
    }

    private boolean verificaOSEncerrada (OrdemDeServico objetoValidado) {
        String idOS = objetoValidado.getId();
        OrdemDeServico osConsultada = repositorioOrdemDeServico().Consultar(idOS);
        return ((osConsultada.getSituacao() == EnumSituacaoOS.ENCERRADA ||
                osConsultada.getSituacao() == EnumSituacaoOS.CANCELADA));
    }

    private boolean verificaOSEmAndamento (OrdemDeServico objetoValidado) {
        String idOS = objetoValidado.getId();
        OrdemDeServico osConsultada = repositorioOrdemDeServico().Consultar(idOS);
        return (osConsultada.getSituacao() == EnumSituacaoOS.EM_ANDAMENTO);
    }

    public void bemDeveExistir() {
        this.conceito("Bem Patrimonial")
                .validarSe(objetoValidado != null && objetoValidado.getBem() != null && !objetoValidado.getBem().isEmpty())
                .ehValidoQuando(verificaBemExiste(objetoValidado))
                .comMensagem("O Bem Patrimonial refenciado não existe")
                .valide();
    }

    private boolean verificaBemExiste (OrdemDeServico objetoValidado) {
        String idBem = objetoValidado.getBem();
        BemPatrimonial bemPatrimonial = repositorioBemPatrimonial().Consultar(idBem);
        return (bemPatrimonial.getId() == idBem);
    }




    private void validacoesAtualizacaodeOS() {
        identificadorDaOSDeveSerInformado();
        bemDeveSerInformado();
        motivoDeveSerInformado();
        dataAberturaDeveSerInformado();
        dataEncerramentoDeveSerInformado();
        nomeDaPrestadoraDeveSerInformado();
        situacaoDeveSerInformado();
        osDeveEstarEmAndamento();
        osNaoPodeEstarEncerrada();
    }

    private void validacoesRegistroDeOS() {
        identificadorDaOSDeveSerInformado();
        bemDeveSerInformado();
        motivoDeveSerInformado();
        dataAberturaDeveSerInformado();
        nomeDaPrestadoraDeveSerInformado();
        bemDeveEstarEmUso();
        situacaoDeveSerInformado();
        situacoesPossiveis();
        bemDeveExistir();
    }


    private IRepositorioOrdemDeServico repositorioOrdemDeServico() {
        return repositorioOrdemDeServico != null ? repositorioOrdemDeServico :
                (repositorioOrdemDeServico = new RepositorioOrdemDeServico());
    }

    private IRepositorioBemPatrimonial repositorioBemPatrimonial() {
        return repositorioBemPatrimonial != null ? repositorioBemPatrimonial :
                (repositorioBemPatrimonial = new RepositorioBemPatrimonial());
    }
}
