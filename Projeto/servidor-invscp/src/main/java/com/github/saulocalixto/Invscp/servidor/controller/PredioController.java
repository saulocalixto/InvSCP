package com.github.saulocalixto.Invscp.servidor.controller;

import com.github.saulocalixto.Invscp.servidor.negocio.Predio;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoPadrao;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoPredio;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/predio")
public class PredioController extends ControllerPadrao<Predio> {
    @Override
    protected ServicoPadrao<Predio> getServico() {
        FabricaDeServicos<ServicoPredio> fabrica = new FabricaDeServicos(ServicoPredio.class);
        return servico != null ? servico : (servico = fabrica.crie());
    }
}
