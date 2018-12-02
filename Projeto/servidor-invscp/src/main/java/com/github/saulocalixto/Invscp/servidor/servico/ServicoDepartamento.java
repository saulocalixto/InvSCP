package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioDepartamento;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorio;
import com.github.saulocalixto.Invscp.servidor.negocio.departamento.Departamento;
import com.github.saulocalixto.Invscp.servidor.negocio.departamento.ValidacoesDepartamento;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;

import java.util.List;

public class ServicoDepartamento extends ServicoPadrao<Departamento> {

    private ServicoSala servicoDeSala;

    public Departamento Consultar(String id) {
        Departamento departamento = repositorio().Consultar(id);
        departamento.setListaDeSalas(servicoSala().consultarSalasDeDepartamento(id));
        return departamento;
    }

    public List<Departamento> ConsultarLista() {
        List<Departamento> lista = repositorio().ConsultarLista();
        lista.forEach(x -> x.setListaDeSalas(servicoSala().consultarSalasDeDepartamento(x.getId())));
        return lista;
    }

    public List<Inconsistencia> Salvar(Departamento objeto) {

        inconsistencias = validador().ValideInclusao();
        validador().setObjetoValidado(objeto);

        if(validador().naoHouveInconsistencias()) {
            repositorio().Salvar(objeto);
        }
        return inconsistencias;
    }

    public List<Inconsistencia> Atualizar(Departamento objeto) {
        validador().setObjetoValidado(objeto);
        inconsistencias = validador.ValideAtualizacao();
        if(validador().naoHouveInconsistencias()) {
            repositorio().Atualizar(objeto);
        }
        return inconsistencias;
    }

    public List<Inconsistencia> Excluir(String id) {
        Departamento departamento = Consultar(id);
        validador().setObjetoValidado(departamento);
        inconsistencias = validador.ValideExclusao();

        if(validador().naoHouveInconsistencias()) {
            repositorio().Excluir(id);
        }

        return inconsistencias;
    }

    @Override
    IRepositorio<Departamento> repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioDepartamento());
    }

    @Override
    ValidadorPadrao<Departamento> validador() {
        return validador != null ? validador : (validador = new ValidacoesDepartamento());
    }

    private ServicoSala servicoSala() {
        FabricaDeServicos<ServicoSala> fabrica = new FabricaDeServicos(ServicoSala.class);
        return servicoDeSala != null ? servicoDeSala : (servicoDeSala = fabrica.crie());
    }
}