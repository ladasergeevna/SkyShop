package org.skypro.skyshop.model.basket;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBasket that = (UserBasket) o;
        return Objects.equals(basketItems, that.basketItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketItems);
    }
}
