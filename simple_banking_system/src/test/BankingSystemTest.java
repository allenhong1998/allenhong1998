package test;

import main.Accounts;
import main.BankSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankingSystemTest {

    private BankSystem system;

    @BeforeEach
    public void setUp() {
        system = new BankSystem();
    }

    @Test
    public void testCreateAccount() {
        assertTrue(system.createAccount("A", 100));
        assertEquals(100, system.getAccount("A").getBalance());
        assertFalse(system.createAccount("A", 200));
    }

    @Test
    public void testDeposit() {
        system.createAccount("B", 50);
        Accounts account = system.getAccount("B");
        assertTrue(account.deposit(50));
        assertEquals(100, account.getBalance());
        assertFalse(account.deposit(-10));
    }

    @Test
    public void testWithdraw() {
        system.createAccount("C", 200);
        Accounts account = system.getAccount("C");
        assertTrue(account.withdraw(100));
        assertEquals(100, account.getBalance());
        assertFalse(account.withdraw(150));
        assertFalse(account.withdraw(-10));
    }

    @Test
    public void testTransfer() {
        system.createAccount("D", 300);
        system.createAccount("E", 100);
        Accounts davidAccount = system.getAccount("D");
        Accounts eveAccount = system.getAccount("E");
        assertTrue(davidAccount.transfer(eveAccount, 100));
        assertEquals(200, davidAccount.getBalance());
        assertEquals(200, eveAccount.getBalance());
        assertFalse(davidAccount.transfer(eveAccount, 500));
    }
}