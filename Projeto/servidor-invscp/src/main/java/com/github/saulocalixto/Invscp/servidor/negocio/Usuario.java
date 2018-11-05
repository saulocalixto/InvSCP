package com.github.saulocalixto.Invscp.servidor.negocio;

import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumGrupoDeAcesso;

/**
 * Created by Saulo Calixto on 23/10/18.
 */
public class Usuario extends CRUDModelPadrao {

    private String nomeDeUsuario;

    private String nome;

    private String cpf;

    private String senha;

    private String email;

    private EnumGrupoDeAcesso grupo;


    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public EnumGrupoDeAcesso getGrupo() {
        return grupo;
    }

    public void setGrupo(EnumGrupoDeAcesso grupo) {
        this.grupo = grupo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
