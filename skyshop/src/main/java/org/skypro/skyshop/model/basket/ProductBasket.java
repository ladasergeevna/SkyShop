package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> productBasket;

    public ProductBasket() {
        this.productBasket = new TreeMap<>();
    }

    public void addProduct(UUID productId) {
        if (productBasket.containsKey(productId))
        {productBasket.put(productId, productBasket.get(productId) + 1);
        }
        else {
            productBasket.put(productId, 1);
        }
    }

    public Map<UUID, Integer> getProductsInBasket() {
        return Collections.unmodifiableMap(productBasket);
    }
}

