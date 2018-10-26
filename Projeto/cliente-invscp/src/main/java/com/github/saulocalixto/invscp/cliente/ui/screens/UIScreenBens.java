/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.ui.screens;

import com.github.saulocalixto.invscp.cliente.ui.UIScreen;
import com.github.saulocalixto.invscp.cliente.ui.UIScreenOption;
import java.util.HashMap;

/**
 *
 * @author Lucas Sampaio Dias
 */
public class UIScreenBens extends UIScreen{
    
    private static final HashMap<Integer, UIScreenOption> opcoes;
    static
    {
        opcoes = new HashMap<>();
        // Será implementado na Etapa 2
    }
    
    public UIScreenBens() {
        super("Bens", opcoes);
    }
    
}
