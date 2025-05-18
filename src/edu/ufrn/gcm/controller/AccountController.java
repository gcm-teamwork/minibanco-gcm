package edu.ufrn.gcm.controller;

import edu.ufrn.gcm.model.AccountModel;
import edu.ufrn.gcm.service.AccountService;

public class AccountController {

    private AccountService service = new AccountService();

    public String createAccount(String number) {
        boolean createWithSucces = service.createAccount(number);
        if (createWithSucces) {
            return "Conta criada com sucesso!";
        } else {
            return "Erro ao criar conta!";
        }
    }

    public String getAccountByNumber(String number) {
        AccountModel accountModel = this.service.getAccountByNumber(number);
        if (accountModel != null) {
            return "O saldo da conta " + accountModel.getNumber() + " é R$" + accountModel.getTotal();
        }
        return "Conta não encontrada!";
    }

    public String credit(String number, Double value) {
        boolean credited = service.credit(number, value);
        if (credited) {
            return "Crédito realizado com sucesso!";
        } else {
            return "Erro ao realizar crédito. Verifique os dados informados.";
        }
    }

    public String debit(String number, Double value) {
        boolean debited = service.debit(number, value);
        if (debited) {
            return "Débito realizado com sucesso!";
        } else {
            return "Erro ao realizar débito. Verifique os dados informados.";
        }
    }

}
