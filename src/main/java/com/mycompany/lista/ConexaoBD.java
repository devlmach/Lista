package com.mycompany.lista;

import java.lang.System;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class ConexaoBD {   
    Scanner sc = new Scanner(System.in);
    
    public Connection conectarBD(String bdName) {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + bdName, "postgres", "1234");
            if (conexao != null) {
                System.out.println("CONEXÃO BEM SUSCEDIDA");
            } else {
                System.out.println("CONEXÃO MAL SUSCEDIDA");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conexao;       
    }
    
    public void createTable(Connection conexao, String tableName) {
        Statement stn;
        try {
            String sql = "CREATE TABLE " + tableName + "(id_user SERIAL PRIMARY KEY,"
                + "nome VARCHAR(10),"
                + "tel VARCHAR(11));";
            stn = conexao.createStatement();
            stn.executeUpdate(sql);
            System.out.println("TABELA CRIADA");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateTable(Connection conexao, String tableName) {
        try {
            System.out.println("DIGITE O NOME DA PESSOA: ");
            String nome = sc.nextLine();
            System.out.println("DIGITE O TELEFONE: ");
            String tel = sc.nextLine();
            
            String sql = "INSERT INTO " + tableName + " (nome, tel) VALUES (?, ?)";            
            
            try {
                PreparedStatement pstn = conexao.prepareStatement(sql);
                pstn.setString(1, nome);
                pstn.setString(2, tel);
                
                int add = pstn.executeUpdate();
                System.out.println(add);
            } catch (Exception e) {
            e.printStackTrace();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
     public void readTable(Connection conexao, String tableName) {
        Statement stn;
        String sql = "SELECT id_user, nome, tel FROM " + tableName;
        try {
            stn = conexao.createStatement();
            ResultSet result = stn.executeQuery(sql);
            while (result.next()) {
                System.out.println("ID: " + result.getInt("id_user") + ", Nome: " + result.getString("nome") + ", Telelefone: " + result.getString("tel"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     public void deleteTable(Connection conexao, String tableName) { 
         
         try {
             readTable(conexao, tableName);
             
             System.out.println("Quem você gostaria de deletar da lista? INDIQUE ATRAVÉS DO ID: ");
             int idUserDel = sc.nextInt();                          
             
             String sql = "DELETE FROM " + tableName + " WHERE id_user = ?";
             
             try {
                 PreparedStatement pstn = conexao.prepareStatement(sql);
                 pstn.setInt(1, idUserDel);
                 int add = pstn.executeUpdate();
                 
             } catch (Exception e) {
                 e.printStackTrace();
             }            
             readTable(conexao, tableName);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
    
}