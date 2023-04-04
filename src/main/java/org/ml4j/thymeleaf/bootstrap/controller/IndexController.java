package org.ml4j.thymeleaf.bootstrap.controller;

import org.ml4j.thymeleaf.bootstrap.model.Selection;
import org.ml4j.thymeleaf.bootstrap.service.SelectionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/", "/index" })
public class IndexController {

    @Autowired
    private SelectionListener selectionListener;

    @GetMapping
    public String main(Model model) {
        model.addAttribute("selection", new Selection());
        return "index";
    }

    @PostMapping
    public String save(Selection selection, Model model) {
        selectionListener.onSelection(selection);
        model.addAttribute("selection", selection);
        return "index";
    }
}
