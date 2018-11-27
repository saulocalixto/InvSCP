package com.github.saulocalixto.Invscp.servidor.negocio.usuario;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumGrupoDeAcesso;
import com.github.saulocalixto.Invscp.servidor.negocio.Login;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;

import java.util.InputMismatchException;
import java.util.List;

public class ValidacoesUsuario extends ValidadorPadrao<Usuario> {

    private IRepositorioUsuario repositorio;

    public ValidacoesUsuario(Usuario objetoValidado) {
        super(objetoValidado);
    }

    public List<Inconsistencia> ValideInclusao () {
        emailNaoCadastrado();
        comumCadastroEAtualizacao();
        return super.ValideInclusao();
    }

    public List<Inconsistencia> ValideAtualizacao () {
        comumCadastroEAtualizacao();
        return super.ValideAtualizacao();
    }

    public List<Inconsistencia> ValideExclusao() {
        usuarioTemPermissaoParaAlterarUsuario();
        return super.ValideExclusao();
    }

    public void emailObrigatorio() {
        this.conceito("E-Mail")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getEmail() != null && !objetoValidado.getEmail().isEmpty())
                .comMensagem("E-mail não informado")
                .valide();
    }

    public void emailValido() {
        this.conceito("E-Mail")
                .validarSe(objetoValidado != null && objetoValidado.getEmail() != null)
                .ehValidoQuando(objetoValidado.getEmail().contains("@"))
                .comMensagem("E-mail inválido")
                .valide();
    }

    public void emailNaoCadastrado() {
        this.conceito("E-Mail")
                .validarSe(objetoValidado != null && objetoValidado.getEmail() != null)
                .ehValidoQuando(repositorio().usuarioNaoExiste(objetoValidado.getEmail()))
                .comMensagem("O E-mail informado já está cadastrado")
                .valide();
    }

    public void cpfValido() {
        this.conceito("Cpf")
                .validarSe(objetoValidado != null && objetoValidado.getCpf() != null)
                .ehValidoQuando(cpfValido(objetoValidado.getCpf()))
                .comMensagem("Cpf inválido")
                .valide();
    }

    public void cpfObrigatorio() {
        this.conceito("Cpf")
                .validarSe(objetoValidado != null && objetoValidado.getCpf() != null)
                .ehValidoQuando(objetoValidado.getCpf() != null && !objetoValidado.getCpf().isEmpty())
                .comMensagem("Cpf não informado")
                .valide();
    }

    public void senhaValida() {
        this.conceito("Senha")
                .validarSe(objetoValidado != null && objetoValidado.getSenha() != null)
                .ehValidoQuando(objetoValidado.getSenha().length() >= 6)
                .comMensagem("Senha deve ter no mínimo 6 dígitos")
                .valide();
    }

    public void senhaObrigatoria() {
        this.conceito("Senha")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getSenha() != null && !objetoValidado.getSenha().isEmpty())
                .comMensagem("Senha não informada")
                .valide();
    }

    public void grupoObrigatorio() {
        this.conceito("Grupo")
                .validarSe(objetoValidado != null)
                .ehValidoQuando(objetoValidado.getGrupo() != null)
                .comMensagem("Grupo não informado ou inválido")
                .valide();
    }

    public void usuarioTemPermissaoParaAlterarUsuario() {
        this.conceito("Grupo")
                .validarSe(permissaoDoUsuario != null)
                .ehValidoQuando(permissaoDoUsuario == EnumGrupoDeAcesso.ADMINISTRADOR_DEPARTAMENTO
                        || permissaoDoUsuario == EnumGrupoDeAcesso.CHEFE_PATRIMONIO)
                .comMensagem("Usuário não tem permissão para alterar outro usuário")
                .valide();
    }

    private void comumCadastroEAtualizacao() {
        emailValido();
        emailObrigatorio();
        cpfValido();
        senhaValida();
        senhaObrigatoria();
        cpfObrigatorio();
        grupoObrigatorio();
        usuarioTemPermissaoParaAlterarUsuario();
    }

    private boolean cpfValido(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }
    
    private IRepositorioUsuario repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioUsuario());
    }
}
