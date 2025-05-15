package edu.ufrn.gcm.service;

import edu.ufrn.gcm.model.AccountModel;

import java.util.ArrayList;
import java.util.List;

public class AccountService {

    private List<AccountModel> accounts;

    public AccountService(){
        this.accounts = new ArrayList<>();
    }

    public boolean createAccount(String number){
        if(number.isEmpty() ){
            return false;
        }
        this.accounts.add(new AccountModel(number, 0.0));
        return true;
    }

    public AccountModel getAccountByNumber(String number){
        for(AccountModel account : this.accounts){
            if(account.getNumber().equals(number)){
                return account;
            }
        }
        return null;
    }


}
