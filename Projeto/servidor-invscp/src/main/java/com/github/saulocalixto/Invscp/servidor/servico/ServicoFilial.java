package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioFilial;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioFilial;
import com.github.saulocalixto.Invscp.servidor.negocio.Filial;

import java.util.List;

/**
 * Created by aluno on 13/11/18.
 */
public class ServicoFilial implements IServico<Filial>{

    private IRepositorioFilial repositorio;

    public Filial Consultar(String id) {
        return null;
    }

    public List<Filial> ConsultarLista() {
        return null;
    }


    public void Salvar(Filial objeto) {

        //valida objeto
        repositorio().Salvar(objeto);
    }

    public void Atualizar(Filial objeto) {
        // Criar Validação
        // Chamar repositório ou devolver inconsistência
    }

    public void Excluir(String id) {
        // Criar Validação
        // Chamar repositório ou devolver inconsistência
    }

    private IRepositorioFilial repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioFilial());
    }
}