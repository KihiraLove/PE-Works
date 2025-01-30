package com.pb8jv3.java1.spotifyimitation.menusystem.submenusystem.dataeditorsubmenusystem;

import com.pb8jv3.java1.spotifyimitation.datamanager.DataManager;
import com.pb8jv3.java1.spotifyimitation.datamanager.data.Song;
import com.pb8jv3.java1.spotifyimitation.menusystem.MainMenuController;
import com.pb8jv3.java1.spotifyimitation.menusystem.MenuPrinter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class PlaylistDataEditorSubmenuController {
    private PlaylistDataEditorSubmenuController() {
    }
    
    public static DataManager init(DataManager dataManager){
	Boolean breaker = true;
	while(breaker){
	    String input;
	    if(dataManager.getPlaylistManager().getPlaylists().isEmpty()){
		MenuPrinter.printPlaylistDataEditorSubmenuWhenNoPlaylist();
		input = MainMenuController.userInput();
		switch (input){
		    case "1":
			dataManager = PlaylistDataEditorSubmenuController.createPlaylist(dataManager);
			break;
		    case "2":
			breaker = false;
			MenuPrinter.separatorLine();
			break;
		    default:
			System.out.println("Invalid input");
		}
	    } else {
		MenuPrinter.printPlaylistDataEditorSubmenu();
		dataManager.getPlaylistManager().listPlaylists();
		input = MainMenuController.userInput();
		switch (input){
		    case "1":
			dataManager = PlaylistDataEditorSubmenuController.createPlaylist(dataManager);
			break;
		    case "2":
			breaker = false;
			MenuPrinter.separatorLine();
			break;
		    default:
			if(dataManager.getPlaylistManager().getPlaylists().containsKey(input)){
			    dataManager = PlaylistDataEditorSubmenuController.addSongsToPlaylist(dataManager, input);
			} else {
			    System.out.println("Invalid input");
			}
		}
	    }
	}
	return dataManager;
    }

    private static DataManager createPlaylist(DataManager dataManager) {
	String input;
	do{
	    System.out.println("Enter name of playlist: ");
	    input = MainMenuController.userInputNoChoice();
	    if(!input.equals("") && !dataManager.getPlaylistManager().getPlaylists().containsKey(input)){
		String name = input;
		List<Song> songs = new ArrayList<>();
		Boolean breaker = true;
		while(breaker){
		    dataManager.getSongManager().listSongs();
		    MenuPrinter.separatorLine();
		    System.out.println("Type the name of the song you want to add, type 0 to Exit:");
		    input = MainMenuController.userInput();
		    Boolean songAdded = false;
		    for(Song song :  dataManager.getSongManager().getSongs()){
			if(song.getName().equals(input)){
			    songs.add(song);
			    songAdded = true;
			    break;
			}
		    }
		    if(songAdded){
			System.out.println("Song added");
		    } else {
			System.out.println("Song doesn't exists");
		    }
		    dataManager.setPlaylistManager(dataManager.getPlaylistManager().addPlaylist(name, songs));
		}
	    } else {
		System.out.println("Invalid input");
	    }
	} while(input.equals(""));
	return dataManager;
    }
    
    
    private static DataManager addSongsToPlaylist(DataManager dataManager, String name) {
	String input;
	List<Song> songs = dataManager.getPlaylistManager().getPlaylist(name).getSongs();
	Boolean breaker = true;
	while(breaker){
	    dataManager.getSongManager().listSongs();
	    System.out.println("Type the name of the song you want to add, type 0 to Exit:");
	    input = MainMenuController.userInput();
	    for(Song song :  dataManager.getSongManager().getSongs()){
		if(song.getName().equals(input)){
		    songs.add(song);
		} else if(input.equals("0")){
		    breaker = false;
		} else {
		    System.out.println("Song does not exist");
		}
	    }
	dataManager.setPlaylistManager(dataManager.getPlaylistManager().addPlaylist(name, songs));
	}
	return dataManager;
    }
}
