package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorio;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumStatusBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.negocio.bemPatrimonial.BemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.bemPatrimonial.ValidacoesBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import java.util.List;

public class ServicoBemPatrimonial extends ServicoPadrao<BemPatrimonial> {
    public BemPatrimonial Consultar(String id) {
        return repositorio().Consultar(id);
    }

    public List<BemPatrimonial> ConsultarLista() {
        return repositorio().ConsultarLista();
    }


    public List<Inconsistencia> Salvar(BemPatrimonial objeto) {

        validador().setObjetoValidado(objeto);
        inconsistencias = validador().ValideInclusao();

        if(validador().naoHouveInconsistencias()) {
            repositorio().Salvar(objeto);
        }

        return inconsistencias;
    }

    public List<Inconsistencia> Atualizar(BemPatrimonial objeto) {
        validador().setObjetoValidado(objeto);
        inconsistencias = validador.ValideAtualizacao();
        if(validador().naoHouveInconsistencias()) {
            repositorio().Atualizar(objeto);
        }
        return inconsistencias;
    }

    public List<Inconsistencia> Excluir(String id) {

        BemPatrimonial bemPatrimonial = repositorio().Consultar(id);

        if(bemPatrimonial.getId().equals(id)) {
            validador().setObjetoValidado(bemPatrimonial);
            inconsistencias = validador().ValideExclusao();

            if(validador().naoHouveInconsistencias()) {
                repositorio().Excluir(id);
            }
        } else {
            CrieInconsistenciaConceitoNaoExiste();
        }

        return inconsistencias;
    }

    public List<Inconsistencia> mudarStatusBemPatrimonial(String idBem, EnumStatusBemPatrimonial status) {
        BemPatrimonial objeto = repositorio().Consultar(idBem);
        objeto.setStatus(status);
        validador().setObjetoValidado(objeto);
        inconsistencias = validador.ValideAtualizacao();
        if(validador().naoHouveInconsistencias()) {
            ((IRepositorioBemPatrimonial)repositorio()).mudarStatusBemPatrimonial(idBem, status);
        }
        return inconsistencias;
    }

    @Override
    IRepositorio<BemPatrimonial> repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioBemPatrimonial());
    }

    @Override
    ValidadorPadrao<BemPatrimonial> validador() {
        return validador != null ? validador : (validador = new ValidacoesBemPatrimonial());
    }
}
