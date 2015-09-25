package com.cgm.storm.adapter.examples;

import java.math.BigDecimal;

/**
 *
 */
public class PersonTest extends PersonBase {

    // write your code here
    private BigDecimal money;

    private PetTest pet;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public PetTest getPet() {
        return pet;
    }

    public void setPet(PetTest pet) {
        this.pet = pet;
    }
}

