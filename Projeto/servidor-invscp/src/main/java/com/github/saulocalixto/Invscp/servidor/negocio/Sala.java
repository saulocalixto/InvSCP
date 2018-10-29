package com.github.saulocalixto.Invscp.servidor.negocio;

/**
 * Created by Marcos Rafael on 29/10/18.
 */
public class Sala extends ModelPadrao {

    private String nomeDaSala;

    private Predio predioEmQueEstaLocalizada;

    private Departamento departamentoAQuePertence;

    private boolean eDeletavel;

    public String getNomeDaSala() {
        return nomeDaSala;
    }

    public void setNomeDaSala(String nomeDaSala) {
        this.nomeDaSala = nomeDaSala;
    }

    public Predio getPredioEmQueEstaLocalizada() {
        return predioEmQueEstaLocalizada;
    }

    public void setPredioEmQueEstaLocalizada(Predio predioEmQueEstaLocalizada) {
        this.predioEmQueEstaLocalizada = predioEmQueEstaLocalizada;
    }

    public Departamento getDepartamentoAQuePertence() {
        return departamentoAQuePertence;
    }

    public void setDepartamentoAQuePertence(Departamento departamentoAQuePertence) {
        this.departamentoAQuePertence = departamentoAQuePertence;
    }

    public boolean getEDeletavel() {
        return eDeletavel;
    }

    public void setEDeletavel(boolean eDeletavel) {
        this.eDeletavel = eDeletavel;
    }
}
