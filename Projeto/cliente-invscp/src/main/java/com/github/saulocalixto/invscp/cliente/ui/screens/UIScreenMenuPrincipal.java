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
public class UIScreenMenuPrincipal extends UIScreen{
    
    private static final HashMap<Integer, UIScreenOption> opcoes;
    static
    {
        opcoes = new HashMap<>();
        opcoes.put(1, new UIScreenOption("Bens", () -> {
            try {
                TerminalUI.mostrar(new UIScreenBens());
            } catch (IOException ex) {
                Logger.getLogger(UIScreenMenuPrincipal.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(2, new UIScreenOption("Locais", () -> {
            try {
                TerminalUI.mostrar(new UIScreenLocais());
            } catch (IOException ex) {
                Logger.getLogger(UIScreenMenuPrincipal.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }));
    }
    
    public UIScreenMenuPrincipal() {
        super("Menu Principal", opcoes);
    }
    
}
