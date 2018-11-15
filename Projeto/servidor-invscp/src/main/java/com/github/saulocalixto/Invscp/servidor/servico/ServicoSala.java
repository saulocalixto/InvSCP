package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioSala;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioSala;
import com.github.saulocalixto.Invscp.servidor.negocio.Sala;

import java.util.List;


public class ServicoSala implements IServico<Sala>{

    private IRepositorioSala repositorio;

    public Sala Consultar(String id) {
        return null;
    }

    public List<Sala> ConsultarLista() {
        return null;
    }


    public void Salvar(Sala objeto) {

        //valida objeto
        repositorio().Salvar(objeto);
    }

    public void Atualizar(Sala objeto) {
        // Criar Validação
        // Chamar repositório ou devolver inconsistência
    }

    public void Excluir(String id) {
        // Criar Validação
        // Chamar repositório ou devolver inconsistência
    }

    private IRepositorioSala repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioSala());
    }
}