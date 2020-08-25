package com.alevel.java.nix.mobileoperator.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@NamedEntityGraph(
        name = "client-accounts",
        includeAllAttributes = true,
        attributeNodes = {
                @NamedAttributeNode(value = "accounts", subgraph = "accounts")
        },
        subgraphs = @NamedSubgraph(
                name = "accounts",
                attributeNodes = {
                        @NamedAttributeNode("phoneNumber"),
                })
)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Username username;

    @Column(nullable = false)
    private LocalDate birthday;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Account> accounts = new ArrayList<>();

    @Embedded
    private Timestamps timestamps;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Timestamps getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(Timestamps timestamps) {
        this.timestamps = timestamps;
    }

    public void addAccount(Account account) {
        account.setClient(this);
        accounts.add(account);
    }
}
