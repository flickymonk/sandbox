package com.alevel.moboperator.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tariffs")
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "monthly_payment", nullable = false)
    private long monthlyPayment;

    @ManyToMany(mappedBy = "applicableTo")
    private List<Promotion> promotions = new ArrayList<>();

    public Tariff() {
    }

    public Tariff(String name, String description, long monthlyPayment) {
        this.name = name;
        this.description = description;
        this.monthlyPayment = monthlyPayment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(long monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }
}
