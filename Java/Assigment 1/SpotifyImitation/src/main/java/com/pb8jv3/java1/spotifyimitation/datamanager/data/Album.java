package com.pb8jv3.java1.spotifyimitation.datamanager.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class Album {
    private final String name;
    private final List<Song> songs;

    public Album(String name, Song song) {
	this.name = name;
	songs = new ArrayList<>();
	songs.add(song);
    }
    
    public Album addSong(Song song) {
	songs.add(song);
	return this;
    }

    public void listSongs() {
	songs.forEach(song -> {System.out.println(song.getName());});
    }
    
    public void playSongs() {
	songs.forEach(song -> {System.out.println("Now playing: " + song.getName());});
    }
    
    public String getName() {
	return name;
    }

    public List<Song> getSongs() {
	return songs;
    }
}
