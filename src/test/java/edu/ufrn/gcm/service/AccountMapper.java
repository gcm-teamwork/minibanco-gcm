package edu.ufrn.gcm.service;

import edu.ufrn.gcm.dto.AccountInfoDTO;
import edu.ufrn.gcm.model.*;

public class AccountMapper {

    public static AccountInfoDTO toDTO(AccountModel account) {
        String type;
        Integer score = null;

        if (account instanceof BonusAccount bonusAccount) {
            type = "BÔNUS";
            score = bonusAccount.getScore();
        } else if (account instanceof SavingsAccount) {
            type = "POUPANÇA";
        } else {
            type = "REGULAR";
        }

        return new AccountInfoDTO(type, account.getNumber(), account.getTotal(), score);
    }
}
