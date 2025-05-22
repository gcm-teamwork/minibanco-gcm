package edu.ufrn.gcm.service;

import edu.ufrn.gcm.model.AccountModel;

import java.util.ArrayList;
import java.util.List;

public class AccountService {

    private List<AccountModel> accounts;

    public AccountService() {
        this.accounts = new ArrayList<>();
    }

    public boolean createAccount(String number) {
        if (number.isEmpty()) {
            return false;
        }
        this.accounts.add(new AccountModel(number, 0.0));
        return true;
    }

    public AccountModel getAccountByNumber(String number) {
        for (AccountModel account : this.accounts) {
            if (account.getNumber().equals(number)) {
                return account;
            }
        }
        return null;
    }

    public boolean credit(String number, Double value) {
        AccountModel account = getAccountByNumber(number);
        if (account != null && value != null) {
            account.setTotal(account.getTotal() + value);
            return true;
        }
        return false;
    }

    public boolean debit(String number, Double value) {
        AccountModel account = getAccountByNumber(number);
        if (account != null && value != null) {
            if (value > account.getTotal()) {
                return false;
            }
            account.setTotal(account.getTotal() - value);
            return true;
        }
        return false;
    }

    public boolean transfer(String fromNumber, String toNumber, Double value) {
        AccountModel fromAccount = getAccountByNumber(fromNumber);
        AccountModel toAccount = getAccountByNumber(toNumber);

        if (fromAccount != null && toAccount != null && value != null) {
            if (value > fromAccount.getTotal()) {
                return false;
            }
            fromAccount.setTotal(fromAccount.getTotal() - value);
            toAccount.setTotal(toAccount.getTotal() + value);
            return true;
        }
        return false;
    }

}
