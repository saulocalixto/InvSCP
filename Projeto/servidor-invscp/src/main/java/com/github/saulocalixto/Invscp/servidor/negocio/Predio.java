package com.github.saulocalixto.Invscp.servidor.negocio;

/**
 * Created by Marcos Rafael on 29/10/18.
 */
public class Predio extends ModelPadrao {

    private String nomeDoPredio;

    private String enderecoDoPredio;

    private Filial filialAQuePertence;

    private boolean eDeletavel;

    public String getNomeDoPredio() {
        return nomeDoPredio;
    }

    public void setNomeDoPredio(String nomeDoPredio) {
        this.nomeDoPredio = nomeDoPredio;
    }

    public String getEnderecoDoPredio() {
        return enderecoDoPredio;
    }

    public void setEnderecoDoPredio(String enderecoDoPredio) {
        this.enderecoDoPredio = enderecoDoPredio;
    }

    public Filial getFilialAQuePertence() {
        return filialAQuePertence;
    }

    public void setFilialAQuePertence(Predio filialAQuePertence) {
        this.filialAQuePertence = filialAQuePertence;
    }

    public boolean getEDeletavel() {
        return eDeletavel;
    }

    public void setEDeletavel(boolean eDeletavel) {
        this.eDeletavel = eDeletavel;
    }
}
