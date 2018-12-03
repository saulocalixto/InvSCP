package com.github.saulocalixto.Invscp.servidor.negocio.usuario;

import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static com.github.saulocalixto.Invscp.servidor.enumeradores.EnumGrupoDeAcesso.CHEFE_PATRIMONIO;
import static org.junit.Assert.*;

/**
 * Created by murillonunes on 20/11/18.
 */
public class ValidacoesUsuarioTest {

    private ValidacoesUsuario validacao;

    private Usuario usuarioTeste;

    @Before
    public void setUp() {
        usuarioTeste = new Usuario();
        usuarioTeste.setCpf("00818193158");
        usuarioTeste.setEmail("joaodasilva@invscp.com.br");
        usuarioTeste.setGrupo("ADMINISTRADOR_DEPARTAMENTO");
        usuarioTeste.setNome("Joao da Silva");
        usuarioTeste.setSenha("123456");
        validacao = new ValidacoesUsuario();
        validacao.setObjetoValidado(usuarioTeste);
    }

    @After
    public void tearDown() {}

    @Test
    public void cpfValido() {
        validacao.cpfValido();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void cpfInvalido() {
        usuarioTeste.setCpf("12345678910");
        validacao.setObjetoValidado(usuarioTeste);
        validacao.cpfValido();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 1);
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Cpf inválido");
    }

    @Test
    public void cpfInformado() {
        usuarioTeste.setCpf("23343003140");
        validacao.setObjetoValidado(usuarioTeste);
        validacao.cpfObrigatorio();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void cpfNaoInformado() {
        usuarioTeste.setCpf("");
        validacao.setObjetoValidado(usuarioTeste);
        validacao.cpfObrigatorio();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 1);
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Cpf não informado");
    }

    @Test
    public void emailInformado() {
        usuarioTeste.setCpf("00818193158");
        usuarioTeste.setEmail("joaodasilva@invscp.com.br");
        validacao.setObjetoValidado(usuarioTeste);
        validacao.emailObrigatorio();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void emailNaoInformado() {
        usuarioTeste.setCpf("00818193158");
        usuarioTeste.setEmail("");
        validacao.setObjetoValidado(usuarioTeste);
        validacao.emailObrigatorio();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 1);
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "E-mail não informado");
    }

    @Test
    public void emailValido() {
        usuarioTeste.setEmail("joaodasilva@invscp.com.br");
        validacao.setObjetoValidado(usuarioTeste);
        validacao.emailValido();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void emailInvalido() {
        usuarioTeste.setEmail("joaodasilva.invscp.com.br");
        validacao.setObjetoValidado(usuarioTeste);
        validacao.emailValido();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 1);
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "E-mail inválido");
    }

    @Test
    public void senhaInvalida() {
        usuarioTeste.setSenha("12345");
        validacao.setObjetoValidado(usuarioTeste);
        validacao.senhaValida();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 1);
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Senha deve ter no " +
                "mínimo 6 dígitos");
    }

    @Test
    public void senhaValida() {
        usuarioTeste.setSenha("123456");
        validacao.setObjetoValidado(usuarioTeste);
        validacao.senhaValida();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void senhaInformada() {
        usuarioTeste.setSenha("98765431");
        validacao.setObjetoValidado(usuarioTeste);
        validacao.senhaObrigatoria();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void senhaNaoInformada() {
        usuarioTeste.setSenha("");
        validacao.setObjetoValidado(usuarioTeste);
        validacao.senhaObrigatoria();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 1);
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Senha não informada");
    }

    @Test
    public void grupoInformado() {
        usuarioTeste.setGrupo(CHEFE_PATRIMONIO);
        validacao.setObjetoValidado(usuarioTeste);
        validacao.grupoObrigatorio();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void grupoNaoInformado() {
        usuarioTeste.setGrupo("");
        validacao.setObjetoValidado(usuarioTeste);
        validacao.grupoObrigatorio();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Grupo não informado " +
                "ou inválido");
    }

}