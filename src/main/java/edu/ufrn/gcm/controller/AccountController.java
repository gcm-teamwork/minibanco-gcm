package edu.ufrn.gcm.controller;

import edu.ufrn.gcm.dto.AccountInfoDTO;
import edu.ufrn.gcm.dto.AmountDTO;
import edu.ufrn.gcm.dto.CreateAccountDTO;
import edu.ufrn.gcm.dto.InterestDTO;
import edu.ufrn.gcm.dto.TransferDTO;
import edu.ufrn.gcm.model.AccountModel;
import edu.ufrn.gcm.model.BonusAccount;
import edu.ufrn.gcm.model.SavingsAccount;
import edu.ufrn.gcm.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banco/conta")
public class AccountController {
    private final AccountService accountService = new AccountService();

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountDTO dto) {
        boolean created = accountService.createAccount(dto.number(), dto.type(), dto.initialBalance());
        return created ? ResponseEntity.ok("Conta criada com sucesso!")
                : ResponseEntity.badRequest().body("Erro ao criar conta.");
    }

    @GetMapping
    public ResponseEntity<List<AccountModel>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{number}/info")
    public ResponseEntity<AccountInfoDTO> getAccountInfo(@PathVariable String number) {
        AccountModel account = accountService.getAccountByNumber(number);
        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

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

        AccountInfoDTO dto = new AccountInfoDTO(
                type,
                account.getNumber(),
                account.getTotal(),
                score);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{number}/saldo")
    public ResponseEntity<String> getBalance(@PathVariable String number) {
        AccountModel account = accountService.getAccountByNumber(number);
        if (account != null) {
            return ResponseEntity.ok("Saldo: R$ " + account.getTotal());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
    }

    @PutMapping("/{number}/credito")
    public ResponseEntity<String> credit(@PathVariable String number, @RequestBody AmountDTO dto) {
        boolean result = accountService.credit(number, dto.amount());
        return result ? ResponseEntity.ok("Crédito realizado.")
                : ResponseEntity.badRequest().body("Erro ao realizar crédito.");
    }

    @PutMapping("/{number}/debito")
    public ResponseEntity<String> debit(@PathVariable String number, @RequestBody AmountDTO dto) {
        boolean result = accountService.debit(number, dto.amount());
        return result ? ResponseEntity.ok("Débito realizado.")
                : ResponseEntity.badRequest().body("Erro ao realizar débito.");
    }

    @PutMapping("/transferencia")
    public ResponseEntity<String> transfer(@RequestBody TransferDTO dto) {
        boolean result = accountService.transfer(dto.from(), dto.to(), dto.amount());
        return result ? ResponseEntity.ok("Transferência realizada.")
                : ResponseEntity.badRequest().body("Erro ao transferir.");
    }

    @PutMapping("/rendimento")
    public ResponseEntity<String> renderInterest(@RequestBody InterestDTO dto) {
        accountService.renderInterest(dto.rate());
        return ResponseEntity.ok("Rendimentos aplicados.");
    }
}
