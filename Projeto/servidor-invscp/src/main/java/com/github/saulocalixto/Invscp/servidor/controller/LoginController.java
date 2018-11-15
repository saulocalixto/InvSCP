package com.github.saulocalixto.Invscp.servidor.controller;

import com.github.saulocalixto.Invscp.servidor.negocio.Login;
import com.github.saulocalixto.Invscp.servidor.negocio.Usuario;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoLogin;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class LoginController {

    private ServicoLogin servicoLogin;

    private ServicoLogin servicoLogin() {
        FabricaDeServicos<ServicoLogin> fabrica = new FabricaDeServicos(ServicoLogin.class);
        return servicoLogin != null ? servicoLogin : (servicoLogin = fabrica.crie());
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<Login> consulta(@RequestParam(value="email") String email, @RequestParam(value="senha") String senha) {

        return ResponseEntity.ok(servicoLogin().validaLogin(email, senha));
    }

    @RequestMapping(method= RequestMethod.DELETE)
    public boolean exclue(@RequestParam(value="id") String id) {
        servicoLogin().Excluir(id);
        return true;
    }
}
