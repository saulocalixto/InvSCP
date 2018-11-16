package com.github.saulocalixto.Invscp.servidor.controller;

import com.github.saulocalixto.Invscp.servidor.negocio.Login;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoLogin;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class LoginController {

    private ServicoLogin servicoLogin;

    private ServicoLogin servicoLogin() {
        return servicoLogin = servicoLogin != null ? servicoLogin : (servicoLogin = new ServicoLogin());
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<Login> logue(@RequestParam(value="email") String email, @RequestParam(value="senha") String senha) {

        return ResponseEntity.ok(servicoLogin().validaLogin(email, senha));
    }

    @RequestMapping(method= RequestMethod.DELETE)
    public boolean deslogue(@RequestParam(value="tokenAcesso") String token) {
        servicoLogin().deslogar(token);
        return true;
    }
}
