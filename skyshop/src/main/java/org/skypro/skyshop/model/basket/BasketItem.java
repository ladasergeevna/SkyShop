package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

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
}
