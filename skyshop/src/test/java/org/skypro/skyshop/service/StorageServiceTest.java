package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StorageServiceTest {
    @InjectMocks
    private StorageService storageService;

    // Поиск в случае отсутствия объектов в  StorageService

    @Test
    void testGetSearchablesWhenNoProductsOrArticles() {
        storageService.clearData();
        Collection<Product> products = storageService.getProducts();
        Collection<Article> articles = storageService.getArticles();

        assertEquals(0, products.size());
        assertEquals(0, articles.size());
        assertEquals(Collections.emptyList(), storageService.getSearchables());
    }

    // Поиск, когда есть подходящий объект в StorageService
    @Test
    void testGetExistingSearchable() {
        Product dress = new SimpleProduct("Платье", 3000, UUID.randomUUID());
        List<Searchable> searchables = storageService.getSearchables();

        boolean containsDress = searchables.stream().anyMatch(p -> p.equals(dress));
        assertTrue(containsDress, "Товар " + dress.getName() + " в наличии");
    }

    // Поиск в случае, если объекты в StorageService есть, но нет подходящего
    @Test
    void testGetNonExistingSearchable() {
        Article dressBlackArticle = new Article("Инфо о черном платье", "Платье черное 52 размера", UUID.randomUUID());
        List<Searchable> searchables = storageService.getSearchables();
        boolean containsDressBlackArticle = searchables.stream().anyMatch(a -> a.equals(dressBlackArticle));
        assertFalse(containsDressBlackArticle, "Список не должен содержать статью " + dressBlackArticle.getSearchTerm());
    }

    @Test
    void testGetProductByIdWhenProductDoesNotExist() {
        UUID nonExistentProductId = UUID.randomUUID();
        Optional<Product> product = storageService.getProductById(nonExistentProductId);
        assertEquals(Optional.empty(), product);
    }
}
