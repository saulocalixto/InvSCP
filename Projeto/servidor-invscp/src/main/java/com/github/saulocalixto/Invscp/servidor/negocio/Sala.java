package com.github.saulocalixto.Invscp.servidor.negocio;

/**
 * Created by Marcos Rafael on 29/10/18.
 */
public class Sala extends CRUDModelPadrao {

    private String nomeDaSala;

    private Predio predioEmQueEstaLocalizada;

    private Departamento departamentoAQuePertence;

    public Sala (String nomeDaSala, Predio predioEmQueEstaLocalizada, Departamento departamentoAQuePertence){
        this.nomeDaSala = nomeDaSala;
        this.predioEmQueEstaLocalizada = predioEmQueEstaLocalizada;
        this.departamentoAQuePertence = departamentoAQuePertence;
    }

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
}
