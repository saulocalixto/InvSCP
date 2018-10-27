package com.github.saulocalixto.Invscp.servidor.controller;

import com.github.saulocalixto.Invscp.servidor.negocio.Usuario;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoUsuario;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Saulo on 23/10/18.
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private ServicoUsuario servicoUsuario;

    private ServicoUsuario servicoUsuario() {
            FabricaDeServicos<ServicoUsuario> fabrica = new FabricaDeServicos(ServicoUsuario.class);
            return servicoUsuario != null ? servicoUsuario : (servicoUsuario = fabrica.crie());
    }

    @RequestMapping("/consulte")
    public Usuario usuario(@RequestParam(value="cpf") String cpf) {
        Usuario usuario = servicoUsuario().ConsultarPorCpf(cpf);
        return usuario;
    }

    @RequestMapping("/cadastre")
    public boolean usuario(@RequestParam(value="usuario") Usuario usuario) {
        servicoUsuario().Salvar(usuario);
        return true;
    }
}
