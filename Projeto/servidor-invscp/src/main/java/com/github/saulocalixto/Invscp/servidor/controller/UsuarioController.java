package com.github.saulocalixto.Invscp.servidor.controller;

import com.github.saulocalixto.Invscp.servidor.negocio.usuario.Usuario;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoLogin;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoUsuario;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Saulo on 23/10/18.
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private ServicoUsuario servicoUsuario;
    private ServicoLogin servicoLogin;

    private ServicoUsuario servicoUsuario() {
            FabricaDeServicos<ServicoUsuario> fabrica = new FabricaDeServicos(ServicoUsuario.class);
            return servicoUsuario != null ? servicoUsuario : (servicoUsuario = fabrica.crie());
    }

    private ServicoLogin servicoLogin() {
        return servicoLogin = servicoLogin != null ? servicoLogin : (servicoLogin = new ServicoLogin());
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity consulta(@RequestHeader String autorizacao, @RequestParam(value="email") String email) {
        if(servicoLogin().tokenValido(autorizacao)) {
            Usuario usuario = servicoUsuario().consultarPorEmail(email);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body("Usuário não autenticado.");
        }
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity salva(@RequestHeader String autorizacao, @RequestBody Usuario usuario) {
        if(true) {
            List<Inconsistencia> inconsistencias = servicoUsuario().Salvar(usuario);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(inconsistencias);
        } else {
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body("Usuário não autenticado.");
        }
    }

    @RequestMapping(method= RequestMethod.DELETE)
    public ResponseEntity exclue(@RequestHeader String autorizacao, @RequestParam(value="id") String id) {
        if(servicoLogin().tokenValido(autorizacao)) {
            servicoUsuario().Excluir(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body("Usuário não autenticado.");
        }
    }

    @RequestMapping(method= RequestMethod.PUT, value = "atualize")
    public ResponseEntity atualiza(@RequestHeader String autorizacao, @RequestBody Usuario usuario) {
        if(servicoLogin().tokenValido(autorizacao)) {
            List<Inconsistencia> inconsistencias = servicoUsuario().Atualizar(usuario);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(inconsistencias);
        } else {
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body("Usuário não autenticado.");
        }
    }
}
