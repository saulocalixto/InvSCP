package com.github.saulocalixto.Invscp.servidor.controller;

import com.github.saulocalixto.Invscp.servidor.negocio.ordemDeServico.OrdemDeServico;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoOrdemDeServico;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoPadrao;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordemDeServico")
public class OrdemDeServicoController extends ControllerPadrao<OrdemDeServico> {
    @Override
    protected ServicoPadrao<OrdemDeServico> getServico() {
        FabricaDeServicos<ServicoPadrao<OrdemDeServico>> fabrica = new FabricaDeServicos(ServicoOrdemDeServico.class);
        return servico != null ? servico : (servico = fabrica.crie());
    }
}
