package com.pb8jv3.java1.spotifyimitation.menusystem.submenusystem;

import com.pb8jv3.java1.spotifyimitation.datamanager.DataManager;
import com.pb8jv3.java1.spotifyimitation.menusystem.MainMenuController;
import com.pb8jv3.java1.spotifyimitation.menusystem.MenuPrinter;
import com.pb8jv3.java1.spotifyimitation.menusystem.submenusystem.playmenusubmenusystem.AlbumSubmenuController;
import com.pb8jv3.java1.spotifyimitation.menusystem.submenusystem.playmenusubmenusystem.PlayAllSubmenuController;
import com.pb8jv3.java1.spotifyimitation.menusystem.submenusystem.playmenusubmenusystem.PlaylistSubmenuController;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class PlayMenuController {

    private PlayMenuController() {
    }
    
    public static void init(DataManager dataManager){
	Boolean breaker = true;
	while(breaker){
	    MenuPrinter.printPlayMenu();
	    switch (MainMenuController.userInput()){
	        case "1":
		    PlayAllSubmenuController.init(dataManager);
		    break;
		case "2":
		    PlaylistSubmenuController.init(dataManager);
		    break;
		case "3":
		    AlbumSubmenuController.init(dataManager);
		    break;
		case "4":
		    breaker = false;
		    MenuPrinter.separatorLine();
		    break;
		default:
		    MenuPrinter.separatorLine();
		    System.out.println("Invalid input");
	    }
	}
    }
}
