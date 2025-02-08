package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final TreeMap<UUID, Product> products;
    private final TreeMap<UUID, Article> articles;


    public StorageService() {
        this.products = new TreeMap<>();
        this.articles = new TreeMap<>();
        addData();
    }

    private void addData() {
        products.put(UUID.randomUUID(), new SimpleProduct("Платье", 3000, UUID.randomUUID()));
        products.put(UUID.randomUUID(), new SimpleProduct("Ролики", 2500, UUID.randomUUID()));
        products.put(UUID.randomUUID(), new FixPriceProduct("Крем", UUID.randomUUID()));
        products.put(UUID.randomUUID(), new DiscountedProduct("Конфеты", 500, 5, UUID.randomUUID()));
        products.put(UUID.randomUUID(), new DiscountedProduct("Леденец", 150, 10, UUID.randomUUID()));
        articles.put(UUID.randomUUID(), new Article("Инфо о конфетах", "Конфеты из молочного шоколада в подарочной коробке", UUID.randomUUID()));
        articles.put(UUID.randomUUID(), new Article("Инфо о платье", "Платье зеленое 42 размера", UUID.randomUUID()));
        articles.put(UUID.randomUUID(), new Article("Инфо о конфетах", "Конфеты из молочного шоколада с начинкой из шоколада", UUID.randomUUID()));
        articles.put(UUID.randomUUID(), new Article("Инфо о леденцах", "Конфеты леденцовые", UUID.randomUUID()));

    }

    public Collection<Article> getArticles() {
        return articles.values();
    }

    public Collection<Product> getProducts() {
        return products.values();
    }

    public List<Searchable> getSearchables() {
        List<Searchable> searchables = new ArrayList<>();
        searchables.addAll(products.values());
        searchables.addAll(articles.values());
        return searchables;
    }
}

