package org.ml4j.thymeleaf.bootstrap.mp3player.library;

import java.util.Arrays;
import java.util.List;

public class Playlist {
    private List<Track> tracks;

    public Playlist(Track...track) {
        this(Arrays.asList(track));
    }

    public Playlist(List<Track> tracks) {
        this.tracks =tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
