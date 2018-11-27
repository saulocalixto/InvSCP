package com.github.saulocalixto.Invscp.servidor.negocio;

import com.github.saulocalixto.Invscp.servidor.negocio.usuario.Usuario;

import java.util.List;

/**
 * Created by Marcos Rafael on 29/10/18.
 */
public class Departamento extends ModelPadrao {

    private String nomeDoDepartamento;

    private List<Usuario> listaDeUsuarios;

    private List<Sala> listaDeSalas;

    public String getNomeDoDepartamento() {
        return nomeDoDepartamento;
    }

    public void setNomeDoDepartamento(String nomeDoDepartamento) {
        this.nomeDoDepartamento = nomeDoDepartamento;
    }

    public List<Usuario> getListaDeUsuarios() {
        return listaDeUsuarios;
    }

    public void setListaDeUsuarios(List<Usuario> listaDeUsuarios) {
        this.listaDeUsuarios = listaDeUsuarios;
    }

    public List<Sala> getListaDeSalas() {
        return listaDeSalas;
    }

    public void setListaDeSalas(List<Sala> listaDeSalas) {
        this.listaDeSalas = listaDeSalas;
    }
}
