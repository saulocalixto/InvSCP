package com.github.saulocalixto.Invscp.servidor.controller;

import com.github.saulocalixto.Invscp.servidor.negocio.endereco.Endereco;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoEndereco;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoPadrao;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class EnderecoController extends ControllerPadrao<Endereco> {
    @Override
    protected ServicoPadrao<Endereco> getServico() {
        FabricaDeServicos<ServicoPadrao<Endereco>> fabrica = new FabricaDeServicos(ServicoEndereco.class);
        return servico != null ? servico : (servico = fabrica.crie());
    }
}
