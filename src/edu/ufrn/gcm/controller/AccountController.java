package edu.ufrn.gcm.controller;

import edu.ufrn.gcm.model.AccountModel;
import edu.ufrn.gcm.model.BonusAccount;
import edu.ufrn.gcm.service.AccountService;
import edu.ufrn.gcm.utils.TypeAccountEnum;

public class AccountController {

    private AccountService service = new AccountService();

    public String createAccount(String number, int typeAccount) {
        boolean createWithSucces = service.createAccount(number, getTypeAccount(typeAccount));
        if (createWithSucces) {
            return "Conta criada com sucesso!";
        } else {
            return "Erro ao criar conta!";
        }
    }

    public String getAccountByNumber(String number) {
        AccountModel accountModel = this.service.getAccountByNumber(number);
        if (accountModel != null) {
            String accountData = "O saldo da conta " + accountModel.getNumber() + " é R$" + accountModel.getTotal();
            if (accountModel instanceof BonusAccount) {
                BonusAccount bonusAccount = (BonusAccount) accountModel;
                String score = "A pontuação da conta é " + bonusAccount.getScore();
                accountData += "\n" + score;
            }
            return accountData;
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

    public String transfer(String from, String to, Double value) {
        boolean transferred = service.transfer(from, to, value);
        if (transferred) {
            return "Transferência realizada com sucesso!";
        } else {
            return "Erro ao realizar transferência. Verifique os dados informados.";
        }
    }

    private TypeAccountEnum getTypeAccount(int typeAccount) {
        switch (typeAccount) {
            case 2:
                return TypeAccountEnum.BONUS;
            case 3:
                return TypeAccountEnum.SAVINGS;
            default:
                return TypeAccountEnum.REGULAR;
        }
    }

    public String renderInterest(Double rate) {
        service.renderInterest(rate);
        return "Juros aplicados com sucesso em todas as contas poupança.";
    }

}
