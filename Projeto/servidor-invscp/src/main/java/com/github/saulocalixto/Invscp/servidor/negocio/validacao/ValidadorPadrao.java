package com.github.saulocalixto.Invscp.servidor.negocio.validacao;

import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumGrupoDeAcesso;
import com.github.saulocalixto.Invscp.servidor.utilitarios.UtilitarioDaSessao;

import java.lang.reflect.ParameterizedType;
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

    public ValidadorPadrao() {
        this.permissaoDoUsuario = UtilitarioDaSessao.retornePermissaoDeUsuarioLogado();
        inconsistencias = new ArrayList<>();
    }

    public void setObjetoValidado(T objetoValidado) {
        inconsistencias = new ArrayList<>();
        try {
            this.objetoValidado = objetoValidado != null ? objetoValidado : (T) ((Class)((ParameterizedType)this.getClass().
                    getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
        }
        catch(IllegalAccessException | InstantiationException e) {
            System.out.println("Erro ao instanciar classe:" + e.getMessage());
        }
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
        conceitoExcluidoExiste();
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

    public void conceitoExcluidoExiste() {
        this.conceito("Objeto que você acabou de tentar excluir")
                .validarSe(objetoValidado == null)
                .ehValidoQuando(objetoValidado != null)
                .comMensagem("Conceito não existe")
                .valide();
    }

    private void adicioneInconsistencia(String mensagem, String conceito) {
        Inconsistencia inconsistencia = new Inconsistencia();

        inconsistencia.setConceito(conceito);
        inconsistencia.setMensagem(mensagem);

        inconsistencias.add(inconsistencia);
    }
}
