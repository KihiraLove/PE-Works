package com.pb8jv3.java1.spotifyimitation.datamanager;

import com.pb8jv3.java1.spotifyimitation.datamanager.data.Album;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class AlbumManager {
    private Map<String, Album> albums;
    
    public AlbumManager(SongManager songManager) {
	albums = new HashMap();
	songManager.getSongs().forEach(song -> {
	    if(albums.containsKey(song.getAlbumName())){
		albums.replace(song.getAlbumName(), albums.get(song.getAlbumName()).addSong(song));
	    } else {
		albums.put(song.getAlbumName(), new Album(song.getAlbumName(), song));
	    }
	});
    }
    
    public Album getAlbum(String name){
	if(albums.containsKey(name))
	    return albums.get(name);
	else
	    return null;
    }
    
    public void listAlbums() {
	albums.keySet().forEach(albumName -> {System.out.println(albumName);});
    }
}