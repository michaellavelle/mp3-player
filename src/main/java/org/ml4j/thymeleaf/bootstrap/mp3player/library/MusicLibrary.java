package org.ml4j.thymeleaf.bootstrap.mp3player.library;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MusicLibrary {

    private File defaultDirectory;
    private List<Track> allTracks;

    public MusicLibrary(File defaultDirectory) {
        if (defaultDirectory != null && defaultDirectory.exists()) {
            this.defaultDirectory = defaultDirectory;
            List<File> allMp3Files = new ArrayList<>();
            collateAllMp3Files(allMp3Files, defaultDirectory);
            allTracks = allMp3Files.stream().map(f -> new Track(f.getAbsolutePath())).collect(Collectors.toList());
            Collections.sort(allTracks, Comparator.comparing(Track::getLocalFilePath));
        }
    }

    public List<Track> getAllTracks() {
        return allTracks;
    }

    void collateAllMp3Files(List<File> allFiles, File directory) {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                collateAllMp3Files(allFiles, file);
            } else {
                if (file.getName().endsWith(".mp3")) {
                    allFiles.add(file);
                }
            }
        }
    }


}
