package com.github.saulocalixto.Invscp.servidor.negocio.ordemDeServico;

import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by murillonunes on 03/12/18.
 */
public class ValidacoesOrdemDeServicoTest {

    private OrdemDeServico osTeste;
    private ValidacoesOrdemDeServico validacao;

    @Before
    public void setUp() {
        osTeste = new OrdemDeServico();
        validacao = new ValidacoesOrdemDeServico();
    }

    @After
    public void tearDown() {}

    @Test
    public void identificadorInformado() {
        osTeste.setIdentificadorDaOS("100");
        validacao.setObjetoValidado(osTeste);
        validacao.identificadorDaOSDeveSerInformado();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void identificadorNaoInformado() {
        osTeste.setIdentificadorDaOS("");
        validacao.setObjetoValidado(osTeste);
        validacao.identificadorDaOSDeveSerInformado();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Identificador Da O.S. " +
                "deve ser informado");
    }

    @Test
    public void bemInformado() {
        osTeste.setBem("Bem Patrimonial de Teste");
        validacao.setObjetoValidado(osTeste);
        validacao.bemDeveSerInformado();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void bemNaoInformado() {
        osTeste.setBem("");
        validacao.setObjetoValidado(osTeste);
        validacao.bemDeveSerInformado();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Bem Patrimonial deve " +
                "ser informado");
    }

    @Test
    public void motivoInformado() {
        osTeste.setMotivo("Motivo de Teste");
        validacao.setObjetoValidado(osTeste);
        validacao.motivoDeveSerInformado();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void motivoNaoInformado() {
        osTeste.setMotivo("");
        validacao.setObjetoValidado(osTeste);
        validacao.motivoDeveSerInformado();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Motivo deve ser " +
                "informado");
    }

    @Test
    public void dataDeAberturaInformada() {
        osTeste.setDataAbertura("02/12/2018");
        validacao.setObjetoValidado(osTeste);
        validacao.dataAberturaDeveSerInformado();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void dataDeAberturaNaoInformada() {
        osTeste.setDataAbertura("");
        validacao.setObjetoValidado(osTeste);
        validacao.dataAberturaDeveSerInformado();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Data de abertura deve " +
                "ser informada");
    }

}
