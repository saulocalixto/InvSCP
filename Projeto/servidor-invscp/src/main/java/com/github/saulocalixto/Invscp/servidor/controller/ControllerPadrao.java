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

import java.util.List;

public abstract class ControllerPadrao<T> {

    private ServicoLogin servicoLogin;
    private ServicoPadrao<T> servico;
    private JSONObject json;

    protected ServicoLogin servicoLogin() {
        return servicoLogin = servicoLogin != null ? servicoLogin : (servicoLogin = new ServicoLogin());
    }

    protected ServicoPadrao<T> getServico() {
        FabricaDeServicos<ServicoSala> fabrica = new FabricaDeServicos(ServicoSala.class);
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

    private Gson GsonCreator() {
        return new Gson();
    }
}
