package edu.ufrn.gcm;

import edu.ufrn.gcm.controller.AccountController;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountController controller = new AccountController();
        int option = -1;
        String menuOptions = "Olá, Seja Bem-vindo ao Minibanco\nEscolha uma das opções abaixo:\n" +
                "1 para Cadatrar conta\n" +
                "2 para consultar saldo de uma conta\n" +
                "3 para crédito\n" +
                "4 para débito\n" +
                "5 para transferência\n" +
                "0 para sair\nDigite: ";

        do {
            System.out.println(menuOptions);
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Informe o número da conta");
                    String number = scanner.next();
                    String result = controller.createAccount(number);
                    System.out.println(result);
                    break;

            }

        } while (option != 0);
    }
}
