package edu.ufrn.gcm;

import edu.ufrn.gcm.controller.AccountController;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            AccountController controller = new AccountController();
            int option = -1;
            String menuOptions = "Olá, Seja Bem-vindo ao Minibanco\nEscolha uma das opções abaixo:\n" +
                    "1 para Cadatrar conta\n" +
                    "2 para Consultar saldo de uma conta\n" +
                    "3 para Crédito\n" +
                    "4 para Débito\n" +
                    "5 para Transferência\n" +
                    "0 para sair\nDigite: ";

            do {
                System.out.println(menuOptions);
                option = scanner.nextInt();
                String number = "";
                switch (option) {
                    case 1:
                        System.out.println("Informe o número da conta para criar:");
                        number = scanner.next();
                        String result = controller.createAccount(number);
                        System.out.println(result);
                        break;
                    case 2:
                        System.out.println("Informe o número da conta para consultar:");
                        number = scanner.next();
                        String account = controller.getAccountByNumber(number);
                        System.out.println(account);
                        break;
                    case 3:
                        System.out.println("Informe o número da conta:");
                        number = scanner.next();
                        System.out.println("Informe o valor do crédito:");
                        Double value = scanner.nextDouble();
                        String creditResult = controller.credit(number, value);
                        System.out.println(creditResult);
                        break;
                    case 4:
                        System.out.println("Informe o número da conta:");
                        number = scanner.next();
                        System.out.println("Informe o valor do débito:");
                        Double debitValue = scanner.nextDouble();
                        String debitResult = controller.debit(number, debitValue);
                        System.out.println(debitResult);
                        break;
                    case 5:
                        System.out.println("Informe o número da conta de origem:");
                        String from = scanner.next();
                        System.out.println("Informe o número da conta de destino:");
                        String to = scanner.next();
                        System.out.println("Informe o valor da transferência:");
                        Double transferValue = scanner.nextDouble();
                        String transferResult = controller.transfer(from, to, transferValue);
                        System.out.println(transferResult);
                        break;

                }

            } while (option != 0);
        }
    }
}
