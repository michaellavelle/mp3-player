package org.ml4j.thymeleaf.bootstrap.mp3player.config;


import org.ml4j.thymeleaf.bootstrap.mp3player.library.MusicLibrary;
import org.ml4j.thymeleaf.bootstrap.mp3player.library.TrackNameCache;
import org.ml4j.thymeleaf.bootstrap.mp3player.player.MusicPlayer;
import org.ml4j.thymeleaf.bootstrap.service.impl.ItemSearchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.File;
import java.io.IOException;

@Configuration
@EnableAsync
public class MusicPlayerConfig {

	public final static String TRACK_NAME_CACHE_FILE_NAME = "/Users/michael/stuff/code/checkouts/mp3-player/src/main/resources/track_paths_with_titles.txt";

	public final static String DEFAULT_MUSIC_DIR = "/Users/michael/stuff/music";

	@Bean
	MusicLibrary musicLibrary() {
		return new MusicLibrary(new File(DEFAULT_MUSIC_DIR));
	}

	@Bean
	MusicPlayer musicPlayer() {
		return new MusicPlayer(DEFAULT_MUSIC_DIR);
	}

	@Bean
	TrackNameCache trackNameCache() throws IOException {
		return new TrackNameCache(new File(TRACK_NAME_CACHE_FILE_NAME));
	}

	@Bean
	ItemSearchService itemSearchService() throws IOException {
		return new ItemSearchService(trackNameCache().getItems());
	}

}
