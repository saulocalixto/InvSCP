package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioSala;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioSala;
import com.github.saulocalixto.Invscp.servidor.negocio.sala.Sala;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;

import java.util.List;


public class ServicoSala implements IServico<Sala>{

    private IRepositorioSala repositorio;

    public Sala Consultar(String id) {
        return repositorio().Consultar(id);
    }

    public List<Sala> ConsultarLista() {
        return repositorio().ConsultarLista();
    }


    public List<Inconsistencia> Salvar(Sala objeto) {

        //valida objeto
        repositorio().Salvar(objeto);
        return null;
    }

    public List<Inconsistencia> Atualizar(Sala objeto) {
        // Criar Validação
        // Chamar repositório ou devolver inconsistência
        return null;
    }

    public List<Inconsistencia> Excluir(String id) {
        // Criar Validação
        // Chamar repositório ou devolver inconsistência
        return null;
    }

    public List<Sala> consultarSalasDeDepartamento(String idDepartamento) {
        return repositorio().consulteSalasDeDepartamento(idDepartamento);
    }

    public List<Inconsistencia> atualizeDepartamentoDaSala(String idSala, String idDepartamento) {
        repositorio().atualizarDepartamento(idSala, idDepartamento);
        return null;
    }

    private IRepositorioSala repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioSala());
    }
}