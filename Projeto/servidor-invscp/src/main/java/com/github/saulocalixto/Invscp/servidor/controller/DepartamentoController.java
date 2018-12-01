package com.github.saulocalixto.Invscp.servidor.controller;

import com.github.saulocalixto.Invscp.servidor.negocio.departamento.Departamento;
import com.github.saulocalixto.Invscp.servidor.negocio.usuario.Usuario;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoDepartamento;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoLogin;
import com.github.saulocalixto.Invscp.servidor.servico.ServicoPadrao;
import com.github.saulocalixto.Invscp.servidor.utilitarios.FabricaDeServicos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController extends ControllerPadrao<Departamento> {

    @Override
    protected ServicoPadrao<Departamento> getServico() {
        FabricaDeServicos<ServicoPadrao<Departamento>> fabrica = new FabricaDeServicos(ServicoDepartamento.class);
        return servico != null ? servico : (servico = (ServicoPadrao<Departamento>) fabrica.crie());
    }
}
