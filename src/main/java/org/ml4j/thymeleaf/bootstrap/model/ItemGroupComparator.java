package org.ml4j.thymeleaf.bootstrap.model;

import java.util.Comparator;

public class ItemGroupComparator implements Comparator<ItemGroup> {
    @Override
    public int compare(ItemGroup o1, ItemGroup o2) {
        int compareSize = Integer.valueOf(o2.getItems().size()).compareTo(o1.getItems().size());
        if (compareSize == 0) {
            return o1.getGroupName().compareTo(o2.getGroupName());
        } else {
            return compareSize;
        }
    }
}
