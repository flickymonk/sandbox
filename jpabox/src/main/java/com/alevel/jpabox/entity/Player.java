package com.alevel.jpabox.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer score;

    @ManyToOne
    @JoinColumn(name = "guild_id")
    private Guild guild;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "player_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "class_id", referencedColumnName = "id")
    )
    private List<PlayerClass> classes = new ArrayList<>();

    public Player() {
    }

    public Player(String name, Integer score, Guild guild) {
        this.name = name;
        this.score = score;
        this.guild = guild;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public List<PlayerClass> getClasses() {
        return classes;
    }

    public void setClasses(List<PlayerClass> classes) {
        this.classes = classes;
    }

    public void addClass(PlayerClass playerClass) {
        classes.add(playerClass);
        playerClass.getPlayers().add(this);
    }
}
