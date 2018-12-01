package com.github.saulocalixto.Invscp.servidor.utilitarios;

import com.github.saulocalixto.Invscp.servidor.servico.ServicoPadrao;

public class FabricaDeServicos<T extends ServicoPadrao> {

    public Class<T> servico;

    public FabricaDeServicos(final Class<T> servico) {
        this.servico = servico;
    }

    public T crie() {
        try {
            T handler = servico.newInstance();
            return handler;
        } catch(IllegalAccessException e) {
            System.out.println("Erro ao acessar a instância de um objeto.");
        } catch(InstantiationException f) {
            System.out.println("Erro ao instanciar um objeto ");
        }

        return null;
    }
}