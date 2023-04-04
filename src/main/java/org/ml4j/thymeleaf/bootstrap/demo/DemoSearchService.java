package org.ml4j.thymeleaf.bootstrap.demo;

import org.ml4j.thymeleaf.bootstrap.model.Item;
import org.ml4j.thymeleaf.bootstrap.service.impl.ItemSearchService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class DemoSearchService extends ItemSearchService {

    public DemoSearchService() {
        super(Arrays.asList("A: You can search for text",
                        "B: Using the first letters of each word",
                        "C: For example - typing the letters 'fe' or 'ttl' will return this sentence")
                .stream().map(s -> new Item(s)).collect(Collectors.toList()));
    }
}
