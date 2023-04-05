package org.ml4j.thymeleaf.bootstrap.mp3player.library;

public class Playlists {

    public final static String DEFAULT_DIR = "/Users/new/stuff/music";

    public final static Playlist PLAYLIST_1 = new Playlist(
            new Track(DEFAULT_DIR, Albums.MADONNA_CONFESSIONS_TOUR, "Madonna - 11. Erotica (Live) (Live).mp3"),
            new Track(DEFAULT_DIR, Albums.LIAM_GALLAGHER_WALL_OF_GLASS, "Liam Gallagher - 01. Wall of Glass.mp3")
    );
}
