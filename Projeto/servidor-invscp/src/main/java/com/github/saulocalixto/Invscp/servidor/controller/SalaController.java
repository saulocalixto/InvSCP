package com.github.saulocalixto.Invscp.servidor.controller;

import com.github.saulocalixto.Invscp.servidor.negocio.sala.Sala;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoPadrao;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoSala;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sala")
public class SalaController extends ControllerPadrao<Sala> {

    @Override
    protected ServicoPadrao<Sala> getServico() {
        FabricaDeServicos<ServicoPadrao<Sala>> fabrica = new FabricaDeServicos(ServicoSala.class);
        return servico != null ? servico : (servico = fabrica.crie());
    }
}
