package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioLogin;
import com.github.saulocalixto.Invscp.servidor.negocio.Login;

import java.util.List;

/**
 * Created by Saulo Calixto on 13/11/18.
 */
public class RepositorioLogin extends RepositorioPadrao<Login> implements IRepositorioLogin {
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

    @Override
    public String NomeTabela() {
        return null;
    }
}
