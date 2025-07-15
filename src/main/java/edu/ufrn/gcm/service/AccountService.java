package edu.ufrn.gcm.service;

import edu.ufrn.gcm.model.AccountModel;
import edu.ufrn.gcm.model.BonusAccount;
import edu.ufrn.gcm.model.SavingsAccount;
import edu.ufrn.gcm.utils.TypeAccountEnum;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    // Teste Qualidade
    private List<AccountModel> accounts;

    public AccountService() {
        this.accounts = new ArrayList<>();
    }

    public boolean createAccount(String number, TypeAccountEnum typeAccount, Double initialBalance) {
        if (number.isEmpty() || initialBalance == null || initialBalance < 0) {
            return false;
        }
        switch (typeAccount) {
            case BONUS:
                this.accounts.add(new BonusAccount(number, initialBalance));
                break;
            case SAVINGS:
                this.accounts.add(new SavingsAccount(number, initialBalance));
                break;
            default:
                this.accounts.add(new AccountModel(number, initialBalance));
                break;
        }
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
        if (account != null && value != null && value > 0) {
            account.setTotal(account.getTotal() + value);
            if (account instanceof BonusAccount) {
                calculateScore((BonusAccount) account, value, 100);
            }
            return true;
        }

        return false;
    }

    public boolean debit(String number, Double value) {
        AccountModel account = getAccountByNumber(number);
        if (account != null && value != null && value > 0) {
            if (account instanceof SavingsAccount) {
                if (value > account.getTotal()) {
                    return false;
                }
                account.setTotal(account.getTotal() - value);
                return true;
            } else if (validateBalance(account, value)) {
                account.setTotal(account.getTotal() - value);
                return true;
            }
        }
        return false;
    }

    private boolean validateBalance(AccountModel account, double value) {
        Double balance = account.getTotal() - value;
        return balance >= -1000;
    }

    private void calculateScore(BonusAccount bonus, Double value, int baseValue) {
        int debit = value.intValue();
        int score = debit / baseValue;
        bonus.increaseScore(score);
    }

    public boolean transfer(String fromNumber, String toNumber, Double value) {
        AccountModel fromAccount = getAccountByNumber(fromNumber);
        AccountModel toAccount = getAccountByNumber(toNumber);

        if (fromAccount != null && toAccount != null && value != null && value > 0) {
            if (fromAccount instanceof SavingsAccount) {
                if (value > fromAccount.getTotal()) {
                    return false;
                }
                fromAccount.setTotal(fromAccount.getTotal() - value);
                toAccount.setTotal(toAccount.getTotal() + value);
                if (toAccount instanceof BonusAccount) {
                    calculateScore((BonusAccount) toAccount, value, 150);
                }
                return true;
            } else if (validateBalance(toAccount, value)) {
                fromAccount.setTotal(fromAccount.getTotal() - value);
                toAccount.setTotal(toAccount.getTotal() + value);
                if (toAccount instanceof BonusAccount) {
                    calculateScore((BonusAccount) toAccount, value, 150);
                }
                return true;
            }
        }
        return false;
    }

    public void renderInterest(double percentageRate) {
        for (AccountModel account : this.accounts) {
            if (account instanceof SavingsAccount) {
                ((SavingsAccount) account).renderInterest(percentageRate);
            }
        }
    }

    public List<AccountModel> getAllAccounts() {
        return accounts;
    }

}
