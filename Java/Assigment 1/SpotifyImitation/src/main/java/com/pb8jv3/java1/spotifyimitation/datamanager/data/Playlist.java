package com.pb8jv3.java1.spotifyimitation.datamanager.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class Playlist {
    private String name;
    private final List<Song> songs;

    public Playlist(String name){
	this.name = name;
	songs = new ArrayList<>();
    }

    public Playlist(String name, List<Song> songs){
	this.name = name;
	this.songs = songs;
    }

    public String getName() {
	return name;
    }
    
    public void setName(String name) {
	this.name = name;
    }

    public List<Song> getSongs() {
	return songs;
    }
    
    public void addSong(Song song){
	songs.add(song);
    }
    
    public void removeSong(Song song){
	songs.stream().filter(songInList -> (songInList.equals(song))).forEachOrdered(songInList -> {songs.remove(songInList);});
    }
    
    public void listSongs(){
	if(!songs.isEmpty())
	    songs.forEach(song -> {System.out.println(song.getName());});
	else
	    System.out.println("Playlist is empty, consider addig songs to it from the data editor menu");
    }
    
    public void playSongs(){
	if(!songs.isEmpty())
	    songs.forEach(song -> {System.out.println("Now playing: " + song.getName());});
	else
	    System.out.println("Playlist is empty, consider addig songs to it from the data editor menu");
    }
}
