package edu.ufrn.gcm;

import java.util.Scanner;

class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        String menuOptions = "Olá, Seja Bem-vindo ao Minibanco\nEscolha uma das opções abaixo:\n" +
                "1 para Cadatrar conta\n" +
                "2 para consultar saldo de uma conta\n" +
                "3 para crédito\n" +
                "4 para débito\n" +
                "5 para transferência\n" +
                "0 para sair\nDigite: ";
        System.out.println(menuOptions);
        do {
            option = scanner.nextInt();
        }while (option !=0);
    }
}
