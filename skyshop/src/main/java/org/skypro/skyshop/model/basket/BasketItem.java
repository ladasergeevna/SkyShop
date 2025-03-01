package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

import java.util.Objects;

public final class BasketItem {
    private final Product products;
    private Integer quantity;

    public BasketItem(Product products, Integer quantity) {
        this.products = products;
        this.quantity = quantity;
    }

    public Product getProducts() {
        return products;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketItem that = (BasketItem) o;
        return Objects.equals(products, that.products) && Objects.equals(quantity, that.quantity);
    }
    @Override
    public int hashCode() {
        return Objects.hash(products, quantity);
    }
}
