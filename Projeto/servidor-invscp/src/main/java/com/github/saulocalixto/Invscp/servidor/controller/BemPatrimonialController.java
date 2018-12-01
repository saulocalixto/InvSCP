package com.github.saulocalixto.Invscp.servidor.controller;

import com.github.saulocalixto.Invscp.servidor.negocio.bemPatrimonial.BemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoPadrao;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bemPatrimonial")
public class BemPatrimonialController extends ControllerPadrao<BemPatrimonial> {

    @Override
    protected ServicoPadrao<BemPatrimonial> getServico() {
        FabricaDeServicos<ServicoPadrao<BemPatrimonial>> fabrica = new FabricaDeServicos(ServicoBemPatrimonial.class);
        return servico != null ? servico : (servico = fabrica.crie());
    }
}
