package com.pb8jv3.java1.spotifyimitation.datamanager;

import com.pb8jv3.java1.spotifyimitation.datamanager.data.Song;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class SongManager {
    private final List<Song> songs;

    public SongManager() {
	songs = new ArrayList<>();
    }

    public SongManager(List<Song> songs) {
	this.songs = songs;
    }
    
    public SongManager addSong(Song song) {
	songs.add(song);
	return this;
    }

    public List<Song> getSongs() {
	return songs;
    }
    
    public void listSongs(){
	songs.forEach(song -> System.out.println(song.getName()));
    }
}
