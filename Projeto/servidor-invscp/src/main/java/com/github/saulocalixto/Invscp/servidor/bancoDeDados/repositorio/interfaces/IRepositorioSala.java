package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces;

import com.github.saulocalixto.Invscp.servidor.negocio.sala.Sala;

import java.util.List;

public interface IRepositorioSala extends IRepositorio<Sala> {
    void atualizarDepartamento(String id, String idDepartamento);
    List<Sala> consulteSalasDeDepartamento(String idDepartamento);
    Boolean numeroDaSalaNaoSeRepeteNoPredio(int numeroSala, String idPredio);
}
