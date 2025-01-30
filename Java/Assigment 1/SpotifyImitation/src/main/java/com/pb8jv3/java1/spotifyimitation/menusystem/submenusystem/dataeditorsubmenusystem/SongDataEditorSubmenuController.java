package com.pb8jv3.java1.spotifyimitation.menusystem.submenusystem.dataeditorsubmenusystem;

import com.pb8jv3.java1.spotifyimitation.datamanager.AlbumManager;
import com.pb8jv3.java1.spotifyimitation.datamanager.DataManager;
import com.pb8jv3.java1.spotifyimitation.datamanager.data.Song;
import com.pb8jv3.java1.spotifyimitation.menusystem.MainMenuController;
import com.pb8jv3.java1.spotifyimitation.menusystem.MenuPrinter;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class SongDataEditorSubmenuController {
    
    private SongDataEditorSubmenuController() {
    }
    
    public static DataManager init(DataManager dataManager){
	Boolean breaker = true;
	while(breaker){
	    MenuPrinter.printSongDataEditorSubmenu();
	    String input = MainMenuController.userInput();
	    switch (input){
		case "1":
		    Song song = new Song();
		    do{
			System.out.println("Enter song name: ");
			input = MainMenuController.userInputNoChoice();
			if(!input.equals("")){
			    song.setName(input);
			} else {
			    System.out.println("Invalid input");
			}
		    } while(input.equals(""));
		    do{
			System.out.println("Enter songa artist: ");
			input = MainMenuController.userInputNoChoice();
			if(!input.equals("")){
			    song.setArtist(input);
			} else {
			    System.out.println("Invalid input");
			}
		    } while(input.equals(""));
		    do{
			System.out.println("Enter songs style: ");
			input = MainMenuController.userInputNoChoice();
			if(!input.equals("")){
			    song.setStyle(input);
			} else {
			    System.out.println("Invalid input");
			}
		    } while(input.equals(""));
		    do{
			System.out.println("Enter songs album name: ");
			input = MainMenuController.userInputNoChoice();
			if(!input.equals("")){
			    song.setAlbumName(input);
			} else {
			    System.out.println("Invalid input");
			}
		    } while(input.equals(""));
		    do{
			System.out.println("Enter songs length: ");
			input = MainMenuController.userInputNoChoice();
			if(!input.equals("")){
			    try{
				song.setLength(Integer.parseInt(input));
			    } catch(NumberFormatException e) {
				System.out.println("Invalid input");
			    }
			} else {
			    System.out.println("Invalid input");
			}
		    } while(input.equals(""));
		    dataManager.setSongManager(dataManager.getSongManager().addSong(song));
		    dataManager.setAlbumManager(new AlbumManager(dataManager.getSongManager()));
		    break;
		case "2":
		    breaker = false;
		    MenuPrinter.separatorLine();
		    break;
		default:
		    System.out.println("Invalid input");
	    }
	}
	return dataManager;
    }
}
