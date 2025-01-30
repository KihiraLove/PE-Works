package com.pb8jv3.java1.spotifyimitation.filemanager;

import com.pb8jv3.java1.spotifyimitation.datamanager.SongManager;
import com.pb8jv3.java1.spotifyimitation.filemanager.utility.FileLocation;
import com.pb8jv3.java1.spotifyimitation.datamanager.PlaylistManager;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class WriteToFile {

    private WriteToFile() {
    }
    
    public static void write(SongManager songManager) {
    	try (FileWriter writer = new FileWriter(FileLocation.getDataFileLocation())) {
	    songManager.getSongs().forEach(song -> {
		try {
		    writer.write(song.printable());
		    writer.write(System.lineSeparator());
		} catch (IOException e) {
		    System.out.println("An error occurred: " + e.getMessage());
		}
	    });
	    System.out.println("Songs written to " + FileLocation.getDataFileName());
	    writer.close();
	} catch (IOException e) {
	    System.out.println("An error occurred: " + e.getMessage());
	}
    }
    
    public static void write(PlaylistManager playlistManager) {
	playlistManager.getPlaylists().values().forEach(playlist -> {
	    try (FileWriter writer = new FileWriter(FileLocation.getPlaylistPath().toString() + "/" + playlist.getName() + ".txt")) {
		playlist.getSongs().forEach(song -> {
		    try {
			writer.write(song.printable());
			writer.write(System.lineSeparator());
		    } catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
		    }
		});
		System.out.println("Songs written to " + FileLocation.getPlaylistPath().toString() + "/" + playlist.getName() + ".txt");
		writer.close();
	    } catch (IOException e) {
		System.out.println("An error occurred: " + e.getMessage());
	    }
	});
    }
}
