package com.github.saulocalixto.Invscp.servidor.negocio;

import java.util.UUID;

/**
 * Created by Saulo Calixto on 23/10/18.
 */
public class ModelPadrao {

    ModelPadrao() {
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
}
