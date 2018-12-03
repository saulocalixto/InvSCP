package com.github.saulocalixto.Invscp.servidor.negocio.predio;

import com.github.saulocalixto.Invscp.servidor.negocio.endereco.Endereco;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by murillonunes on 20/11/18.
 */
public class ValidacoesPredioTest {

    private Predio predioTeste;
    private ValidacoesPredio validacao;
    private Endereco enderecoTeste;

    @Before
    public void setUp() {
        predioTeste = new Predio();
        validacao = new ValidacoesPredio();
        enderecoTeste = new Endereco();
    }

    @After
    public void tearDown() {}

    @Test
    public void nomeDoPredioInformado() {
        predioTeste.setNome("Predio de Teste 1");
        validacao.setObjetoValidado(predioTeste);
        validacao.nomeObrigatorio();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

    @Test
    public void nomeDoPredioNaoInformado() {
        predioTeste.setNome("");
        validacao.setObjetoValidado(predioTeste);
        validacao.nomeObrigatorio();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 1);
        assertEquals(listaDeInconsistencias.stream().findFirst().get().getMensagem(), "Nome do prédio é " +
                "obrigatório");
    }

    @Test
    public void enderecoDoPredioInformado() {
        enderecoTeste.setRua("Rua Teste");
        enderecoTeste.setCep("75620000");
        enderecoTeste.setCidade("Goiânia");
        enderecoTeste.setBairro("Goiânia II");
        enderecoTeste.setId("1");
        predioTeste.setEndereco(enderecoTeste);
        validacao.setObjetoValidado(predioTeste);
        validacao.enderecoObrigatorio();
        List<Inconsistencia> listaDeInconsistencias = validacao.retorneInconsistencias();
        assertEquals(listaDeInconsistencias.size(), 0);
    }

}
