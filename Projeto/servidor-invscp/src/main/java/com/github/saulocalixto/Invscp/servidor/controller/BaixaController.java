package com.github.saulocalixto.Invscp.servidor.controller;

import com.github.saulocalixto.Invscp.servidor.negocio.baixa.Baixa;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoBaixa;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoPadrao;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/baixa")
public class BaixaController extends ControllerPadrao<Baixa> {
    @Override
    protected ServicoPadrao<Baixa> getServico() {
        FabricaDeServicos<ServicoPadrao<Baixa>> fabrica = new FabricaDeServicos(ServicoBaixa.class);
        return servico != null ? servico : (servico = fabrica.crie());
    }
}
