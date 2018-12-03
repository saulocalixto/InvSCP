package com.github.saulocalixto.Invscp.servidor.negocio.endereco;

import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by murillonunes on 03/12/18.
 */
public class ValidacoesEnderecoTest {

    private Endereco enderecoTeste;
    private ValidacoesEndereco validacao;

    @Before
    public void setUp() {
        enderecoTeste = new Endereco();
        validacao = new ValidacoesEndereco();
    }

    @After
    public void tearDown() {}

    @Test
    public void bairroInformado() {
        enderecoTeste.setBairro("Goiânia II");
        validacao.setObjetoValidado(enderecoTeste);
        validacao.bairroDeveSerInformado();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void bairroNaoInformado() {
        enderecoTeste.setBairro("");
        validacao.setObjetoValidado(enderecoTeste);
        validacao.bairroDeveSerInformado();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 1);
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Bairro deve " +
                "ser informado");
    }

    @Test
    public void cidadeInformada() {
        enderecoTeste.setCidade("Goiânia");
        validacao.setObjetoValidado(enderecoTeste);
        validacao.cidadeDeveSerInformado();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void cidadeNaoInformada() {
        enderecoTeste.setCidade("");
        validacao.setObjetoValidado(enderecoTeste);
        validacao.cidadeDeveSerInformado();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 1);
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Cidade deve " +
                "ser informada");
    }

    @Test
    public void cepInformado() {
        enderecoTeste.setCep("75620000");
        validacao.setObjetoValidado(enderecoTeste);
        validacao.cepDeveSerInformado();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void cepNaoInformado() {
        enderecoTeste.setCep("");
        validacao.setObjetoValidado(enderecoTeste);
        validacao.cepDeveSerInformado();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 1);
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Cep deve ser informado");
    }

    @Test
    public void ruaInformada() {
        enderecoTeste.setRua("Rua Flamboyant");
        validacao.setObjetoValidado(enderecoTeste);
        validacao.ruaDeveSerInformado();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void ruaNaoInformada() {
        enderecoTeste.setRua("");
        validacao.setObjetoValidado(enderecoTeste);
        validacao.ruaDeveSerInformado();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 1);
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Rua deve ser informada");
    }

}
