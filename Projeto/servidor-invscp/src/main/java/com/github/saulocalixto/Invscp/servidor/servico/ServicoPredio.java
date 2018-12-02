package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioPredio;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorio;
import com.github.saulocalixto.Invscp.servidor.negocio.Predio;
import com.github.saulocalixto.Invscp.servidor.negocio.endereco.Endereco;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import java.util.List;


public class ServicoPredio extends ServicoPadrao<Predio> {

    private ServicoEndereco servicoEndereco;

    public Predio Consultar(String id) {
        Predio predio = repositorio().Consultar(id);

        if(predio.getEndereco() != null && predio.getEndereco().getId() != null) {
            predio.setEndereco(servicoEndereco().Consultar(predio.getEndereco().getId()));
        }

        return predio;
    }

    public List<Predio> ConsultarLista() {
        List<Predio> lista = repositorio().ConsultarLista();
        lista.forEach(x -> {
            if(x.getEndereco() != null && x.getEndereco().getId() != null) {
                x.setEndereco(servicoEndereco().Consultar(x.getEndereco().getId()));
            }
        });

        return lista;
    }

    public List<Inconsistencia> Salvar(Predio objeto) {

        validador().setObjetoValidado(objeto);

        inconsistencias = validador().ValideInclusao();

        if(objeto.getEndereco() != null) {
            inconsistencias = servicoEndereco().Salvar(objeto.getEndereco());
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
            inconsistencias = servicoEndereco().Atualizar(objeto.getEndereco());
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
        return null;
    }

    private ServicoPadrao<Endereco> servicoEndereco() {
        return servicoEndereco != null ? servicoEndereco : (servicoEndereco = new ServicoEndereco());
    }
}