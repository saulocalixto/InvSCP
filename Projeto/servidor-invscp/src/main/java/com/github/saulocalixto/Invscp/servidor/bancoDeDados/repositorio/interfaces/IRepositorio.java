package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Saulo Calixto on 23/10/18.
 */
public interface IRepositorio<T> {
    T Consultar(String id);
    List<T> ConsultarLista();
    void Salvar(T objeto);
    void Atualizar(T objeto);
    void Excluir(String id);
    Connection RetorneConexaoBd();
}
