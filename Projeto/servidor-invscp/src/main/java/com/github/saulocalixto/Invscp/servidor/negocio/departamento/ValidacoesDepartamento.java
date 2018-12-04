package com.github.saulocalixto.Invscp.servidor.negocio.departamento;

import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import javax.validation.constraints.Null;
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
        try {
            this.conceito("Nome")
                    .validarSe(objetoValidado != null)
                    .ehValidoQuando(objetoValidado.getNomeDoDepartamento() != null
                            && !objetoValidado.getNomeDoDepartamento().isEmpty())
                    .comMensagem("Nome do departamento não informado")
                    .valide();
        } catch (NullPointerException e) {

        }
    }

    private void comumCadastroEAtualizacao() {
        nomeObrigatorio();
    }
}
