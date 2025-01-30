package com.pb8jv3.java1.spotifyimitation.menusystem.submenusystem.playmenusubmenusystem;

import com.pb8jv3.java1.spotifyimitation.datamanager.DataManager;
import com.pb8jv3.java1.spotifyimitation.datamanager.data.Album;
import com.pb8jv3.java1.spotifyimitation.menusystem.MainMenuController;
import com.pb8jv3.java1.spotifyimitation.menusystem.MenuPrinter;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class AlbumSubmenuController {
    private AlbumSubmenuController(){
    }
    
    public static void init(DataManager dataManager){
	Boolean breaker = true;
	while(breaker){
	    MenuPrinter.printPlaylistSubmenu();
	    dataManager.getAlbumManager().listAlbums();
	    String input = MainMenuController.userInput();
	    switch (input){
		case "1":
		    breaker = false;
		    MenuPrinter.separatorLine();
		    break;
		default:
		    Album album = dataManager.getAlbumManager().getAlbum(input);
		    if(album != null){
			MenuPrinter.separatorLine();
			album.playSongs();
		    } else {
			System.out.println("Album doesn't exist");
		    }
		    MenuPrinter.separatorLine();
	    }
	}
    }
}
