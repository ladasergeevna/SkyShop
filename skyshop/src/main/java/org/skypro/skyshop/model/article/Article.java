package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private String name;
    private String content;
    private final UUID id;

    public Article(String name, String content, UUID id) {
        this.name = name;
        this.content = content;
        this.id = id;
    }

    @Override
    public String toString() {
        return name + ": " + content;
    }

    public String getSearchTerm() {
        return toString();
    }

    @JsonIgnore
    public String getSearchContentType() {
        return "ARTICLE";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(name, article.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public UUID getId() {
        return id;
    }
}