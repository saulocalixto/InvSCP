package com.github.saulocalixto.Invscp.servidor.negocio.usuario;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumGrupoDeAcesso;
import com.github.saulocalixto.Invscp.servidor.negocio.Departamento;
import com.github.saulocalixto.Invscp.servidor.negocio.ModelPadrao;

/**
 * Created by Saulo Calixto on 23/10/18.
 */

public class Usuario extends ModelPadrao {

    private String nome;

    private String cpf;

    private String senha;

    private String email;

    private Departamento departamento;

    private EnumGrupoDeAcesso grupo;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha != null ? senha : "";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome != null ? nome : "";
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf != null ? cpf : "";
    }

    public EnumGrupoDeAcesso getGrupo() {
        return grupo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public void setGrupo(EnumGrupoDeAcesso grupo) {
        this.grupo = grupo;
    }

    public void setGrupo(String grupo) {
        try {
            this.grupo = EnumGrupoDeAcesso.valueOf(grupo);
        } catch(Exception e) {
            this.grupo = null;
        }

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email != null ? email : "";
    }
}
