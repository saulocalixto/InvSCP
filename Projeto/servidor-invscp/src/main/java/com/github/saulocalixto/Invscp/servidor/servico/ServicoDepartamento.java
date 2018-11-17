package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioDepartamento;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioDepartamento;
import com.github.saulocalixto.Invscp.servidor.negocio.Departamento;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;

import java.util.List;


public class ServicoDepartamento implements IServico<Departamento>{

    private IRepositorioDepartamento repositorio;

    public Departamento Consultar(String id) {
        return null;
    }

    public List<Departamento> ConsultarLista() {
        return null;
    }


    public List<Inconsistencia> Salvar(Departamento objeto) {

        //valida objeto
        repositorio().Salvar(objeto);
        return null;
    }

    public List<Inconsistencia> Atualizar(Departamento objeto) {
        // Criar Validação
        // Chamar repositório ou devolver inconsistência
        return null;
    }

    public void Excluir(String id) {
        // Criar Validação
        // Chamar repositório ou devolver inconsistência
    }

    private IRepositorioDepartamento repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioDepartamento());
    }
}