package com.github.saulocalixto.Invscp.servidor.negocio.predio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioEndereco;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioSala;
import com.github.saulocalixto.Invscp.servidor.negocio.ModelPadrao;
import com.github.saulocalixto.Invscp.servidor.negocio.endereco.Endereco;
import com.github.saulocalixto.Invscp.servidor.negocio.sala.Sala;
import java.util.ArrayList;

import java.util.List;

/**
 * Created by Marcos Rafael on 29/10/18.
 */
public class Predio extends ModelPadrao {

    private String nome;

    private String endereco;

    private List<String> listaDeSalas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        RepositorioEndereco repo = new RepositorioEndereco();
        return repo.Consultar(endereco);
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco.getId();
    }

    public List<Sala> getListaDeSalas() {
        RepositorioSala repo = new RepositorioSala();
        List<Sala> listinhas = new ArrayList<Sala>();
        for (String s : this.listaDeSalas) {
            listinhas.add(repo.Consultar(s));
        }
        return listinhas;
    }

    public void setListaDeSalas(List<Sala> listaDeSalas) {
        List<String> salasString = new ArrayList<String>();
        for (Sala s : listaDeSalas) {
            salasString.add(s.getId());
        }
        this.listaDeSalas = salasString;
    }
}
