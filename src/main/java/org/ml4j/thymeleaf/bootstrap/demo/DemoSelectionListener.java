package org.ml4j.thymeleaf.bootstrap.demo;

import org.ml4j.thymeleaf.bootstrap.model.Selection;
import org.ml4j.thymeleaf.bootstrap.service.SelectionListener;
import org.springframework.stereotype.Service;

@Service
public class DemoSelectionListener implements SelectionListener {
    @Override
    public void onSelection(Selection selection) {
       System.out.println("Selection with id:" + selection.getId() + " was selected");
       // Setting the label on the selection so it can be displayed back to the user.
       selection.setLabel(selection.getId());
    }
}
