package org.ml4j.thymeleaf.bootstrap.service;

import java.util.List;

public interface SearchService<I> {
    List<I> getItems(String searchString);
}
