package com.pb8jv3.java1.spotifyimitation.menusystem.submenusystem;

import com.pb8jv3.java1.spotifyimitation.datamanager.DataManager;
import com.pb8jv3.java1.spotifyimitation.menusystem.MainMenuController;
import com.pb8jv3.java1.spotifyimitation.menusystem.MenuPrinter;
import com.pb8jv3.java1.spotifyimitation.menusystem.submenusystem.dataeditorsubmenusystem.PlaylistDataEditorSubmenuController;
import com.pb8jv3.java1.spotifyimitation.menusystem.submenusystem.dataeditorsubmenusystem.SongDataEditorSubmenuController;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class DataEditorMenuController {

    private DataEditorMenuController() {
    }

    public static DataManager init(DataManager dataManager){
	Boolean breaker = true;
	while(breaker){
	    MenuPrinter.printDataEditorMenu();
	    switch (MainMenuController.userInput()){
	        case "1":
		    dataManager = SongDataEditorSubmenuController.init(dataManager);
		    break;
		case "2":
		    dataManager = PlaylistDataEditorSubmenuController.init(dataManager);
		    break;
		case "3":
		    breaker = false;
		    MenuPrinter.separatorLine();
		    break;
		default:
		    MenuPrinter.separatorLine();
		    System.out.println("Invalid input");
	    }
	}
	return dataManager;
    }
}
