package com.pb8jv3.java1.spotifyimitation.filemanager.utility;

import com.pb8jv3.java1.spotifyimitation.datamanager.SongManager;
import com.pb8jv3.java1.spotifyimitation.filemanager.WriteToFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class FileTreeGenerator {

    private FileTreeGenerator() {
    }
    
    public static void init() throws FailedDirectoryCreationException, FailedFileCreationException{
        try {
            Files.createDirectories(FileLocation.getPlaylistPath());
            System.out.println("Data directory created or already exists");
	    System.out.println("Playlist directory created or already exists");
        } catch (IOException e) {
	    throw new FailedDirectoryCreationException("Failed to create directory! " + e.getMessage());
        }
	
	try {
	    File songsFile = new File(FileLocation.getDataFileLocation());
	    if(songsFile.createNewFile()){
		System.out.println("File created in data directory: songs.txt");
		WriteToFile.write(new SongManager(SongsGenerator.generateSongs()));
	    } else {
		System.out.println("File lready exists: songs.txt");
	    }
	} catch (IOException e) {
	    throw new FailedFileCreationException("Failed to create file: songs.txt "+ e.getMessage());
	}
    }
}
