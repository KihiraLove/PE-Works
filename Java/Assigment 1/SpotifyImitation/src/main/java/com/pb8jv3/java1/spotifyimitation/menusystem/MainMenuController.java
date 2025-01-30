package com.pb8jv3.java1.spotifyimitation.menusystem;

import com.pb8jv3.java1.spotifyimitation.menusystem.submenusystem.PlayMenuController;
import com.pb8jv3.java1.spotifyimitation.datamanager.DataManager;
import com.pb8jv3.java1.spotifyimitation.menusystem.submenusystem.DataEditorMenuController;
import java.util.Scanner;
/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class MainMenuController {
    
    public DataManager dataManager;

    public MainMenuController(DataManager dataManager) {
	this.dataManager = dataManager;
    }

    public DataManager getDataManager() {
	return dataManager;
    }

    public void init() {
	Boolean breaker = true;
	while(breaker){
	    MenuPrinter.printMainMenu();
	    switch (MainMenuController.userInput()){
		case "1":
		    PlayMenuController.init(dataManager);
		    break;
		case "2":
		    dataManager = DataEditorMenuController.init(dataManager);
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
    }
    
    public static String userInput(){
	System.out.println("Enter your choice: ");
	return new Scanner(System.in).nextLine();
    }
    
    public static String userInputNoChoice(){
	return new Scanner(System.in).nextLine();
    }
}
