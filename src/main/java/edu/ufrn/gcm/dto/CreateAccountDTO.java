package edu.ufrn.gcm.dto;

import edu.ufrn.gcm.utils.TypeAccountEnum;

public record CreateAccountDTO(
        String number,
        TypeAccountEnum type,
        Double initialBalance
) {}