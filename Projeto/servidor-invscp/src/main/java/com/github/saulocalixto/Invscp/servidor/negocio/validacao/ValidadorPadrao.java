package com.github.saulocalixto.Invscp.servidor.negocio.validacao;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class ValidadorPadrao<T> {

    private List<Inconsistencia> inconsistencias;
    protected T objetoValidado;

    public ValidadorPadrao(T objetoValidado) {
        this.objetoValidado = objetoValidado;
        inconsistencias = new ArrayList<>();
    }

    public Boolean ehValido() {
        return inconsistencias.size() == 0;
    }

    public List<Inconsistencia> ValideInclusao () {

        return inconsistencias;
    }

    public List<Inconsistencia> ValideAtualizacao () {

        return inconsistencias;
    }

    public List<Inconsistencia> ValideExclusao () {

        return inconsistencias;
    }

    protected void adicioneInconsistencia(String mensagem, String conceito) {
        Inconsistencia inconsistencia = new Inconsistencia();

        inconsistencia.setConceito(conceito);
        inconsistencia.setMensagem(mensagem);

        inconsistencias.add(inconsistencia);
    }
}
