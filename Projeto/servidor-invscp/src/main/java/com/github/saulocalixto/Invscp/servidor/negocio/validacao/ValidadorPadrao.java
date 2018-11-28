package com.github.saulocalixto.Invscp.servidor.negocio.validacao;

import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumGrupoDeAcesso;
import com.github.saulocalixto.Invscp.servidor.utilitarios.UtilitarioDaSessao;

import java.util.ArrayList;
import java.util.List;

public abstract class ValidadorPadrao<T> {

    private List<Inconsistencia> inconsistencias;
    private Boolean quando;
    private Boolean ehValido;
    private String mensagem;
    private String conceito;
    protected T objetoValidado;
    protected EnumGrupoDeAcesso permissaoDoUsuario;

    public ValidadorPadrao(T objetoValidado) {
        this.objetoValidado = objetoValidado;
        this.permissaoDoUsuario = UtilitarioDaSessao.retornePermissaoDeUsuarioLogado();
        inconsistencias = new ArrayList<>();
    }

    public void altereUsuarioValidado(T objeto) {
        objetoValidado = objeto;
    }

    public Boolean naoHouveInconsistencias() {
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

    public List<Inconsistencia> retorneInconsistencias() {
        return inconsistencias;
    }

    public ValidadorPadrao<T> validarSe(Boolean quando) {
        this.quando = quando;
        return this;
    }

    public ValidadorPadrao<T> ehValidoQuando(Boolean condicao) {
        this.ehValido = condicao;
        return this;
    }

    public ValidadorPadrao<T> comMensagem(String mensagem) {
        this.mensagem = mensagem;
        return this;
    }

    public ValidadorPadrao<T> conceito(String conceito) {
        this.conceito = conceito;
        return this;
    }

    public ValidadorPadrao<T> valide() {
        if(quando) {
            if(ehValido) {
                return this;
            } else {
                adicioneInconsistencia(mensagem, conceito);
            }
        }
        return this;
    }

    private void adicioneInconsistencia(String mensagem, String conceito) {
        Inconsistencia inconsistencia = new Inconsistencia();

        inconsistencia.setConceito(conceito);
        inconsistencia.setMensagem(mensagem);

        inconsistencias.add(inconsistencia);
    }
}