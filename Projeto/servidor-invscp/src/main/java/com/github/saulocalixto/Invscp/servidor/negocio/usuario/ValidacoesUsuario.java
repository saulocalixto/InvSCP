package com.github.saulocalixto.Invscp.servidor.negocio.usuario;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioUsuario;
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

    public void emailObrigatorio() {
        if(objetoValidado.getEmail() != null && !objetoValidado.getEmail().isEmpty()) {
            return;
        } else {
            adicioneInconsistencia("E-mail não informado.", "E-Mail");
        }
    }

    public void emailValido() {
        if(objetoValidado.getEmail().contains("@")) {
            return;
        } else {
            adicioneInconsistencia("E-mail inválido.", "E-Mail");
        }
    }

    public void emailNaoCadastrado() {
        if(repositorio().usuarioNaoExiste(objetoValidado.getEmail())) {
            return;
        } else {
            adicioneInconsistencia("O E-mail informado já está cadastrado.", "E-mail");
        }
    }

    public void cpfValido() {
        if(cpfValido(objetoValidado.getCpf())) {
            return;
        } else {
            adicioneInconsistencia("Cpf inválido.", "Cpf");
        }
    }

    public void cpfObrigatorio() {
        if(objetoValidado.getCpf() != null && !objetoValidado.getCpf().isEmpty()) {
            return;
        } else {
            adicioneInconsistencia("Cpf não informado.", "Cpf");
        }
    }

    public void senhaValida() {
        if(objetoValidado.getSenha().length() > 6) {
            return;
        } else {
            adicioneInconsistencia("Senha deve ter no mínimo 6 dígitos.", "Senha");
        }
    }

    public void senhaObrigatoria() {
        if(objetoValidado.getSenha() != null && !objetoValidado.getSenha().isEmpty()) {
            return;
        } else {
            adicioneInconsistencia("Senha não informada.", "Senha");
        }
    }

    public void grupoObrigatorio() {
        if(objetoValidado.getGrupo() != null) {
            return;
        } else {
            adicioneInconsistencia("Grupo não informado ou inválido.", "Grupo");
        }
    }

    private void comumCadastroEAtualizacao() {
        emailValido();
        emailObrigatorio();
        cpfValido();
        senhaValida();
        senhaObrigatoria();
        cpfObrigatorio();
        grupoObrigatorio();
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
