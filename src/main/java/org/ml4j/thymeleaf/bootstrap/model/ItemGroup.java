package org.ml4j.thymeleaf.bootstrap.model;

import java.util.List;

public class ItemGroup {
    private String groupName;
    public List<Item> items;

    public ItemGroup(String groupName, List<Item> items) {
        this.items = items;
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Item> getItems() {
        return items;
    }
}
