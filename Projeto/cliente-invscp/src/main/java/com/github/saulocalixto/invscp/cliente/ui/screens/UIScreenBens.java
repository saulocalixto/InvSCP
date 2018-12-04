/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.ui.screens;

import com.github.saulocalixto.invscp.cliente.ui.TerminalUI;
import com.github.saulocalixto.invscp.cliente.ui.UIScreen;
import com.github.saulocalixto.invscp.cliente.ui.UIScreenOption;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas Sampaio Dias
 */
public class UIScreenBens extends UIScreen{
    
    private static final HashMap<Integer, UIScreenOption> opcoes;
    static
    {
        opcoes = new HashMap<>();
        opcoes.put(1, new UIScreenOption("Bens Patrimoniais", () -> {
            try {
                TerminalUI.mostrar(new UIScreenBemPatrimonial());
            } catch (IOException ex) {
                Logger.getLogger(UIScreenMenuPrincipal.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(2, new UIScreenOption("Baixas", () -> {
            try {
                TerminalUI.mostrar(new UIScreenBaixa());
            } catch (IOException ex) {
                Logger.getLogger(UIScreenMenuPrincipal.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(3, new UIScreenOption("Ordens de Serviço", () -> {
            try {
                TerminalUI.mostrar(new UIScreenOrdemDeServico());
            } catch (IOException ex) {
                Logger.getLogger(UIScreenMenuPrincipal.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }));
    }
    
    public UIScreenBens() {
        super("Bens", opcoes);
    }
    
}
