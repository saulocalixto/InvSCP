package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Created by Saulo Calixto on 23/10/18.
 */
public interface IServico<T> {
    T Consultar(String id);
    List<T> ConsultarLista();
    List<Inconsistencia> Salvar(T objeto) throws ValidationException;
    List<Inconsistencia> Atualizar(T objeto);
    void Excluir(String id);
}
