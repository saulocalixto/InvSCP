package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioSala;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorio;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioSala;
import com.github.saulocalixto.Invscp.servidor.negocio.sala.Sala;
import com.github.saulocalixto.Invscp.servidor.negocio.sala.ValidacoesSala;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import java.util.List;


public class ServicoSala extends ServicoPadrao<Sala> {

    public Sala Consultar(String id) {
        return repositorio().Consultar(id);
    }

    public List<Sala> ConsultarLista() {
        return repositorio().ConsultarLista();
    }


    public List<Inconsistencia> Salvar(Sala objeto) {

        validador().setObjetoValidado(objeto);
        inconsistencias = validador().ValideInclusao();

        if(validador().naoHouveInconsistencias()) {
            repositorio().Salvar(objeto);
        }

        return inconsistencias;
    }

    public List<Inconsistencia> Atualizar(Sala objeto) {
        validador().setObjetoValidado(objeto);
        inconsistencias = validador.ValideAtualizacao();
        if(validador().naoHouveInconsistencias()) {
            repositorio().Atualizar(objeto);
        }
        return inconsistencias;
    }

    public List<Inconsistencia> Excluir(String id) {
        Sala sala = repositorio().Consultar(id);
        validador().setObjetoValidado(sala);
        inconsistencias = validador().ValideExclusao();

        if(validador().naoHouveInconsistencias()) {
            repositorio().Excluir(id);
        }

        return inconsistencias;
    }

    public List<Sala> consultarSalasDeDepartamento(String idDepartamento) {
        return ((IRepositorioSala)repositorio()).consulteSalasDeDepartamento(idDepartamento);
    }

    public List<Sala> consultarSalasDePredio(String idPredio) {
        return ((IRepositorioSala)repositorio()).consulteSalasDePredio(idPredio);
    }

    public List<Inconsistencia> atualizeSalasDePredio(String idSala, String idPredio) {
        Sala objeto = Consultar(idSala);
        objeto.setIdPredio(idPredio);
        validador().setObjetoValidado(objeto);
        inconsistencias = validador.ValideAtualizacao();
        if(validador().naoHouveInconsistencias()) {
            ((IRepositorioSala)repositorio()).atualizarPredio(idSala, idPredio);
        }
        return inconsistencias;
    }

    public List<Inconsistencia> atualizeDepartamentoDaSala(String idSala, String idDepartamento) {
        ((IRepositorioSala)repositorio()).atualizarDepartamento(idSala, idDepartamento);
        return inconsistencias;
    }

    @Override
    IRepositorio<Sala> repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioSala());
    }

    @Override
    ValidadorPadrao<Sala> validador() {
        return validador != null ? validador : (validador = new ValidacoesSala());
    }
}