package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioOrdemDeServico;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorio;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumSituacaoOS;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumStatusBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.negocio.ordemDeServico.OrdemDeServico;
import com.github.saulocalixto.Invscp.servidor.negocio.ordemDeServico.ValidacoesOrdemDeServico;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import javax.validation.ValidationException;
import java.util.List;

public class ServicoOrdemDeServico extends ServicoPadrao<OrdemDeServico> {

    ServicoBemPatrimonial servicoBemPatrimonial;

    @Override
    IRepositorio<OrdemDeServico> repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioOrdemDeServico());
    }

    @Override
    ValidadorPadrao<OrdemDeServico> validador() {
        return validador != null ? validador : (validador = new ValidacoesOrdemDeServico());
    }

    @Override
    public OrdemDeServico Consultar(String id) {
        OrdemDeServico ordemDeServico = repositorio().Consultar(id);
        return ordemDeServico;
    }

    @Override
    public List<OrdemDeServico> ConsultarLista() {
        List<OrdemDeServico> lista = repositorio().ConsultarLista();
        return lista;
    }

    @Override
    public List<Inconsistencia> Salvar(OrdemDeServico objeto) throws ValidationException {
        inconsistencias = validador().ValideInclusao();
        validador().setObjetoValidado(objeto);

        if(validador().naoHouveInconsistencias()) {
            repositorio().Salvar(objeto);
            servicoBemPatrimonial().mudarStatusBemPatrimonial(objeto.getBem(), EnumStatusBemPatrimonial.EM_ORDEM_DE_SERVICO);
        }

        return inconsistencias;
    }

    @Override
    public List<Inconsistencia> Atualizar(OrdemDeServico objeto) {
        validador().setObjetoValidado(objeto);
        inconsistencias = validador.ValideAtualizacao();
        if(validador().naoHouveInconsistencias()) {
            repositorio().Atualizar(objeto);

            // Caso seja um encerramento ou cancelamento de OS o status do objeto deve voltar a ser EM_USO
            if (objeto.getSituacao() == EnumSituacaoOS.ENCERRADA || objeto.getSituacao() == EnumSituacaoOS.CANCELADA) {
                servicoBemPatrimonial().mudarStatusBemPatrimonial(objeto.getBem(), EnumStatusBemPatrimonial.EM_USO);
            }
        }
        return inconsistencias;
    }

    @Override
    public List<Inconsistencia> Excluir(String id) {
        OrdemDeServico ordemDeServico = Consultar(id);
        validador().setObjetoValidado(ordemDeServico);
        inconsistencias = validador.ValideExclusao();
        if(validador().naoHouveInconsistencias()) {
            repositorio().Excluir(id);
            servicoBemPatrimonial().mudarStatusBemPatrimonial(ordemDeServico.getBem(), EnumStatusBemPatrimonial.EM_USO);
        }

        return inconsistencias;
    }

    private ServicoBemPatrimonial servicoBemPatrimonial() {
        return servicoBemPatrimonial != null ? servicoBemPatrimonial : (servicoBemPatrimonial = new ServicoBemPatrimonial());
    }
}
