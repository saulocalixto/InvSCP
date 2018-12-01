package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorio;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import java.util.List;

public abstract class ServicoPadrao<T> implements IServico<T> {

    protected IRepositorio<T> repositorio;
    protected ValidadorPadrao<T> validador;
    protected List<Inconsistencia> inconsistencias;

    abstract IRepositorio<T> repositorio();

    abstract ValidadorPadrao<T> validador();
}
