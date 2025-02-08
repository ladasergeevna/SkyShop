package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    public String getSearchTerm(); // Отображаемый результат поиска

    public String getSearchContentType(); //Тип контента

    default String getStringRepresentation()// Имя объекта
    {
        return this.getClass().getSimpleName() + "-" + getSearchContentType();
    }
    public UUID getId();

}