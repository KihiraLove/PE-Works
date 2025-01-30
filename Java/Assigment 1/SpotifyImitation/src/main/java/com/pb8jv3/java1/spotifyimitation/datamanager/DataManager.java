package com.pb8jv3.java1.spotifyimitation.datamanager;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class DataManager {
    SongManager songManager;
    AlbumManager albumManager;
    PlaylistManager playlistManager;

    public DataManager(SongManager songManager, PlaylistManager playlistManager) {
	this.songManager = songManager;
	this.albumManager = new AlbumManager(songManager);
	this.playlistManager = playlistManager;
    }

    public SongManager getSongManager() {
	return songManager;
    }

    public AlbumManager getAlbumManager() {
	return albumManager;
    }

    public PlaylistManager getPlaylistManager() {
	return playlistManager;
    }

    public void setSongManager(SongManager songManager) {
	this.songManager = songManager;
    }

    public void setAlbumManager(AlbumManager albumManager) {
	this.albumManager = albumManager;
    }

    public void setPlaylistManager(PlaylistManager playlistManager) {
	this.playlistManager = playlistManager;
    }
}
