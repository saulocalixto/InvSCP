package com.github.saulocalixto.Invscp.servidor.negocio.usuario;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import java.util.List;

public class ValidacoesUsuario extends ValidadorPadrao<Usuario> {

    private IRepositorioUsuario repositorio;

    public ValidacoesUsuario(Usuario objetoValidado) {
        super(objetoValidado);
    }

    public List<Inconsistencia> ValideInclusao () {
        emailValido();
        cpfValido();
        return super.ValideInclusao();
    }

    public void emailValido() {
        if(objetoValidado.getEmail().contains("@")) {
            return;
        } else {
            adicioneInconsistencia("E-mail inválido.", "E-Mail");
        }
    }

    public void cpfValido() {
        if(objetoValidado.getCpf().length() == 11) {
            return;
        } else {
            adicioneInconsistencia("Cpf deve ter 11 dígitos.", "Cpf");
        }
    }

    private IRepositorioUsuario repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioUsuario());
    }
}
