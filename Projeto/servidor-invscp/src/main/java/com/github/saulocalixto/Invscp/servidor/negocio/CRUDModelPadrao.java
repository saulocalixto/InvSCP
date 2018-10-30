package com.github.saulocalixto.Invscp.servidor.negocio;

/**
 * Created by Marcos Rafael on 30/10/18.
 */
public class CRUDModelPadrao extends ModelPadrao {

    private boolean eDeletavel;

    public boolean isEDeletavel() {
        return eDeletavel;
    }

    public void setEDeletavel(boolean eDeletavel) {
        this.eDeletavel = eDeletavel;
    }
}