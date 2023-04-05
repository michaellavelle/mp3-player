package org.ml4j.thymeleaf.bootstrap.mp3player.library;

public class Track {

    private String localFilePath;

    public Track(String localFilePath) {
        this.localFilePath = localFilePath;
    }


    public Track(String dir, Album album, String fileName) {
        this.localFilePath = dir + "/" + album.getLocalFilePath() + "/" + fileName;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }
}
