package com.github.saulocalixto.Invscp.servidor.controller;

import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoLogin;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoPadrao;
import com.google.gson.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class ControllerPadrao<T> {

    private ServicoLogin servicoLogin;
    protected ServicoPadrao<T> servico;
    private JsonObject json;

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

    protected abstract ServicoPadrao<T> getServico();

    protected JsonElement toJson(T objeto) {
        return GsonCreator().toJsonTree(objeto);
    }

    protected JsonArray toListJson(List<T> objetos) {
        JsonArray array = new JsonArray();
        for (T objeto: objetos) {
            array.add(toJson(objeto));
        }
        return array;
    }

    protected String toJson(List<Inconsistencia> inconsistencias) {
        return GsonCreator().toJson(inconsistencias);
    }

    protected ResponseEntity retorneInconsistencias(List<Inconsistencia> inconsistencias) {
        json = new JsonObject();
        try {
            json.add("erro", toJson((T) inconsistencias));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("content-type", "application/json;charset=UTF-8")
                .body(json.toString());
    }

    protected ResponseEntity retorneErroDeAutenticacao() {
        json = new JsonObject();
        try {
            json.add("erro", toJson((T) "Usuário não autenticado."));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
                .header("content-type", "application/json;charset=UTF-8")
                .body(json.toString());
    }

    protected ResponseEntity retorneObjeto(T objeto) {
        json = new JsonObject();
        try {
            json.add("data", toJson(objeto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("content-type", "application/json;charset=UTF-8")
                .body(json.toString());
    }

    protected ResponseEntity retorneObjeto(List<T> objetos) {
        json = new JsonObject();
        try {
            json.add("data", toListJson(objetos));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("content-type", "application/json;charset=UTF-8")
                .body(json.toString());
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
