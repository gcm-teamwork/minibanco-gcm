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

}
