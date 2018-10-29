package com.github.saulocalixto.Invscp.servidor.negocio;

/**
 * Created by Marcos Rafael on 29/10/18.
 */
public class Departamento extends ModelPadrao {

    private String nomeDoDepartamento;

    private boolean eDeletavel;

    public String getNomeDoDepartamento() {
        return nomeDoDepartamento;
    }

    public void setNomeDoDepartamento(String nomeDoDepartamento) {
        this.nomeDoDepartamento = nomeDoDepartamento;
    }

    public boolean getEDeletavel() {
        return eDeletavel;
    }

    public void setEDeletavel(boolean eDeletavel) {
        this.eDeletavel = eDeletavel;
    }

}
