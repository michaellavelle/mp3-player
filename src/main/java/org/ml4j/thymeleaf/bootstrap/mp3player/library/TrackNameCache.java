package org.ml4j.thymeleaf.bootstrap.mp3player.library;

import org.ml4j.thymeleaf.bootstrap.model.Item;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TrackNameCache {
    private List<Item> items;

    public TrackNameCache(String trackNameCacheFileResourcePath) {
    }

    public TrackNameCache() throws IOException {
        this.items = new ArrayList<>();
        URL trackPathsWithTitlesResource = TrackNameCache.class
                .getClassLoader().getResource("track_paths_with_titles.txt");
        try (FileReader fr = new FileReader(trackPathsWithTitlesResource.getPath())) {
            try (BufferedReader br = new BufferedReader(fr)) {
                String line = br.readLine();
                while (line != null) {
                    int index = line.indexOf(":");
                    String fileName = line.substring(0, index);
                    String title = line.substring(index + 1);
                    if (!this.items.stream().anyMatch(i -> i.getName().toLowerCase().trim().equals(title.trim().toLowerCase()))) {
                        this.items.add(new Item(fileName, title));
                    }
                    line = br.readLine();
                }
            }
        }
    }

    public String getTrackName(String filePath) {
        return items.stream().filter((i -> i.getId().equals(filePath)))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Could not find track name for file path:" + filePath)).getName();
    }

    public List<Item> getItems() {
        return items;
    }
}
