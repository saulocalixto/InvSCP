package com.github.saulocalixto.Invscp.servidor.negocio;

import com.github.saulocalixto.Invscp.servidor.negocio.endereco.Endereco;
import com.github.saulocalixto.Invscp.servidor.negocio.sala.Sala;

import java.util.List;

/**
 * Created by Marcos Rafael on 29/10/18.
 */
public class Predio extends ModelPadrao {

    private String nomeDoPredio;

    private Endereco enderecoDoPredio;

    private Filial filialAQuePertence;

    private List<Sala> listaDeSalas;

    public String getNomeDoPredio() {
        return nomeDoPredio;
    }

    public void setNomeDoPredio(String nomeDoPredio) {
        this.nomeDoPredio = nomeDoPredio;
    }

    public Endereco getEnderecoDoPredio() {
        return enderecoDoPredio;
    }

    public void setEnderecoDoPredio(Endereco enderecoDoPredio) {
        this.enderecoDoPredio = enderecoDoPredio;
    }

    public Filial getFilialAQuePertence() {
        return filialAQuePertence;
    }

    public void setFilialAQuePertence(Filial filialAQuePertence) {
        this.filialAQuePertence = filialAQuePertence;
    }
}
