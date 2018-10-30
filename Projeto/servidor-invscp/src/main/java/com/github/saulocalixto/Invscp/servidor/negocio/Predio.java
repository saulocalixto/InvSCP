package com.github.saulocalixto.Invscp.servidor.negocio;

/**
 * Created by Marcos Rafael on 29/10/18.
 */
public class Predio extends CRUDModelPadrao {

    private String nomeDoPredio;

    private String enderecoDoPredio;

    private Filial filialAQuePertence;

    public Predio (String nomeDoPredio, String enderecoDoPredio, Filial filialAQuePertence){
        this.nomeDoPredio = nomeDoPredio;
        this.enderecoDoPredio = enderecoDoPredio;
        this. filialAQuePertence = filialAQuePertence;
    }

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

    public void setFilialAQuePertence(Filial filialAQuePertence) {
        this.filialAQuePertence = filialAQuePertence;
    }
}
