package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;


import com.github.saulocalixto.Invscp.servidor.bancoDeDados.ConexaoBd;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.SalaMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorio;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioSala;
import com.github.saulocalixto.Invscp.servidor.negocio.Sala;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RepositorioSala extends RepositorioPadrao<Sala> implements IRepositorioSala{

    public Sala Consultar(String id) {
        return null;
    }

    public List<Sala> ConsultarLista() {
        return null;
    }

    public void Salvar(Sala objeto) {

    }

    public void Atualizar(Sala objeto) {

    }

    public void Excluir(String id) {

    }

}