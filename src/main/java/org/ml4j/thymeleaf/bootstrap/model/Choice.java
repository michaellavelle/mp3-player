package org.ml4j.thymeleaf.bootstrap.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Choice {

    private String id;
    private String text;
    private String slug;

}
