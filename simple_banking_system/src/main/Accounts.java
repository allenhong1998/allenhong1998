package main;

public class Accounts {

    private String name;
    private double balance;

    public Accounts(String name, double balance){
        this.name = name;
        this.balance = balance;
    }
    public String getName(){
        return this.name;
    }

    public double getBalance(){
        return this.balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
//Users can deposit money to their accounts
    public boolean deposit (double amount) {
        if (amount > 0){
            balance += amount;
            return true;
        }
        return false;
    }
//Users can withdraw money from their accounts and Users are not allowed to overdraft their accounts
    public boolean withdraw(double amount){
        if (amount < 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
//Users can transfer money to other accounts in the same banking system
    public boolean transfer (Accounts targeAccount, double amount){
        if (this.withdraw(amount)){
            targeAccount.deposit(amount);
            return true;
        }
        return false;
    }

}
