package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces;

import com.github.saulocalixto.Invscp.servidor.negocio.departamento.Departamento;
import com.github.saulocalixto.Invscp.servidor.negocio.usuario.Usuario;

import java.util.List;

public interface IRepositorioDepartamento extends IRepositorio<Departamento> {

    Boolean departamentoTemChefe(String idDepartamento);
    Boolean departamentoExiste(String id);
}
