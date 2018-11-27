package com.github.saulocalixto.Invscp.servidor.negocio.usuario;

import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by murillonunes on 20/11/18.
 */
public class ValidacoesUsuarioTest {

    private ValidacoesUsuario validacao;

    private Usuario usuarioTeste;

    @Before
    public void setUp() throws Exception {

        usuarioTeste = new Usuario();
        usuarioTeste.setCpf("00818193158");
        usuarioTeste.setEmail("joaodasilva@invscp.com.br");
        usuarioTeste.setGrupo("ADMINISTRADOR_DEPARTAMENTO");
        usuarioTeste.setNome("Joao da Silva");
        usuarioTeste.setSenha("123456");
        validacao = new ValidacoesUsuario(usuarioTeste);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void cpfValido() {

        validacao.cpfValido();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);

    }

    @Test
    public void cpfInvalido() {
        usuarioTeste.setCpf("12345678910");
        validacao.altereUsuarioValidado(usuarioTeste);
        validacao.cpfValido();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 1);
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Cpf inválido");
    }

    @Test
    public void emailNaoInformado() {
        usuarioTeste.setCpf("00818193158");
        usuarioTeste.setEmail("");
        validacao.altereUsuarioValidado(usuarioTeste);
        validacao.emailObrigatorio();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 1);
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "E-mail não informado");
    }

    @Test
    public void emailValido() {
        usuarioTeste.setEmail("joaodasilva@invscp.com.br");
        validacao.altereUsuarioValidado(usuarioTeste);
        validacao.emailValido();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void senhaInvalida() {
        usuarioTeste.setSenha("12345");
        validacao.altereUsuarioValidado(usuarioTeste);
        validacao.senhaValida();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 1);
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Senha deve ter no " +
                "mínimo 6 dígitos");
    }

    @Test
    public void senhaValida() {
        usuarioTeste.setSenha("123456");
        validacao.altereUsuarioValidado(usuarioTeste);
        validacao.senhaValida();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void senhaNula() {
        usuarioTeste.setSenha("");
        validacao.altereUsuarioValidado(usuarioTeste);
        validacao.senhaObrigatoria();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 1);
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Senha não informada");
    }

}