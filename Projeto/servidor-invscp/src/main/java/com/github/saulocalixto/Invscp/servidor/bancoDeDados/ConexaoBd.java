package com.github.saulocalixto.Invscp.servidor.bancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBd {
    public static String status = "Não conectou...";

    public ConexaoBd() {

    }

    public static java.sql.Connection getConexaoMySQL() {
        Connection connection = null;          //atributo do tipo Connection
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);

            String serverName = "localhost";    //caminho do servidor do BD
            String port = "3306";        //porta do seu banco de dados
            String databaseName = "invscp";     //nome do seu banco no MySQL
            String url = "jdbc:mysql://" + serverName + ":" + port + "/" + databaseName;
            String username = "admin";        //nome de um usuário de seu BD
            String password = "123456";      //sua senha de acesso
            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {

                status = ("STATUS--->Conectado com sucesso!");

            } else {

                status = ("STATUS--->Não foi possivel realizar conexão");
            }

            System.gc();
            return connection;

        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println("O driver expecificado nao foi encontrado.");
            return null;

        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao Banco de Dados. + " + e.getMessage());
            return null;
        }
    }

    public static String statusConection() {
        return status;
    }

    public static boolean FecharConexao() {
        try {
            ConexaoBd.getConexaoMySQL().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        return ConexaoBd.getConexaoMySQL();
    }
}
