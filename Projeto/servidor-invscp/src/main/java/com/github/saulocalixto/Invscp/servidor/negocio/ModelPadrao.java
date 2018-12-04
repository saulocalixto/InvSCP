package com.github.saulocalixto.Invscp.servidor.negocio;

import java.util.UUID;

/**
 * Created by Saulo Calixto on 23/10/18.
 */
public class ModelPadrao {

    public ModelPadrao() {
        id = getRandomID();
    }

    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    protected String getRandomID() { return UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof ModelPadrao))
            return false;
        if (obj == this)
            return true;
        return this.getId().equals(((ModelPadrao) obj).getId());
    }
}
