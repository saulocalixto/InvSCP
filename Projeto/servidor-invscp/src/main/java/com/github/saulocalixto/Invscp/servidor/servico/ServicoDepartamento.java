package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioDepartamento;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioDepartamento;
import com.github.saulocalixto.Invscp.servidor.negocio.departamento.Departamento;
import com.github.saulocalixto.Invscp.servidor.negocio.departamento.validacoesDepartamento;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;

import java.util.List;


public class ServicoDepartamento implements IServico<Departamento>{

    private IRepositorioDepartamento repositorio;
    private ValidadorPadrao<Departamento> validador;
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

        List<Inconsistencia> inconsistencias;
        inconsistencias = Validador(objeto).ValideInclusao();
        if(Validador(objeto).naoHouveInconsistencias()) {
            repositorio().Salvar(objeto);
        }
        return inconsistencias;
    }

    public List<Inconsistencia> Atualizar(Departamento objeto) {
        List<Inconsistencia> inconsistencias;
        inconsistencias = Validador(objeto).ValideAtualizacao();
        if(Validador(objeto).naoHouveInconsistencias()) {
            repositorio().Atualizar(objeto);
        }
        return inconsistencias;
    }

    public List<Inconsistencia> Excluir(String id) {
        List<Inconsistencia> inconsistencias;
        Departamento departamento = Consultar(id);
        inconsistencias = Validador(departamento).ValideExclusao();
        if(Validador(departamento).naoHouveInconsistencias()) {
            repositorio().Excluir(id);
        }

        return inconsistencias;
    }

    private ValidadorPadrao<Departamento> Validador(Departamento departamento){
        return validador != null ? validador : (validador = new validacoesDepartamento(departamento));
    }

    private IRepositorioDepartamento repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioDepartamento());
    }

    private ServicoSala servicoSala() {
        FabricaDeServicos<ServicoSala> fabrica = new FabricaDeServicos(ServicoSala.class);
        return servicoDeSala != null ? servicoDeSala : (servicoDeSala = fabrica.crie());
    }
}