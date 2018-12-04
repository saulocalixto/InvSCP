package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioPadrao;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorio;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.negocio.departamento.Departamento;
import com.github.saulocalixto.Invscp.servidor.negocio.usuario.Usuario;
import com.github.saulocalixto.Invscp.servidor.negocio.usuario.ValidacoesUsuario;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;
import com.github.saulocalixto.Invscp.servidor.utilitarios.SenhaEncript;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saulo Calixto on 23/10/18.
 */
public class ServicoUsuario extends ServicoPadrao<Usuario> {

    private ServicoDepartamento servicoDepartamento;

    public Usuario Consultar(String id) {
        Usuario usuario = repositorio().Consultar(id);
        if(usuario.getDepartamento() != null && usuario.getDepartamento() != null) {
            usuario.setDepartamento(servicoDepartamento().Consultar(usuario.getDepartamento()).getId());
        }

        return usuario;
    }

    public List<Usuario> ConsultarLista() {
        List<Usuario> lista = repositorio().ConsultarLista();
        lista.forEach(x -> {
            if(x.getDepartamento() != null && x.getDepartamento() != null) {
                x.setDepartamento(servicoDepartamento().Consultar(x.getDepartamento()).getId());
            }
        });

        return lista;
    }

    public Usuario consultarPorEmail(String email) {

        Usuario usuario = ((IRepositorioUsuario)repositorio()).consultarPorEmail(email);

        if(usuario.getDepartamento() != null && usuario.getDepartamento() != null) {
            usuario.setDepartamento(servicoDepartamento().Consultar(usuario.getDepartamento()).getId());
        }

        return usuario;
    }

    public List<Inconsistencia> Salvar(Usuario objeto) {

        validador().setObjetoValidado(objeto);

        inconsistencias = validador().ValideInclusao();

        if(validador().naoHouveInconsistencias()) {
            objeto.setSenha(SenhaEncript.criptografeSenha(objeto.getSenha()));
            repositorio().Salvar(objeto);
        }

        return inconsistencias;
    }

    public List<Inconsistencia> Atualizar(Usuario objeto) {

        validador().setObjetoValidado(objeto);

        inconsistencias = validador().ValideAtualizacao();

        if(validador().naoHouveInconsistencias()) {
            objeto.setSenha(SenhaEncript.criptografeSenha(objeto.getSenha()));
            repositorio().Atualizar(objeto);
        }

        return inconsistencias;
    }

    public List<Inconsistencia> Excluir(String id) {
        Usuario usuario = Consultar(id);

        if(usuario.getId().equals(id)) {
            validador.setObjetoValidado(usuario);

            inconsistencias = validador.ValideExclusao();
            if(validador.naoHouveInconsistencias()) {
                repositorio().Excluir(id);
            } else {
                CrieInconsistenciaConceitoNaoExiste();
            }
        }


        return inconsistencias;
    }

    private ServicoDepartamento servicoDepartamento() {
        FabricaDeServicos<ServicoDepartamento> fabrica = new FabricaDeServicos(ServicoDepartamento.class);
        return servicoDepartamento != null ? servicoDepartamento : (servicoDepartamento = fabrica.crie());
    }

    @Override
    IRepositorio<Usuario> repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioUsuario());
    }

    @Override
    ValidadorPadrao<Usuario> validador() {
        return validador != null ? validador : (validador = new ValidacoesUsuario());
    }
}
