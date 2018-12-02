package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces;

import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumStatusBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.negocio.bemPatrimonial.BemPatrimonial;

public interface IRepositorioBemPatrimonial extends IRepositorio<BemPatrimonial> {
    void mudarStatusBemPatrimonial(String idBem, EnumStatusBemPatrimonial status);
}
