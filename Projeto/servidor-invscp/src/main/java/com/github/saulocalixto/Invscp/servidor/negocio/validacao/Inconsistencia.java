package com.github.saulocalixto.Invscp.servidor.negocio.validacao;

public class Inconsistencia {
    private String mensagem;
    private String conceito;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getConceito() {
        return conceito;
    }

    public void setConceito(String conceito) {
        this.conceito = conceito;
    }
}
