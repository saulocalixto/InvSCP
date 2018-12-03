package com.github.saulocalixto.Invscp.servidor.negocio.departamento;

import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

/**
 * Created by murillonunes on 20/11/18.
 */
public class ValidacoesDepartamentoTest {

    private Departamento departamentoTeste;
    private ValidacoesDepartamento validacao;

    @Before
    public void setUp() {
        departamentoTeste = new Departamento();
        validacao = new ValidacoesDepartamento();
    }

    @After
    public void tearDown() {}

    @Test
    public void nomeDoDepartamentoInformado() {
        departamentoTeste.setNomeDoDepartamento("Departamento de Teste 1");
        validacao.setObjetoValidado(departamentoTeste);
        validacao.nomeObrigatorio();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void nomeDoDepartamentoNaoInformado() {
        departamentoTeste.setNomeDoDepartamento("");
        validacao.setObjetoValidado(departamentoTeste);
        validacao.nomeObrigatorio();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 1);
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Nome do departamento " +
                "não informado");
    }

}
