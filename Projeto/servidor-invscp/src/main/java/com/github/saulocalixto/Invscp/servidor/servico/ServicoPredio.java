package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioPredio;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorio;
import com.github.saulocalixto.Invscp.servidor.negocio.predio.Predio;
import com.github.saulocalixto.Invscp.servidor.negocio.endereco.Endereco;
import com.github.saulocalixto.Invscp.servidor.negocio.predio.ValidacoesPredio;
import com.github.saulocalixto.Invscp.servidor.negocio.sala.Sala;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import java.util.List;


public class ServicoPredio extends ServicoPadrao<Predio> {

    private ServicoEndereco servicoEndereco;
    private ServicoSala servicoSala;

    public Predio Consultar(String id) {
        Predio predio = repositorio().Consultar(id);

        if(predio.getEndereco() != null && predio.getEndereco().getId() != null) {
            predio.setEndereco(servicoEndereco().Consultar(predio.getEndereco().getId()));
        }

        predio.setListaDeSalas(((ServicoSala)servicoSala()).consultarSalasDePredio(id));

        return predio;
    }

    public List<Predio> ConsultarLista() {
        List<Predio> lista = repositorio().ConsultarLista();
        lista.forEach(x -> {
            if(x.getEndereco() != null && x.getEndereco().getId() != null) {
                x.setEndereco(servicoEndereco().Consultar(x.getEndereco().getId()));
            }

            x.setListaDeSalas(((ServicoSala)servicoSala()).consultarSalasDePredio(x.getId()));
        });

        return lista;
    }

    public List<Inconsistencia> Salvar(Predio objeto) {

        validador().setObjetoValidado(objeto);

        inconsistencias = validador().ValideInclusao();

        if(objeto.getEndereco() != null) {
            inconsistencias.addAll(servicoEndereco().Salvar(objeto.getEndereco()));
        }

        if(validador().naoHouveInconsistencias()) {
            repositorio().Salvar(objeto);
        }

        return inconsistencias;
    }

    public List<Inconsistencia> Atualizar(Predio objeto) {
        validador().setObjetoValidado(objeto);

        inconsistencias = validador().ValideAtualizacao();

        if(objeto.getEndereco() != null) {
            inconsistencias.addAll(servicoEndereco().Atualizar(objeto.getEndereco()));
        }

        if(validador().naoHouveInconsistencias()) {
            repositorio().Atualizar(objeto);
        }

        return inconsistencias;
    }

    public List<Inconsistencia> Excluir(String id) {
        Predio predio = Consultar(id);

        validador.setObjetoValidado(predio);

        inconsistencias = validador.ValideExclusao();

        predio.getListaDeSalas().forEach(sala -> {
            inconsistencias.addAll(((ServicoSala)servicoSala()).atualizeSalasDePredio(null, predio.getId()));
        });

        if(validador.naoHouveInconsistencias()) {
            repositorio().Excluir(id);
        }

        return inconsistencias;
    }

    @Override
    IRepositorio<Predio> repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioPredio());
    }

    @Override
    ValidadorPadrao<Predio> validador() {
        return validador != null ? validador : (validador = new ValidacoesPredio());
    }

    private ServicoPadrao<Endereco> servicoEndereco() {
        return servicoEndereco != null ? servicoEndereco : (servicoEndereco = new ServicoEndereco());
    }

    private ServicoPadrao<Sala> servicoSala() {
        return servicoSala != null ? servicoSala : (servicoSala = new ServicoSala());
    }
}