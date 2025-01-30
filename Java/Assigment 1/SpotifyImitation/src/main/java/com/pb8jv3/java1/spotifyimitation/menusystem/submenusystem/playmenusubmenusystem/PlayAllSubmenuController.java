package com.pb8jv3.java1.spotifyimitation.menusystem.submenusystem.playmenusubmenusystem;

import com.pb8jv3.java1.spotifyimitation.datamanager.DataManager;
import com.pb8jv3.java1.spotifyimitation.datamanager.data.Song;
import com.pb8jv3.java1.spotifyimitation.menusystem.MainMenuController;
import com.pb8jv3.java1.spotifyimitation.menusystem.MenuPrinter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class PlayAllSubmenuController {

    private PlayAllSubmenuController() {
    }
    
    public static void init(DataManager dataManager){
	Boolean breaker = true;
	while(breaker){
	    MenuPrinter.printPlayAllSubmenu();
	    switch (MainMenuController.userInput()){
		case "1":
		    List<Song> newestFirst = new ArrayList<>(dataManager.getSongManager().getSongs());
		    Collections.reverse(newestFirst);
		    newestFirst.forEach(song -> {
			System.out.println("Now playing: " + song.getName());
		    });
		    break;
		case "2":
		    dataManager.getSongManager().getSongs().forEach(song -> {
			System.out.println("Now playing: " + song.getName());
		    });
		    break;
		case "3":
		    List<Song> randomOrder = new ArrayList<>(dataManager.getSongManager().getSongs());
		    Collections.shuffle(randomOrder);
		    randomOrder.forEach(song -> {
			System.out.println("Now playing: " + song.getName());
		    });
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
