package com.github.saulocalixto.Invscp.servidor.negocio.predio;

import com.github.saulocalixto.Invscp.servidor.negocio.ModelPadrao;
import com.github.saulocalixto.Invscp.servidor.negocio.endereco.Endereco;
import com.github.saulocalixto.Invscp.servidor.negocio.sala.Sala;

import java.util.List;

/**
 * Created by Marcos Rafael on 29/10/18.
 */
public class Predio extends ModelPadrao {

    private String nome;

    private Endereco endereco;

    private List<Sala> listaDeSalas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Sala> getListaDeSalas() {
        return listaDeSalas;
    }

    public void setListaDeSalas(List<Sala> listaDeSalas) {
        this.listaDeSalas = listaDeSalas;
    }
}
