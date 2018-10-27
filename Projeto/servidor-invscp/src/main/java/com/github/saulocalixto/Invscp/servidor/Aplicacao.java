package com.github.saulocalixto.Invscp.servidor;

import com.github.saulocalixto.Invscp.servidor.negocio.Usuario;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoUsuario;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Saulo Calixto on 23/10/18.
 */
@SpringBootApplication
public class Aplicacao {
    private static ServicoUsuario servicoUsuario;
    public static void main(String[] args) {
        SpringApplication.run(Aplicacao.class, args);
    }
}

