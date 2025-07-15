package edu.ufrn.gcm.service;

import edu.ufrn.gcm.model.*;
import edu.ufrn.gcm.utils.TypeAccountEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {

    private AccountService service;

    @BeforeEach
    void setUp() {
        service = new AccountService();
    }

    // Cadastrar Conta
    @Test
    void testCreateRegularAccount() {
        boolean result = service.createAccount("001", TypeAccountEnum.REGULAR, 100.0);
        assertTrue(result);
        assertInstanceOf(AccountModel.class, service.getAccountByNumber("001"));
    }

    @Test
    void testCreateBonusAccount() {
        boolean result = service.createAccount("002", TypeAccountEnum.BONUS, 100.0);
        assertTrue(result);
        assertInstanceOf(BonusAccount.class, service.getAccountByNumber("002"));
    }

    @Test
    void testCreateSavingsAccount() {
        boolean result = service.createAccount("003", TypeAccountEnum.SAVINGS, 100.0);
        assertTrue(result);
        assertInstanceOf(SavingsAccount.class, service.getAccountByNumber("003"));
    }

    // Consultar Conta
    @Test
    void testGetBonusAccountByNumber() {
        service.createAccount("004", TypeAccountEnum.BONUS, 200.0);
        AccountModel acc = service.getAccountByNumber("004");
        assertNotNull(acc);
        assertInstanceOf(BonusAccount.class, acc);
    }

    @Test
    void testGetUnknownAccount() {
        AccountModel acc = service.getAccountByNumber("999");
        assertNull(acc);
    }

    // Consultar Saldo
    @Test
    void testGetAccountBalance() {
        service.createAccount("005", TypeAccountEnum.REGULAR, 500.0);
        AccountModel acc = service.getAccountByNumber("005");
        assertEquals(500.0, acc.getTotal());
    }

    // Crédito
    @Test
    void testCreditNormalCase() {
        service.createAccount("006", TypeAccountEnum.REGULAR, 100.0);
        boolean result = service.credit("006", 50.0);
        assertTrue(result);
        assertEquals(150.0, service.getAccountByNumber("006").getTotal());
    }

    @Test
    void testCreditNegativeValue() {
        service.createAccount("007", TypeAccountEnum.REGULAR, 100.0);
        boolean result = service.credit("007", -10.0);
        assertFalse(result);
    }

    @Test
    void testCreditBonusAccountIncreasesScore() {
        service.createAccount("008", TypeAccountEnum.BONUS, 100.0);
        service.credit("008", 200.0);
        BonusAccount acc = (BonusAccount) service.getAccountByNumber("008");
        assertEquals(12, acc.getScore());
    }

    // Débito
    @Test
    void testDebitNormalCase() {
        service.createAccount("009", TypeAccountEnum.REGULAR, 100.0);
        boolean result = service.debit("009", 50.0);
        assertTrue(result);
        assertEquals(50.0, service.getAccountByNumber("009").getTotal());
    }

    @Test
    void testDebitNegativeValue() {
        service.createAccount("010", TypeAccountEnum.REGULAR, 100.0);
        boolean result = service.debit("010", -50.0);
        assertFalse(result);
    }

    @Test
    void testDebitOverdraftLimit() {
        service.createAccount("011", TypeAccountEnum.REGULAR, 100.0);
        boolean result = service.debit("011", 1200.0);
        assertTrue(result);
    }

    // Transferência
    @Test
    void testTransferNegativeValue() {
        service.createAccount("012", TypeAccountEnum.REGULAR, 1000.0);
        service.createAccount("013", TypeAccountEnum.REGULAR, 100.0);
        boolean result = service.transfer("012", "013", -100.0);
        assertFalse(result);
    }

    @Test
    void testTransferOverdraftLimit() {
        service.createAccount("014", TypeAccountEnum.REGULAR, 0.0);
        service.createAccount("015", TypeAccountEnum.REGULAR, 0.0);
        boolean result = service.transfer("014", "015", 2000.0);
        assertTrue(result);
    }

    @Test
    void testTransferToBonusAccountIncreasesScore() {
        service.createAccount("016", TypeAccountEnum.REGULAR, 1000.0);
        service.createAccount("017", TypeAccountEnum.BONUS, 0.0);
        service.transfer("016", "017", 300.0);
        BonusAccount bonus = (BonusAccount) service.getAccountByNumber("017");
        assertEquals(12, bonus.getScore());
    }

    // Render Juros
    @Test
    void testRenderInterestForMultipleSavingsAccounts() {
        service.createAccount("018", TypeAccountEnum.SAVINGS, 1000.0);
        service.createAccount("019", TypeAccountEnum.SAVINGS, 500.0);
        service.createAccount("020", TypeAccountEnum.BONUS, 2000.0);

        service.renderInterest(10.0);

        SavingsAccount acc1 = (SavingsAccount) service.getAccountByNumber("018");
        SavingsAccount acc2 = (SavingsAccount) service.getAccountByNumber("019");

        assertEquals(1100.0, acc1.getTotal());
        assertEquals(550.0, acc2.getTotal());

        BonusAccount bonusAcc = (BonusAccount) service.getAccountByNumber("020");
        assertEquals(2000.0, bonusAcc.getTotal());
    }

}
