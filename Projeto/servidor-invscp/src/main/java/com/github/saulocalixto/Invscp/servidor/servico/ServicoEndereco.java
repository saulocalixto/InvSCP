package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioEndereco;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorio;
import com.github.saulocalixto.Invscp.servidor.negocio.endereco.Endereco;
import com.github.saulocalixto.Invscp.servidor.negocio.endereco.ValidacoesEndereco;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import javax.validation.ValidationException;
import java.util.List;

public class ServicoEndereco extends ServicoPadrao<Endereco> {
    @Override
    IRepositorio<Endereco> repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioEndereco());
    }

    @Override
    ValidadorPadrao<Endereco> validador() {
        return validador != null ? validador : (validador = new ValidacoesEndereco());
    }

    @Override
    public Endereco Consultar(String id) {
        return repositorio().Consultar(id);
    }

    @Override
    public List<Endereco> ConsultarLista() {
        return repositorio().ConsultarLista();
    }

    @Override
    public List<Inconsistencia> Salvar(Endereco objeto) throws ValidationException {
        validador().setObjetoValidado(objeto);
        inconsistencias = validador().ValideInclusao();

        if(validador().naoHouveInconsistencias()) {
            repositorio().Salvar(objeto);
        }

        return inconsistencias;
    }

    @Override
    public List<Inconsistencia> Atualizar(Endereco objeto) {
        validador().setObjetoValidado(objeto);
        inconsistencias = validador.ValideAtualizacao();
        if(validador().naoHouveInconsistencias()) {
            repositorio().Atualizar(objeto);
        }
        return inconsistencias;
    }

    @Override
    public List<Inconsistencia> Excluir(String id) {
        Endereco endereco = repositorio().Consultar(id);
        validador().setObjetoValidado(endereco);
        inconsistencias = validador().ValideExclusao();

        if(validador().naoHouveInconsistencias()) {
            repositorio().Excluir(id);
        }

        return inconsistencias;
    }
}
