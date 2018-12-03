package com.github.saulocalixto.Invscp.servidor.negocio.sala;

import com.github.saulocalixto.Invscp.servidor.negocio.ModelPadrao;

/**
 * Created by Marcos Rafael on 29/10/18.
 */
public class Sala extends ModelPadrao {

    private String numeroSala;

    private String idPredio;

    private String idDepartamento;

    public String getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(String numeroSala) {
        this.numeroSala = numeroSala;
    }

    public String getIdPredio() {
        return idPredio;
    }

    public void setIdPredio(String idPredio) {
        this.idPredio = idPredio;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
}
