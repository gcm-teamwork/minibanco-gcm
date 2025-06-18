package edu.ufrn.gcm.dto;

public record TransferDTO(
        String from,
        String to,
        Double amount
) {}