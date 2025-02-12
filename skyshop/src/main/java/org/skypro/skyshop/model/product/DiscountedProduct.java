package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int basePrice;
    private int discount;

   public DiscountedProduct(String name, int basePrice, int discount, UUID id) {
        super(name, id);
        if (basePrice <= 0 )  {
            throw new IllegalArgumentException("Цена должна быть больше нуля");
        }
        if (discount < 0 ||  discount > 100)  {
            throw new IllegalArgumentException("Размер скидки должен быть в диапазоне 0-100");
        }
        this.basePrice = basePrice;
        this.discount = discount;
    }


    @Override
    public int getPrice() {
        return basePrice - (int)(basePrice * discount * 0.01);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return  getName() + ": " + getPrice() + " (скидка " + discount + "%)";
    }

}