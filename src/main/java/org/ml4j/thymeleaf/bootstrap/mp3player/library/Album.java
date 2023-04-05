package org.ml4j.thymeleaf.bootstrap.mp3player.library;

public class Album {

    private String localFilePath;

    public Album(String localFilePath) {
        this.localFilePath = localFilePath;
    }

    public Album(String artistDirectory, String albumDirectory) {
        this.localFilePath = artistDirectory + "/" + albumDirectory;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }
}
