package com.github.saulocalixto.Invscp.servidor.negocio.departamento;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioSala;
import com.github.saulocalixto.Invscp.servidor.negocio.ModelPadrao;
import com.github.saulocalixto.Invscp.servidor.negocio.sala.Sala;
import java.util.ArrayList;

import java.util.List;

/**
 * Created by Marcos Rafael on 29/10/18.
 */
public class Departamento extends ModelPadrao {

    private String nomeDoDepartamento;

    private List<String> listaDeSalas;

    public String getNomeDoDepartamento() {
        return nomeDoDepartamento;
    }

    public void setNomeDoDepartamento(String nomeDoDepartamento) {
        this.nomeDoDepartamento = nomeDoDepartamento;
    }

    public List<Sala> getListaDeSalas() {
        RepositorioSala repo = new RepositorioSala();
        List<Sala> listaDeSalas = new ArrayList<Sala>();
        for (String s : this.listaDeSalas) {
            listaDeSalas.add(repo.Consultar(s));
        }
        return listaDeSalas;
    }

    public void setListaDeSalas(List<Sala> listaDeSalas) {
        List<String> salasString = new ArrayList<String>();
        for (Sala s : listaDeSalas) {
            salasString.add(s.getId());
        }
        this.listaDeSalas = salasString;
    }
}
