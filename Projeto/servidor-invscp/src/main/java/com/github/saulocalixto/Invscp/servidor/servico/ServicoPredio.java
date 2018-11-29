package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioPredio;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioPredio;
import com.github.saulocalixto.Invscp.servidor.negocio.Predio;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;

import java.util.List;


public class ServicoPredio implements IServico<Predio>{

    private IRepositorioPredio repositorio;

    public Predio Consultar(String id) {
        return null;
    }

    public List<Predio> ConsultarLista() {
        return null;
    }


    public List<Inconsistencia> Salvar(Predio objeto) {

        //valida objeto
        repositorio().Salvar(objeto);
        return null;
    }

    public List<Inconsistencia> Atualizar(Predio objeto) {
        // Criar Validação
        // Chamar repositório ou devolver inconsistência
        return null;
    }

    public List<Inconsistencia> Excluir(String id) {
        // Criar Validação
        // Chamar repositório ou devolver inconsistência
        return null;
    }

    private IRepositorioPredio repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioPredio());
    }
}