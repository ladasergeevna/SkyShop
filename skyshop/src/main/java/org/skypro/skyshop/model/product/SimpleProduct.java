package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int simplePrice;

    public SimpleProduct(String name, int simplePrice, UUID id) {
        super(name, id);
        if (simplePrice <= 0 )  {
            throw new IllegalArgumentException("Цена должна быть больше нуля");
        }
        this.simplePrice = simplePrice;
    }

    @Override
    public int getPrice() {
        return simplePrice;

    }
    @Override
    public boolean isSpecial() {
        return false;
    }
    @Override
    public String toString() {
        return  getName() + ": " + getPrice ();
    }
}
