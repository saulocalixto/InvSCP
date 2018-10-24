package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumGrupoDeAcesso;
import com.github.saulocalixto.Invscp.servidor.negocio.Usuario;

import java.util.List;

/**
 * Created by Saulo Calixto on 23/10/18.
 */
public class ServicoUsuario implements IServico<Usuario> {
    public Usuario Consultar(String id) {
        return null;
    }

    public List<Usuario> ConsultarLista() {
        return null;
    }

    public Usuario ConsultarPorCpf(String cpf) {
        Usuario usuario = new Usuario();
        usuario.setNomeDeUsuario("saulocalixto");
        usuario.setNome("Saulo");
        usuario.setCpf(cpf);
        usuario.setGrupo(EnumGrupoDeAcesso.CHEFE_DEPARTAMENTO);

        return usuario;
    }

    public void Salvar(Usuario objeto) {

    }

    public void Atualizar(Usuario objeto) {

    }

    public void Excluir(String id) {

    }
}
