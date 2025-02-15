package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.searchResult.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String keyWord) {
        List<Searchable> allSearchables = storageService.getSearchables();
        return allSearchables.stream()
                .filter(searchable -> searchable.getSearchTerm().contains(keyWord))
                .map(searchable -> new SearchResult(
                        searchable.getId().toString(),
                        searchable.getSearchTerm(),
                        searchable.getSearchContentType())
                )
                .collect(Collectors.toList());
    }

    public List<SearchResult> searchid(UUID id) {
        List<Searchable> allSearchablesid = storageService.getSearchables();
        return allSearchablesid.stream()
                .filter(searchable -> searchable.getId() == id)
                .map(searchable -> new SearchResult(
                        searchable.getId().toString(),
                        searchable.getSearchTerm(),
                        searchable.getSearchContentType())
                )
                .collect(Collectors.toList());
    }
}
