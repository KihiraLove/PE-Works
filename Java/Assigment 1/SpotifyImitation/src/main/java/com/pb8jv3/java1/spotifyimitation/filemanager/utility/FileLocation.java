package com.pb8jv3.java1.spotifyimitation.filemanager.utility;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class FileLocation {
    private static final String DATA_DIR_LOCATION = "/data";
    private static final String PLAYLIST_DIR_LOCATION = "/data/playlists";
    private static final String DATA_FILE_LOCATION = "data/songs.txt";
    private static final String DATA_FILE_NAME = "songs.txt";

    private FileLocation() {
    }
    
    public static String getDataDirLocation() {
	return DATA_DIR_LOCATION;
    }

    public static String getPlaylistDirLocation() {
	return PLAYLIST_DIR_LOCATION;
    }

    public static String getDataFileLocation() {
	return DATA_FILE_LOCATION;
    }
    
    public static String getDataFileName(){
	return DATA_FILE_NAME;
    }
    
    public static Path getPlaylistPath() {
	return Paths.get(new File("").getAbsolutePath().concat(PLAYLIST_DIR_LOCATION));
    }

    public static Path getDataPath() {
	return Paths.get(new File("").getAbsolutePath().concat(DATA_DIR_LOCATION));
    }
    
    
}
