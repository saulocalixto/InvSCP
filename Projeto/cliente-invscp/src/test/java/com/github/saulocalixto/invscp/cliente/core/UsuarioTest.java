/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.core;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucas Sampaio Dias
 */
public class UsuarioTest {
    
    public UsuarioTest() {
    }

    /**
     * Test of isEmail method, of class Usuario.
     */
    @Test
    public void testIsEmail_True() {
        assertEquals(true, Usuario.isEmail("joao123@gmail.com"));
    }

    @Test
    public void testIsEmail_False_Empty() {
        assertEquals(false, Usuario.isEmail(""));
    }
    
    @Test
    public void testIsEmail_False_Null() {
        assertEquals(false, Usuario.isEmail(null));
    }
}
