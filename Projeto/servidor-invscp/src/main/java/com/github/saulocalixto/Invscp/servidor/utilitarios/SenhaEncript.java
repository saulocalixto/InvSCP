package com.github.saulocalixto.Invscp.servidor.utilitarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SenhaEncript {

    @Autowired
    private static PasswordEncoder passwordEncoder;


    public static String criptografeSenha(String senha) {
        return passwordEncoder().encode(senha);
    }

    public static Boolean valideSenhe(String senhaSalva, String senhaInformada) {
        return passwordEncoder().matches(senhaInformada, senhaSalva);
    }

    @Bean
    private static PasswordEncoder passwordEncoder() {
        if (passwordEncoder == null) {
            passwordEncoder = new BCryptPasswordEncoder();
        }
        return passwordEncoder;
    }
}
