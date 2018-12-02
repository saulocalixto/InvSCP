package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces;

import com.github.saulocalixto.Invscp.servidor.negocio.sala.Sala;

import java.util.List;

public interface IRepositorioSala extends IRepositorio<Sala> {
    void atualizarDepartamento(String id, String idDepartamento);
    void atualizarPredio(String id, String idPredio);
    List<Sala> consulteSalasDeDepartamento(String idDepartamento);
    List<Sala> consulteSalasDePredio(String idPredio);
    Boolean numeroDaSalaNaoSeRepeteNoPredio(int numeroSala, String idPredio);
}
