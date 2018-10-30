package com.github.saulocalixto.Invscp.servidor.negocio;

/**
 * Created by Marcos Rafael on 29/10/18.
 */
public class Departamento extends CRUDModelPadrao {

    private String nomeDoDepartamento;

    public Departamento (String NomeDoDepartamento) {
        this.nomeDoDepartamento = nomeDoDepartamento;
    }

    public String getNomeDoDepartamento() {
        return nomeDoDepartamento;
    }

    public void setNomeDoDepartamento(String nomeDoDepartamento) {
        this.nomeDoDepartamento = nomeDoDepartamento;
    }
}
