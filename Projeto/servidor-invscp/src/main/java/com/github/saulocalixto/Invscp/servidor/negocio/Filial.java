package com.github.saulocalixto.Invscp.servidor.negocio;

/**
 * Created by Marcos Rafael on 29/10/18.
 */
public class Filial extends ModelPadrao {

    private String nomeDaFilial;
    
    private boolean eDeletavel;


    public String getNomeDaFilial() {
        return nomeDaFilial;
    }

    public void setNomeDaFilial(String nomeDaFilial) {
        this.nomeDaFilial = nomeDaFilial;
        
    }
    
    public boolean getEDeletavel() {
        return eDeletavel;
    }

    public void setEDeletavel(boolean eDeletavel) {
        this.eDeletavel = eDeletavel;
    }
}
