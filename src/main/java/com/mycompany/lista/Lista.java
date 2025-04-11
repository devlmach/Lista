package com.mycompany.lista;

import java.sql.Connection;
import java.util.Scanner;

public class Lista {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        ConexaoBD conect_to_db = new ConexaoBD();
        Connection conexao = conect_to_db.conectarBD(bdName);
        
        while (true) {            
            System.out.println("O que gostaria de fazer? "
                    + "1 - CRIAR TABELA "
                    + "2 - ADICIONAR ALGUM USUARIO NA LISTA TELEFONICA "
                    + "3 - DELETAR CONTATO "
                    + "4 - VISUALIZAR SUA LISTA TELEFONICA "
                    + "5 - SAIR"
                    + "OPCAO ");
            int opcao = sc.nextInt();
            if (opcao == 1) {
                conect_to_db.createTable(conexao, tableName);
            } else if (opcao == 2) {
                conect_to_db.updateTable(conexao, tableName);
            } else if (opcao == 3) {
                conect_to_db.deleteTable(conexao, tableName);
            } else if (opcao == 4) {
                conect_to_db.readTable(conexao, tableName);
            } else if (opcao == 5) {
                System.exit(0);
                sc.close();
            } else {
                System.out.println("OPCAO INVÁLIDA");
            }
        }

        // teste
    }
}