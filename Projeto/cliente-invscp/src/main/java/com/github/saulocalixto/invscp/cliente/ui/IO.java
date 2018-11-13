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
    
    public static String readString() throws IOException {
        return br.readLine();
    }
    
    public static String readString(String message) throws IOException {
        System.out.println(message);
        return readString();
    }
    
    public static int readInt(int min, int max) throws IOException{
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
                System.out.println("Número fora do intervalo permitido, tente "
                        + "novamente.");
            }
        }
    }
    
    public static int readInt() throws IOException{
        while(true) {
            try {
                int op = Integer.parseInt(br.readLine());
                return op;
            }
            catch (IOException e) {
                System.out.println("Não foi possível ler o número inserido, "
                        + "tente novamente.");
            }
            catch (NumberFormatException e) {
                System.out.println("Número inválido, tente novamente.");
            }
        }
    }
    
    /**
     * Lê o nome do usuário inserido no terminal. Disponível em:
     * http://www.javaxt.com/Tutorials/Console_Apps/How_To_Prompt_a_User_for_a_Username_and_Password_from_the_Command_Line
     * @param prompt
     * @return 
     */
    public static String readNomeUsuario(String prompt){
        String username = null;
        System.out.print(prompt);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            username = br.readLine();
        } 
        catch (IOException e) {
            System.out.println("Error trying to read your name!");
            System.exit(1);
        }
        return username;
    }
 
    /**
     * Lê a senha inserida no terminal. Disponível em:
     * http://www.javaxt.com/Tutorials/Console_Apps/How_To_Prompt_a_User_for_a_Username_and_Password_from_the_Command_Line
     * @param prompt
     * @return 
     */
    public static String readSenha(String prompt) throws IOException {
        String password = "";
        ConsoleEraser consoleEraser = new ConsoleEraser();
        System.out.print(prompt);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        consoleEraser.start();
        try {
            password = in.readLine();
        }
        catch (IOException e){
            System.out.println("Error trying to read your password!");
            System.exit(1);
        }
 
        consoleEraser.halt();
        System.out.print("\b");
 
        return password;
    }
    
    /**
     * Classe utilitária para ajudar a camuflar a senha inserida pelo usuário.
     * Disponível em: http://www.javaxt.com/Tutorials/Console_Apps/How_To_Prompt_a_User_for_a_Username_and_Password_from_the_Command_Line
     */
    private static class ConsoleEraser extends Thread {
        private boolean running = true;
        public void run() {
            while (running) {
                System.out.print("\b ");
                try {
                    Thread.currentThread().sleep(1);
                }
                catch(InterruptedException e) {
                    break;
                }
            }
        }
        public synchronized void halt() {
            running = false;
        }
    }
}
