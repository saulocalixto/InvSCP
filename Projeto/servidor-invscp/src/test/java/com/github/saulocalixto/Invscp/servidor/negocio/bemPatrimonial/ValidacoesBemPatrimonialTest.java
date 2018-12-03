package com.github.saulocalixto.Invscp.servidor.negocio.bemPatrimonial;

import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.utilitarios.UtilitarioDaSessao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static com.github.saulocalixto.Invscp.servidor.enumeradores.EnumGrupoDeAcesso.FUNCIONARIO;
import static org.junit.Assert.*;

import static com.github.saulocalixto.Invscp.servidor.enumeradores.EnumGrupoDeAcesso.CHEFE_PATRIMONIO;

/**
 * Created by murillonunes on 02/12/18.
 */
public class ValidacoesBemPatrimonialTest {

    private ValidacoesBemPatrimonial validacao;
    private BemPatrimonial bem;

    @Before
    public void setUp() {
        bem = new BemPatrimonial();
        validacao = new ValidacoesBemPatrimonial();
        validacao.setObjetoValidado(bem);
    }

    @After
    public void tearDown() {}

    @Test
    public void usuarioTemPermissao() {
        UtilitarioDaSessao.setPermissaoDeUsuarioLogado(CHEFE_PATRIMONIO);
        validacao.setObjetoValidado(bem);
        validacao.usuarioTemPermissaoParaAlterarBem();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    /*
    @Test
    public void usuarioNaoTemPermissao() {
        UtilitarioDaSessao.setPermissaoDeUsuarioLogado(FUNCIONARIO);
        validacao.setObjetoValidado(bem);
        validacao.usuarioTemPermissaoParaAlterarBem();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Usuário não tem " +
                "permissão para alterar outro bem patrimonial");
    }
    */

}
