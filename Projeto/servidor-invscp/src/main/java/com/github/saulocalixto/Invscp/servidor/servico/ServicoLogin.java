package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.negocio.Login;

import java.util.List;

/**
 * Created by Saulo Calixto on 13/11/18.
 */
public class ServicoLogin implements IServico<Login> {
    @Override
    public Login Consultar(String id) {
        return null;
    }

    @Override
    public List<Login> ConsultarLista() {
        return null;
    }

    @Override
    public void Salvar(Login objeto) {

    }

    @Override
    public void Atualizar(Login objeto) {

    }

    @Override
    public void Excluir(String id) {

    }
}
