package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorio;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import java.util.ArrayList;
import java.util.List;

public abstract class ServicoPadrao<T> implements IServico<T> {

    protected IRepositorio<T> repositorio;
    protected ValidadorPadrao<T> validador;
    protected List<Inconsistencia> inconsistencias;

    List<Inconsistencia> deletarPadrao() {
        return null;
    }

    @Override
    public List<Inconsistencia> Excluir(String id) {

        deletarPadrao();

        return inconsistencias;
    }

    protected void CrieInconsistenciaConceitoNaoExiste() {
        inconsistencias = new ArrayList<>();
        Inconsistencia inconsistencia = new Inconsistencia();
        inconsistencia.setMensagem("Conceito não pode ser deletado porque não existe");
        inconsistencia.setConceito("O Saulo manda!");
        inconsistencias.add(inconsistencia);
    }

    abstract IRepositorio<T> repositorio();

    abstract ValidadorPadrao<T> validador();
}
