package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.PredioMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioPredio;
import com.github.saulocalixto.Invscp.servidor.negocio.Predio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RepositorioPredio extends RepositorioPadrao<Predio> implements IRepositorioPredio {


    public Predio Consultar(String id) {
        return null;
    }

    public List<Predio> ConsultarLista() {
        return null;
    }

    public void Salvar(Predio objeto) {

    }

    public void Atualizar(Predio objeto) {

    }

    public void Excluir(String id) {

    }
}
