/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.ui.screens;

import com.github.saulocalixto.invscp.cliente.ui.TerminalUI;
import com.github.saulocalixto.invscp.cliente.ui.UIScreen;
import com.github.saulocalixto.invscp.cliente.ui.UIScreenOption;
import java.util.HashMap;

/**
 *
 * @author Lucas Sampaio Dias
 */
public class UIScreenFiliais extends UIScreen{
    
    private static final HashMap<Integer, UIScreenOption> opcoes;
    static
    {
        opcoes = new HashMap<>();
        opcoes.put(1, new UIScreenOption("Cadastrar", () -> {
            System.out.println("Funcionalidade não implementada");
        }));
        opcoes.put(2, new UIScreenOption("Visualizar", () -> {
            System.out.println("Funcionalidade não implementada");
        }));
        opcoes.put(3, new UIScreenOption("Editar", () -> {
            System.out.println("Funcionalidade não implementada");
        }));
        opcoes.put(4, new UIScreenOption("Deletar", () -> {
            System.out.println("Funcionalidade não implementada");
        }));
    }
    
    public UIScreenFiliais() {
        super("Filiais", opcoes);
    }
    
}
