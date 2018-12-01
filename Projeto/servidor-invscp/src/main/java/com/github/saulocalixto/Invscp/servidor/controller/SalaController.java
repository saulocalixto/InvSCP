package com.github.saulocalixto.Invscp.servidor.controller;

import com.github.saulocalixto.Invscp.servidor.negocio.sala.Sala;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoLogin;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoSala;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sala")
public class SalaController extends ControllerPadrao<Sala> {

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity consulta(@RequestHeader String autorizacao, @RequestParam(value="id") String id) {
        if(servicoLogin().tokenValido(autorizacao)) {
            Sala sala = getServico().Consultar(id);
            return retorneObjeto(sala);
        } else {
            return retorneErroDeAutenticacao();
        }
    }

    @RequestMapping(method= RequestMethod.GET, value = "consulteTodos")
    public ResponseEntity consultaTodos(@RequestHeader String autorizacao) {
        if(servicoLogin().tokenValido(autorizacao)) {
            List<Sala> salas = getServico().ConsultarLista();
            return retorneObjeto(salas);
        } else {
            return retorneErroDeAutenticacao();
        }
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity salva(@RequestHeader String autorizacao, @RequestBody Sala sala) {
        if(servicoLogin().tokenValido(autorizacao)) {
            List<Inconsistencia> inconsistencias = getServico().Salvar(sala);
            return retorneInconsistencias(inconsistencias);

        } else {
            return retorneErroDeAutenticacao();
        }
    }

    @RequestMapping(method= RequestMethod.DELETE)
    public ResponseEntity exclue(@RequestHeader String autorizacao, @RequestParam(value="id") String id) {
        if(servicoLogin().tokenValido(autorizacao)) {
            List<Inconsistencia> inconsistencias = getServico().Excluir(id);
            return retorneInconsistencias(inconsistencias);
        } else {
            return retorneErroDeAutenticacao();
        }
    }

    @RequestMapping(method= RequestMethod.PUT, value = "atualize")
    public ResponseEntity atualiza(@RequestHeader String autorizacao, @RequestBody Sala sala) {
        if(servicoLogin().tokenValido(autorizacao)) {
            List<Inconsistencia> inconsistencias = getServico().Atualizar(sala);
            return retorneInconsistencias(inconsistencias);
        } else {
            return retorneErroDeAutenticacao();
        }
    }
}
