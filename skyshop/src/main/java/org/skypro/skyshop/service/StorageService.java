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
        Product dress = new SimpleProduct("Платье", 3000, UUID.randomUUID());
        Product rollers = new SimpleProduct("Ролики", 2500, UUID.randomUUID());
        Product cream = new FixPriceProduct("Крем", UUID.randomUUID());
        Product sweets = new DiscountedProduct("Конфеты", 500, 5, UUID.randomUUID());
        Product lollipop = new DiscountedProduct("Леденец", 150, 10, UUID.randomUUID());
        Article dressGreenArticle = new Article("Инфо о зеленом платье", "Платье зеленое 42 размера", UUID.randomUUID());
        Article dressRedArticle = new Article("Инфо о красном платье", "Платье красное 50 размера", UUID.randomUUID());
        Article sweetArticle = new Article("Инфо о конфетах в упаковке", "Конфеты из молочного шоколада в подарочной коробке", UUID.randomUUID());
        Article chocolateArticle = new Article("Инфо о конфетах", "Конфеты из молочного шоколада с начинкой из шоколада", UUID.randomUUID());
        Article lollipopArticle = new Article("Инфо о леденцах", "Конфеты леденцовые", UUID.randomUUID());
        this.products.put(rollers.getId(), rollers);
        this.products.put(dress.getId(), dress);
        this.products.put(cream.getId(), cream);
        this.products.put(sweets.getId(), sweets);
        this.products.put(lollipop.getId(), lollipop);
        this.articles.put(dressGreenArticle.getId(), dressGreenArticle);
        this.articles.put(dressRedArticle.getId(), dressRedArticle);
        this.articles.put(sweetArticle.getId(), sweetArticle);
        this.articles.put(chocolateArticle.getId(), chocolateArticle);
        this.articles.put(lollipopArticle.getId(), lollipopArticle);
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

    public Optional<Product> getProductById(UUID productId) {
        return Optional.ofNullable(products.get(productId));
    }

}

