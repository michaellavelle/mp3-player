package org.ml4j.thymeleaf.bootstrap.mp3player.selection;

import org.ml4j.thymeleaf.bootstrap.model.Selection;
import org.ml4j.thymeleaf.bootstrap.mp3player.library.Track;
import org.ml4j.thymeleaf.bootstrap.mp3player.library.TrackNameCache;
import org.ml4j.thymeleaf.bootstrap.mp3player.player.MusicPlayer;
import org.ml4j.thymeleaf.bootstrap.service.SelectionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerSelectionListener implements SelectionListener {

    @Autowired
   private MusicPlayer musicPlayer;

    @Autowired
    private TrackNameCache trackNameCache;

    @Override
    public void onSelection(Selection selection) {
        if (selection.getId() != null) {
            String trackName = trackNameCache.getTrackName(selection.getId());
            selection.setLabel(trackName);
            musicPlayer.playTrack(new Track(selection.getId()));
        }
    }
}
