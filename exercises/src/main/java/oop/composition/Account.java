package oop.composition;

public class Account {

    private int id;
    private Customer customer;
    private double balance;

    public Account(int id, Customer customer, double balance) {
        this.id = id;
        this.customer = customer;
        this.balance = balance;
    }

    public Account(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", customer=" + customer +
                ", balance=" + balance +
                '}';
    }

    public String getCustomerName() {
        return getCustomer().getName();
    }

    public Account deposit(double amount) {
        return new Account(id, customer, balance + amount);
    }

    public Account withdraw(double amount) {
        if(balance >= amount){
            return new Account(id, customer, balance-amount);
        } else{
            System.out.println("No enough money");
            return new Account(id, customer, balance);
        }

    }


}
