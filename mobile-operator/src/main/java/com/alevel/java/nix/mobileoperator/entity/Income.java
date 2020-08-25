package com.alevel.java.nix.mobileoperator.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("income")
public class Income extends Operation {
}
