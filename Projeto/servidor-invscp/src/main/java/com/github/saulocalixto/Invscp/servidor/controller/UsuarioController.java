package com.github.saulocalixto.Invscp.servidor.controller;

import com.github.saulocalixto.Invscp.servidor.negocio.Login;
import com.github.saulocalixto.Invscp.servidor.negocio.usuario.Usuario;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoLogin;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoUsuario;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;
import com.github.saulocalixto.Invscp.servidor.utilitarios.UtilitarioDaSessao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Saulo on 23/10/18.
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController extends ControllerPadrao<Usuario> {

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity consulta(@RequestHeader String autorizacao, @RequestParam(value="email") String email) {
        if(servicoLogin().tokenValido(autorizacao)) {
            Usuario usuario = ((ServicoUsuario)getServico()).consultarPorEmail(email);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body("Usuário não autenticado.");
        }
    }
}
