package oop.classes;

public class Account {

    private String is;
    private String name;
    private int balance;

    public Account(String is, String name) {
        this.is = is;
        this.name = name;
        this.balance = 0;
    }

    public Account(String is, String name, int balance) {
        this.is = is;
        this.name = name;
        this.balance = balance;
    }

    public String getIs() {
        return is;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public int credit(int amount) {
        return balance + amount;
    }

    public int debit(int amount) {
        if (amount <= balance) {
            balance -= amount;
            return balance - amount;
        } else {
            System.out.println("Amount exceeded balance");
            return balance;
        }
    }

    public int transferTo(Account account, int amount) {
        if (amount <= balance) {
            debit(amount);
            account.balance += amount;
        } else {
            System.out.println("Amount exceeded balance");
        }
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "is='" + is + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}