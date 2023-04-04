package org.ml4j.thymeleaf.bootstrap.service.impl;

import org.ml4j.thymeleaf.bootstrap.model.Item;
import org.ml4j.thymeleaf.bootstrap.model.ItemGroup;
import org.ml4j.thymeleaf.bootstrap.model.ItemGroupComparator;
import org.ml4j.thymeleaf.bootstrap.service.SearchService;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ItemSearchService implements SearchService<Item> {

    private List<ItemGroup> itemGroups;

    private List<Item> items;

    private int itemGroupLimit;

    private int itemLimit;


    public ItemSearchService(List<Item> items)  {
        this.items = items;
        this.itemGroups = getItemGroups();
        this.itemLimit = 100;
        this.itemGroupLimit = 100;
    }

    private ItemGroup getItemsGroupedByFirstLetterEncoding(List<Item> allItems, String firstLetterEncodingInLowerCase) {
        List<Item> groupItems = allItems.stream()
                .filter(i -> getFirstLetterEncodingInLowerCase(i.getName())
                        .equals(firstLetterEncodingInLowerCase)).collect(Collectors.toList());
        Collections.sort(groupItems, Comparator.comparing(Item::getName));
        return new ItemGroup(firstLetterEncodingInLowerCase, groupItems);
    }

    public List<ItemGroup> getItemGroups() {
        List<ItemGroup> allItemGroups = new ArrayList<>();
        // Add items grouped by distinct track name
        // Add items grouped by distinct first-letter-encoding name
        allItemGroups.addAll(getDistinctFirstLetterEncodedItemNamesInLowerCase()
                .stream().map( name -> getItemsGroupedByFirstLetterEncoding(getItems(), name)).collect(Collectors.toList()));
        return allItemGroups;
    }

    private List<String> getItemNames() {
        return getItems().stream().map(i -> i.getName()).distinct().collect(Collectors.toList());
    }

    private List<Item>  getItems() {
       return items;

    }

    private List<String> getDistinctFirstLetterEncodedItemNamesInLowerCase() {
        return getItemNames().stream().map(n -> getFirstLetterEncodingInLowerCase(n)).distinct().collect(Collectors.toList());
    }

    @Override
    public List<Item> getItems(String query) {
        List<Item> items = new ArrayList<>();
        items.addAll(getItemGroups(query).stream().flatMap(g -> g.getItems().stream()).collect(Collectors.toList()));
        List<Item> individualItems = new ArrayList<>();
        if (StringUtils.isEmpty(query)) {
            individualItems.addAll(getItems().stream().limit(itemLimit).collect(Collectors.toList()));
        } else {
            individualItems.addAll(getItems().stream().filter(i -> i.getName().toLowerCase().contains(query)).limit(itemLimit).collect(Collectors.toList()));
        }
        Collections.sort(individualItems, Comparator.comparing(Item::getName));
        // Filter for distinct, but ensuring we maintain order
        for (Item item : individualItems) {
            if (!items.contains(item)) {
                items.add(item);
            }
        }
        return items;
    }

    protected List<ItemGroup> getItemGroups(String query) {
        if (StringUtils.isEmpty(query)) {
            List<ItemGroup> returnList = itemGroups.stream()
                    .limit(itemGroupLimit)
                    .collect(Collectors.toList());
            Collections.sort(returnList, new ItemGroupComparator());

            return returnList;
        }
        List<ItemGroup> returnList = itemGroups.stream()
                .filter(group -> group.getGroupName()
                        .toLowerCase()
                        .contains(query.toLowerCase()))
                .limit(itemGroupLimit)
                .collect(Collectors.toList());
        Collections.sort(returnList, new ItemGroupComparator());
        return returnList;
    }

    private String getFirstLetterEncodingInLowerCase(String itemName) {
        String[] parts = itemName.split(" ");
        StringBuilder encoding = new StringBuilder();
        for (String part : parts) {
            if (part.length() >= 1) {
                encoding.append(part.substring(0, 1));
            }
        }
        return encoding.toString().toLowerCase();
    }


}
