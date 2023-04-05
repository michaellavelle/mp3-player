# mp3-player

Initial drop-down search framework code an mp3-player application

This template repository caches a list of local track paths with titles from the track_paths_with_titles.txt file in the resources folder.

The paths in this file are absolute paths to tracks on the machine the code is running on.

Currently, for demo purposes, there are a selection of Beatles songs in this list.

Try searching using the first-letter encodings, such as any substring of 'litswd' to find Lucy In The Sky With Diamonds ( eg. lit or tsw )

The mp3s referred to in this demo file do not exist in this project for copyright reasons.

However, replacing the content of this file with your own track names and file locations should allow this mp3 player to actually play the tracks selected.

( This is an initial prototype with functionality to play the track itself, but not to stop it, or any other such player functions)

Without this modification, this player showcases the search by first-letter-track-name-encoding, but will not play the files, and an file not found error will be seen in the console.

To run, after cloning the repository ( assuming Java and Maven are installed locally)

> cd mp3-player
> mvn spring-boot:run
