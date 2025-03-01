package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    ProductBasket productBasket;
    @Mock
    StorageService storageService;
    @InjectMocks
    private BasketService basketService = new BasketService(
            new ProductBasket(), new StorageService());
    @BeforeEach
    void setUp() {
        productBasket = Mockito.mock(ProductBasket.class);
        storageService = Mockito.mock(StorageService.class);
        basketService = new BasketService(productBasket, storageService);
    }

    // Проверка сценария - Добавление несуществующего товара в корзину приводит к выбросу исключения
    @Test
    void exceptionTesting() {
        UUID nonExistentProductId = UUID.randomUUID();
        Mockito.lenient().when(storageService.getProductById(nonExistentProductId)).thenReturn(Optional.empty());
        assertThrows(NoSuchProductException.class, () -> {
            basketService.addProduct(nonExistentProductId);
        });

        Exception exception = assertThrows(NoSuchProductException.class, () ->
                basketService.addProduct(nonExistentProductId));
        assertEquals("Нет такого продукта", exception.getMessage());

    }

    // Проверка сценария - Добавление существующего товара вызывает метод addProduct у мока ProductBasket
    @Test
    void testIfAddMethodWasCalledOnProductBasket() {
        UUID existingProductId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        when(storageService.getProductById(existingProductId)).thenReturn(Optional.of(new SimpleProduct("Ролики", 2500, existingProductId)));
        basketService.addProduct(existingProductId);
        verify(productBasket).addProduct(existingProductId);
    }

    // Проверка сценария - Метод getUserBacket возвращает пустую корзину, если ProductBasket пуст
    @Test
    void testIfProductBasketIsEmpty() {
        when(productBasket.getProductsInBasket()).thenReturn(Collections.emptyMap());
        UserBasket userBasket = basketService.getUserBasket();
        assertEquals(Collections.emptyList(), userBasket.getBasketItems());
    }

    // Проверка сценария - Метод getUserBasket возвращает подходящую корзину, если в ProductBasket есть товары.
    @Test
    void testIfProductBasketHasItems() {
        UUID productId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        int quantity = 1;

        // Возвращаем корзину с одним товаром
        when(productBasket.getProductsInBasket()).thenReturn(Collections.singletonMap(productId, quantity));

        // Возвращаем продукт по ID
        BasketItem basketItem = new BasketItem(new SimpleProduct("Ролики", 2500, productId), quantity);
        when(storageService.getProductById(productId)).thenReturn(Optional.of(basketItem.getProducts()));

        // Получаем корзину пользователя
        UserBasket userBasket = basketService.getUserBasket();

        // Проверяем, что корзина пользователя содержит один элемент
        List<BasketItem> expectedItems = Collections.singletonList(basketItem);
        assertEquals(expectedItems, userBasket.getBasketItems());
    }
}
