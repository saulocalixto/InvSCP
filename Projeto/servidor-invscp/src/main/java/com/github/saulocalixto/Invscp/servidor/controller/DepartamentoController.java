package com.github.saulocalixto.Invscp.servidor.controller;

import com.github.saulocalixto.Invscp.servidor.negocio.departamento.Departamento;
import com.github.saulocalixto.Invscp.servidor.negocio.usuario.Usuario;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoDepartamento;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoLogin;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {
    private ServicoDepartamento servicoDepartamento;
    private ServicoLogin servicoLogin;

    private ServicoLogin servicoLogin() {
        return servicoLogin = servicoLogin != null ? servicoLogin : (servicoLogin = new ServicoLogin());
    }

    private ServicoDepartamento servicoDepartamento() {
        FabricaDeServicos<ServicoDepartamento> fabrica = new FabricaDeServicos(ServicoDepartamento.class);
        return servicoDepartamento != null ? servicoDepartamento : (servicoDepartamento = fabrica.crie());
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity consulta(@RequestHeader String autorizacao, @RequestParam(value="id") String id) {
        if(servicoLogin().tokenValido(autorizacao)) {
            Departamento departamento = servicoDepartamento().Consultar(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(departamento);
        } else {
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body("Usuário não autenticado.");
        }
    }

    @RequestMapping(method= RequestMethod.GET, value = "consulteTodos")
    public ResponseEntity consultaTodos(@RequestHeader String autorizacao) {
        if(servicoLogin().tokenValido(autorizacao)) {
            List<Departamento> departamentos = servicoDepartamento().ConsultarLista();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(departamentos);
        } else {
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body("Usuário não autenticado.");
        }
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity salva(@RequestHeader String autorizacao, @RequestBody Departamento departamento) {
        if(servicoLogin().tokenValido(autorizacao)) {
            List<Inconsistencia> inconsistencias = servicoDepartamento().Salvar(departamento);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(inconsistencias);
        } else {
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body("Usuário não autenticado.");
        }
    }

    @RequestMapping(method= RequestMethod.DELETE)
    public ResponseEntity exclue(@RequestHeader String autorizacao, @RequestParam(value="id") String id) {
        if(servicoLogin().tokenValido(autorizacao)) {
            servicoDepartamento().Excluir(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body("Usuário não autenticado.");
        }
    }

    @RequestMapping(method= RequestMethod.PUT, value = "atualize")
    public ResponseEntity atualiza(@RequestHeader String autorizacao, @RequestBody Departamento departamento) {
        if(servicoLogin().tokenValido(autorizacao)) {
            List<Inconsistencia> inconsistencias = servicoDepartamento().Atualizar(departamento);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(inconsistencias);
        } else {
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body("Usuário não autenticado.");
        }
    }
}
