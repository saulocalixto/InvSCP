package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Created by Saulo Calixto on 23/10/18.
 */
public interface IServico<T> {
    public T Consultar(String id);
    public List<T> ConsultarLista();
    public List<Inconsistencia> Salvar(T objeto) throws ValidationException;
    public void Atualizar(T objeto);
    public void Excluir(String id);
}
