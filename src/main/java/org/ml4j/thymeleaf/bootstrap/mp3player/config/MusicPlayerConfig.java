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

	public final static String DEFAULT_MUSIC_DIR = "/music";
	public final static File DEFAULT_MUSIC_DIR_FILE = new File(DEFAULT_MUSIC_DIR);


	@Bean
	MusicLibrary musicLibrary() {
		return new MusicLibrary(DEFAULT_MUSIC_DIR_FILE);
	}

	@Bean
	MusicPlayer musicPlayer() {
		return new MusicPlayer(DEFAULT_MUSIC_DIR);
	}

	@Bean
	TrackNameCache trackNameCache() throws IOException {
		return new TrackNameCache();
	}

	@Bean
	ItemSearchService itemSearchService() throws IOException {
		return new ItemSearchService(trackNameCache().getItems());
	}

}
