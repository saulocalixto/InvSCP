package com.github.saulocalixto.Invscp.servidor.controller;

import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoLogin;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoPadrao;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoSala;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class ControllerPadrao<T> {

    private ServicoLogin servicoLogin;
    private ServicoPadrao<T> servico;
    private JSONObject json;

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity consulta(@RequestHeader String autorizacao, @RequestParam(value="id") String id) {
        return consultarConceito(autorizacao, id);
    }

    @RequestMapping(method= RequestMethod.GET, value = "consulteTodos")
    public ResponseEntity consultaTodos(@RequestHeader String autorizacao) {
        return consultarTodos(autorizacao);
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity salva(@RequestHeader String autorizacao, @RequestBody T objeto) {
        return salvarConceito(autorizacao, objeto);
    }

    @RequestMapping(method= RequestMethod.DELETE)
    public ResponseEntity exclue(@RequestHeader String autorizacao, @RequestParam(value="id") String id) {
        return deletarConceito(autorizacao, id);
    }

    @RequestMapping(method= RequestMethod.PUT, value = "atualize")
    public ResponseEntity atualiza(@RequestHeader String autorizacao, @RequestBody T objeto) {
        return atualizarConceito(autorizacao, objeto);
    }

    protected ServicoLogin servicoLogin() {
        return servicoLogin = servicoLogin != null ? servicoLogin : (servicoLogin = new ServicoLogin());
    }

    protected ServicoPadrao<T> getServico() {
        FabricaDeServicos<ServicoSala> fabrica = new FabricaDeServicos(ServicoPadrao.class);
        return servico != null ? servico : (servico = (ServicoPadrao<T>) fabrica.crie());
    }

    protected String toJson(T objeto) {
        return GsonCreator().toJson(objeto);
    }

    protected String toListJson(List<T> objetos) {
        return GsonCreator().toJson(objetos);
    }

    protected String toJson(List<Inconsistencia> inconsistencias) {
        return GsonCreator().toJson(inconsistencias);
    }

    protected ResponseEntity retorneInconsistencias(List<Inconsistencia> inconsistencias) {
        json = new org.json.JSONObject();
        try {
            json.put("erro", toJson(inconsistencias));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(json.toString());
    }

    protected ResponseEntity retorneErroDeAutenticacao() {
        json = new org.json.JSONObject();
        try {
            json.put("erro", "Usuário não autenticado.");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body(json.toString());
    }

    protected ResponseEntity retorneObjeto(T objeto) {
        json = new org.json.JSONObject();
        try {
            json.put("data", toJson(objeto));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(json.toString());
    }

    protected ResponseEntity retorneObjeto(List<T> objetos) {
        json = new org.json.JSONObject();
        try {
            json.put("data", toListJson(objetos));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(json.toString());
    }

    protected ResponseEntity consultarConceito(String autorizacao, String id) {
        if(servicoLogin().tokenValido(autorizacao)) {
            T objeto = getServico().Consultar(id);
            return retorneObjeto(objeto);
        } else {
            return retorneErroDeAutenticacao();
        }
    }

    protected ResponseEntity consultarTodos(String autorizacao) {
        if(servicoLogin().tokenValido(autorizacao)) {
            List<T> objetos = getServico().ConsultarLista();
            return retorneObjeto(objetos);
        } else {
            return retorneErroDeAutenticacao();
        }
    }

    protected ResponseEntity salvarConceito(String autorizacao, T objeto) {
        if(servicoLogin().tokenValido(autorizacao)) {
            List<Inconsistencia> inconsistencias = getServico().Salvar(objeto);
            return retorneInconsistencias(inconsistencias);

        } else {
            return retorneErroDeAutenticacao();
        }
    }

    protected ResponseEntity deletarConceito(String autorizacao, String id) {
        if(servicoLogin().tokenValido(autorizacao)) {
            List<Inconsistencia> inconsistencias = getServico().Excluir(id);
            return retorneInconsistencias(inconsistencias);
        } else {
            return retorneErroDeAutenticacao();
        }
    }

    protected ResponseEntity atualizarConceito(String autorizacao, T objeto) {
        if(servicoLogin().tokenValido(autorizacao)) {
            List<Inconsistencia> inconsistencias = getServico().Atualizar(objeto);
            return retorneInconsistencias(inconsistencias);
        } else {
            return retorneErroDeAutenticacao();
        }
    }

    private Gson GsonCreator() {
        return new Gson();
    }
}
