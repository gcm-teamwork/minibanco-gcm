package edu.ufrn.gcm.controller;

import edu.ufrn.gcm.service.AccountService;


public class AccountController {

    private AccountService service = new AccountService();

    public String createAccount(String number){
        boolean createWithSucces = service.createAccount(number);
        if(createWithSucces){
            return "Conta criada com sucesso!";
        }else{
            return "Erro ao criar conta!";
        }
    }

}
