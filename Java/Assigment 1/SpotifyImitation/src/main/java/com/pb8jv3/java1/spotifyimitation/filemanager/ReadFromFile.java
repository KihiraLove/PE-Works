package com.pb8jv3.java1.spotifyimitation.filemanager;

import com.pb8jv3.java1.spotifyimitation.datamanager.data.Playlist;
import com.pb8jv3.java1.spotifyimitation.datamanager.SongManager;
import com.pb8jv3.java1.spotifyimitation.datamanager.data.Song;
import com.pb8jv3.java1.spotifyimitation.filemanager.utility.FileLocation;
import com.pb8jv3.java1.spotifyimitation.datamanager.PlaylistManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class ReadFromFile {
    private ReadFromFile(){
    }
    
    public static SongManager readSongs() throws FileNotFoundException{
	File songsFile = new File(FileLocation.getDataFileLocation());
	List<Song> songs = new ArrayList<>();
	Scanner fileScanner = new Scanner(songsFile);
	while(fileScanner.hasNextLine()) {
	    String line = fileScanner.nextLine();
	    if(!line.isEmpty()){
	        songs.add(ReadFromFile.songFromString(line));
	    }
	}
	return new SongManager(songs);
    }
    
    public static PlaylistManager readPlaylists(){
	Map<String, Playlist> playlists = new HashMap();
	try {
	    File folder = new File(FileLocation.getPlaylistPath().toString());
	    File[] listOfPlaylists = folder.listFiles();

	    for(File playlist : listOfPlaylists) {
		playlists.put(playlist.getName().substring(0, playlist.getName().length()-4), ReadFromFile.playlistFromFile(playlist.getName().substring(0, playlist.getName().length()-4), playlist));
	    }

	    return new PlaylistManager(playlists);
	} catch (NullPointerException e){
	    return null;
	} catch (FileNotFoundException e){
	    System.out.println("Some error occured while reading playlists: " + e.getMessage());
	    return null;
	}
    }
    
    private static Song songFromString(String rawString){
	String [] splitString = rawString.split(";");
	return new Song(splitString[0], splitString[1], splitString[2], Integer.parseInt(splitString[3]), splitString[4]);
    }
    
    private static Playlist playlistFromFile(String name, File playlistFile) throws FileNotFoundException{
	List<Song> songs = new ArrayList<>();
	Scanner fileScanner = new Scanner(playlistFile);
	while(fileScanner.hasNextLine()) {
	    String line = fileScanner.nextLine();
	    if(!line.isEmpty()){
	        songs.add(ReadFromFile.songFromString(line));
	    }
	}
	return new Playlist(name, songs);
    }
}
