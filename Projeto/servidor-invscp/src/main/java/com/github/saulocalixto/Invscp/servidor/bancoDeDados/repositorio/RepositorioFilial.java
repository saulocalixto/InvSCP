package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.ConexaoBd;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.FilialMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorio;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioFilial;
import com.github.saulocalixto.Invscp.servidor.negocio.Filial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class RepositorioFilial extends RepositorioPadrao<Filial> implements IRepositorioFilial{

    public Filial Consultar(String id) {
        return null;
    }

    public List<Filial> ConsultarLista() {
        return null;
    }

    public void Salvar(Filial objeto) {

    }

    public void Atualizar(Filial objeto) {

    }

    public void Excluir(String id) {

    }

    @Override
    public String NomeTabela() {
        return "Filial";
    }

}
