package com.github.saulocalixto.Invscp.servidor.repositorio;

import java.util.List;

/**
 * Created by Saulo Calixto on 23/10/18.
 */
public interface IRepositorio<T> {
    public T Consultar(String id);
    public List<T> ConsultarLista();
    public void Salvar(T objeto);
    public void Atualizar(T objeto);
    public void Excluir(String id);
}
