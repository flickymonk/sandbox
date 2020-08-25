package com.alevel.java.nix.mobileoperator.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("expense")
public class Expense extends Operation {
}
