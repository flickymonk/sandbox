package com.alevel.moboperator.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "promotions")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, name = "start_date")
    private LocalDate start;

    @Column(nullable = false, name = "end_date")
    private LocalDate end;

    @ManyToMany
    @JoinTable(
            name = "promotions_for_tariffs",
            joinColumns = @JoinColumn(
                    name = "promotion_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "tariff_id",
                    referencedColumnName = "id"
            )
    )
    private List<Tariff> applicableTo = new ArrayList<>();

    public Promotion() {
    }

    public Promotion(String name, String description, LocalDate start, LocalDate end) {
        this.name = name;
        this.description = description;
        this.start = start;
        this.end = end;
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

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public List<Tariff> getApplicableTo() {
        return applicableTo;
    }

    public void setApplicableTo(List<Tariff> applicableTo) {
        this.applicableTo = applicableTo;
    }
}
