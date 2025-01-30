package com.pb8jv3.java1.spotifyimitation;

import com.pb8jv3.java1.spotifyimitation.datamanager.DataManager;
import com.pb8jv3.java1.spotifyimitation.filemanager.ReadFromFile;
import com.pb8jv3.java1.spotifyimitation.filemanager.WriteToFile;
import com.pb8jv3.java1.spotifyimitation.filemanager.utility.FailedDirectoryCreationException;
import com.pb8jv3.java1.spotifyimitation.filemanager.utility.FailedFileCreationException;
import com.pb8jv3.java1.spotifyimitation.filemanager.utility.FileTreeGenerator;
import com.pb8jv3.java1.spotifyimitation.menusystem.MainMenuController;
import java.io.FileNotFoundException;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class SpotifyImitationApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	try {
	    /**
	     * Generates directories and files if they are non-existent, fills the data file with songs in random order if generated
	     */
	    FileTreeGenerator.init();
	    
	    /**
	     * Initializes menu system
	     * Loads songs into song database
	     * Generates album data from loaded songs, loads it into album database
	     * Loads playlists into playlist database
	     */
	    MainMenuController menu = new MainMenuController(new DataManager(ReadFromFile.readSongs(), ReadFromFile.readPlaylists()));

	    /**
	     * Initializes the menu system
	     */
	    menu.init();
	    
	    /**
	     * Writes songs data to data file
	     */
	    WriteToFile.write(menu.getDataManager().getSongManager());
	    
	    /**
	     * Writes each playlist into their corresponding file
	     */
	    WriteToFile.write(menu.getDataManager().getPlaylistManager());
	    
	} catch (FailedDirectoryCreationException | FailedFileCreationException | FileNotFoundException ex) {
	    System.out.println(ex);
	}
    }
}