package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {
    private final List<BasketItem> basketItems;
    private final int totalPrice;

    public UserBasket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
        this.totalPrice = basketItems.stream().mapToInt(i -> i.getProducts().getPrice() * i.getQuantity()).sum();
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
