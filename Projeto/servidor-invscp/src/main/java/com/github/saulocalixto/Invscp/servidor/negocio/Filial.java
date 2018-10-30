package com.github.saulocalixto.Invscp.servidor.negocio;

/**
 * Created by Marcos Rafael on 29/10/18.
 */
public class Filial extends CRUDModelPadrao {

    private String nomeDaFilial;

    public Filial (String nomeDaFilial){
        this.nomeDaFilial = nomeDaFilial;
    }

    public String getNomeDaFilial() {
        return nomeDaFilial;
    }

    public void setNomeDaFilial(String nomeDaFilial) {
        this.nomeDaFilial = nomeDaFilial;
    }
}
