package oop.composition;

public class Invoice {

    private int id;
    private Customer customer;
    private double amount;

    public Invoice(int id, Customer customer, double amount) {
        this.id = id;
        this.customer = customer;
        this.amount = amount;
    }

    public int getID() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCustomerID() {
        return getCustomer().getID();
    }

    public String getCustomerName() {
        return getCustomer().getName();
    }

    public int getCustomerDiscount() {
        return 1;
    }

    public double getAmountAfterDiscount() {
        return amount / 100 * (100 - getCustomerDiscount());
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", customer=" + customer +
                ", amount=" + amount +
                '}';
    }
}
