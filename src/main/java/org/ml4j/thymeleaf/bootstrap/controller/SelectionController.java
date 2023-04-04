package org.ml4j.thymeleaf.bootstrap.controller;

import org.ml4j.thymeleaf.bootstrap.model.Choice;
import org.ml4j.thymeleaf.bootstrap.model.Item;
import org.ml4j.thymeleaf.bootstrap.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("selection")
public class SelectionController {

    @Autowired
    private SearchService<Item> searchService;

    @GetMapping
    public List<Choice> choices(@RequestParam(value = "q", required = false) String query) {
        return searchService.getItems(query).stream().map(this::mapToChoice).distinct().collect(Collectors.toList());
    }

    private Choice mapToChoice(Item item) {
        return Choice.builder()
                        .id(item.getId())
                        .text(item.getName())
                        .slug(item.getId())
                        .build();
    }
}
