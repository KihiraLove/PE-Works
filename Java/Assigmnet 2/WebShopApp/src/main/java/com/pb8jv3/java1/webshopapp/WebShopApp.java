package com.pb8jv3.java1.webshopapp;

import com.pb8jv3.java1.webshopapp.datamanager.DataManager;
import com.pb8jv3.java1.webshopapp.filemanager.utility.FailedDirectoryCreationException;
import com.pb8jv3.java1.webshopapp.filemanager.utility.FailedFileCreationException;
import com.pb8jv3.java1.webshopapp.filemanager.utility.FileTreeGenerator;
import com.pb8jv3.java1.webshopapp.menumanager.MenuManager;
import java.io.FileNotFoundException;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class WebShopApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	try{
	    FileTreeGenerator.init();
	    
	    DataManager dataManager = new DataManager();
	    
	    MenuManager menuManager = new MenuManager(dataManager);
	} catch (FailedDirectoryCreationException | FailedFileCreationException | FileNotFoundException ex) {
	    System.out.println(ex);
	}
    }
}
