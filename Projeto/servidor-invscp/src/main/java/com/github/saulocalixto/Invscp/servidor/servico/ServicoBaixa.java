package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioBaixa;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorio;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumStatusBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.negocio.baixa.Baixa;
import com.github.saulocalixto.Invscp.servidor.negocio.baixa.ValidacoesBaixa;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import javax.validation.ValidationException;
import java.util.List;

public class ServicoBaixa extends ServicoPadrao<Baixa> {

    ServicoBemPatrimonial servicoBemPatrimonial;

    @Override
    IRepositorio<Baixa> repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioBaixa());
    }

    @Override
    ValidadorPadrao<Baixa> validador() {
        return validador != null ? validador : (validador = new ValidacoesBaixa());
    }

    @Override
    public Baixa Consultar(String id) {
        Baixa baixa = repositorio().Consultar(id);
        return baixa;
    }

    @Override
    public List<Baixa> ConsultarLista() {
        List<Baixa> lista = repositorio().ConsultarLista();
        return lista;
    }

    @Override
    public List<Inconsistencia> Salvar(Baixa objeto) throws ValidationException {
        validador().setObjetoValidado(objeto);
        inconsistencias = validador().ValideInclusao();

        if(validador().naoHouveInconsistencias()) {
            repositorio().Salvar(objeto);
            servicoBemPatrimonial().mudarStatusBemPatrimonial(objeto.getIdBem(), EnumStatusBemPatrimonial.BAIXADO);
        }

        return inconsistencias;
    }

    @Override
    public List<Inconsistencia> Atualizar(Baixa objeto) {
        validador().setObjetoValidado(objeto);
        inconsistencias = validador.ValideAtualizacao();
        if(validador().naoHouveInconsistencias()) {
            repositorio().Atualizar(objeto);
        }
        return inconsistencias;
    }

    @Override
    public List<Inconsistencia> Excluir(String id) {
        Baixa baixa = Consultar(id);
        if(baixa.getId().equals(id)) {
            validador().setObjetoValidado(baixa);
            inconsistencias = validador.ValideExclusao();
            if(validador().naoHouveInconsistencias()) {
                repositorio().Excluir(id);
                servicoBemPatrimonial().mudarStatusBemPatrimonial(baixa.getIdBem(), EnumStatusBemPatrimonial.EM_USO);
            }
        } else {
            CrieInconsistenciaConceitoNaoExiste();
        }

        return inconsistencias;
    }

    private ServicoBemPatrimonial servicoBemPatrimonial() {
        return servicoBemPatrimonial != null ? servicoBemPatrimonial : (servicoBemPatrimonial = new ServicoBemPatrimonial());
    }
}
