package org.ml4j.thymeleaf.bootstrap.mp3player.player;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.ml4j.thymeleaf.bootstrap.mp3player.library.Album;
import org.ml4j.thymeleaf.bootstrap.mp3player.library.Playlist;
import org.ml4j.thymeleaf.bootstrap.mp3player.library.Track;
import org.springframework.scheduling.annotation.Async;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MusicPlayer {

    private MediaPlayer currentMediaPlayer;
    private List<Track> queue;
    private boolean playingFromQueue;

    private String musicDir;

    public MusicPlayer(String musicDir) {
        this.queue = new ArrayList<>();
        this.musicDir = musicDir;
    }

    @Async
    public void playTrack(Track track) {
        try {
            playingFromQueue = false;
            playInternal(track);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    public void playNextTrackInQueue() {
        if (playingFromQueue) {
            if (!queue.isEmpty()) {
                queue.remove(0);
            }
        }
        if (!queue.isEmpty()) {
            playingFromQueue = true;
            try {
                playInternal(queue.get(0));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addTrackToQueue(Track track) {
        this.queue.add(track);
        printPlayQueue(queue);
    }

    public List<Track> getQueue() {
        return queue;
    }

    @Async
    public void playTracks(List<Track> tracksToPlay) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        printPlayQueue(tracksToPlay);
        System.out.println("\n");
        for (Track track : tracksToPlay) {
            playInternal(track);
        }
    }

    private void printPlayQueue(List<Track> tracksToPlay) {
        System.out.println("Play Queue:\n");
        for (Track track : tracksToPlay) {
            System.out.println(track.getLocalFilePath());
        }
    }

    @Async
    public void playQueue() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        printPlayQueue(queue);
        System.out.println("\n");
        for (Track track : queue) {
            playInternal(track);
        }
    }

    private void playInternal(Track track) throws MalformedURLException {
        File file = new File(track.getLocalFilePath());
        final Media media = new Media(file.toURI().toURL().toString());
        List<Boolean> finished = new ArrayList<>();
        stopCurrentTrack();
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        currentMediaPlayer = mediaPlayer;
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {finished.add(true); playNextTrackInQueue(); });
    }

    private void stopCurrentTrack() {
        if (currentMediaPlayer != null) {
            currentMediaPlayer.dispose();
            currentMediaPlayer = null;
        }
    }

    @Async
    public void play(Playlist playlist) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playTracks(playlist.getTracks());
    }

    @Async
    public void play(Album album) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {

        File directory = new File(musicDir, album.getLocalFilePath());
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Album does not correspond to a directory");
        }
        List<String> wavNames = Arrays.asList(directory.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".mp3");
            }
        }));
        Collections.sort(wavNames);
        List<Track> tracks = new ArrayList<>();
        for (String trackName : wavNames) {
            tracks.add(new Track(musicDir, album, trackName));
        }
        playTracks(tracks);
    }
}
