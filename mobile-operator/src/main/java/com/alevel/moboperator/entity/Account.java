package com.alevel.moboperator.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@NamedEntityGraph(
        name = "account-with-promotions",
        attributeNodes = {
                @NamedAttributeNode("phoneNumber"),
                @NamedAttributeNode("balance"),
                @NamedAttributeNode(value = "tariff", subgraph = "tariffs-with-promotions")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "tariffs-with-promotions",
                        type = Tariff.class,
                        attributeNodes = @NamedAttributeNode(value = "promotions", subgraph = "promotions")
                ),
                @NamedSubgraph(
                        name = "promotions",
                        type = Promotion.class,
                        attributeNodes = {
                                @NamedAttributeNode("name"),
                                @NamedAttributeNode("description"),
                                @NamedAttributeNode("start"),
                                @NamedAttributeNode("end")
                        }
                )
        }
)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private long balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tariff_id", nullable = false)
    private Tariff tariff;

    public Account() {
    }

    public Account(String phoneNumber, Customer customer, Tariff tariff) {
        this.phoneNumber = phoneNumber;
        this.customer = customer;
        this.customer.getAccounts().add(this);
        this.tariff = tariff;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }
}
