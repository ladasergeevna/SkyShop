package org.skypro.skyshop.service;


import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;


    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;

    }

    public void addProduct(UUID productId) {
        if (storageService.getProductById(productId).isPresent() == false) {
           throw new  NoSuchProductException();
        }
        productBasket.addProduct(productId);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> basketItems = productBasket.getProductsInBasket()
                .entrySet()
                .stream()
                .map(p -> new BasketItem(storageService.getProductById(p.getKey()).orElseThrow(), p.getValue()))
                .toList();
        return new UserBasket(basketItems);
    }

}
