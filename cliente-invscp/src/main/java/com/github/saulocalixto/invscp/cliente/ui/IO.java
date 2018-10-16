/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe com métodos úteis para entrada e saída de dados via terminal
 * @author Lucas Sampaio Dias
 */
public class IO {
    private static BufferedReader br = new 
        BufferedReader(new InputStreamReader(System.in));
    
    public static int getOpcao(int min, int max) throws IOException{
        while(true) {
            try {
                int op = Integer.parseInt(br.readLine());
                if (op < min || op > max) {
                    throw new Exception();
                }
                return op;
            }
            catch (IOException e) {
                System.out.println("Não foi possível ler o número inserido, "
                        + "tente novamente.");
            }
            catch (NumberFormatException e) {
                System.out.println("Número inválido, tente novamente.");
            }
            catch (Exception ex) {
                System.out.println("Opção inválida, tente novamente.");
            }
        }
        
    }
}
