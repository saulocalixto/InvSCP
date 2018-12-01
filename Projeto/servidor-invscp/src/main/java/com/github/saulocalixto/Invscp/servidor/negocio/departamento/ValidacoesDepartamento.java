package com.github.saulocalixto.Invscp.servidor.negocio.departamento;

import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import java.util.List;

public class ValidacoesDepartamento extends ValidadorPadrao<Departamento> {

    public List<Inconsistencia> ValideInclusao () {
        comumCadastroEAtualizacao();
        return super.ValideInclusao();
    }

    public List<Inconsistencia> ValideAtualizacao () {
        comumCadastroEAtualizacao();
        return super.ValideAtualizacao();
    }

    public List<Inconsistencia> ValideExclusao() {
        return super.ValideExclusao();
    }

    public void nomeObrigatorio() {
        this.conceito("Nome")
            .validarSe(objetoValidado != null)
            .ehValidoQuando(objetoValidado.getNomeDoDepartamento() != null
                    && !objetoValidado.getNomeDoDepartamento().isEmpty())
            .comMensagem("Nome não informado.")
            .valide();
    }

    private void comumCadastroEAtualizacao() {
        nomeObrigatorio();
    }
}
