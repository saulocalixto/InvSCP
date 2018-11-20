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
    public void tearDown() throws Exception {
    }

    @Test
    public void cpfValido() throws Exception {

        validacao.cpfValido();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);

    }

    @Test
    public void cpfInvalido() throws Exception {
        usuarioTeste.setCpf("12345678910");
        validacao.altereUsuarioValidado(usuarioTeste);
        validacao.cpfValido();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 1);
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Cpf inválido");

    }

}