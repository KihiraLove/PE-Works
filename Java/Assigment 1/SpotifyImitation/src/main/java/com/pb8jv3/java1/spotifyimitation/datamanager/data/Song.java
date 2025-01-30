package com.pb8jv3.java1.spotifyimitation.datamanager.data;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class Song {
    private String name;
    private String artist;
    private String style;
    private Integer length;
    private String albumName;

    public Song() {
    }

    public Song(String name, String artist, String style, Integer lenght, String albumName) {
	this.name = name;
	this.artist = artist;
	this.style = style;
	this.length = lenght;
	this.albumName = albumName;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getArtist() {
	return artist;
    }

    public void setArtist(String artist) {
	this.artist = artist;
    }

    public String getStyle() {
	return style;
    }

    public void setStyle(String style) {
	this.style = style;
    }

    public Integer getLength() {
	return length;
    }

    public void setLength(Integer lenght) {
	this.length = lenght;
    }

    public String getAlbumName() {
	return albumName;
    }
    
    public void setAlbumName(String albumName) {
	this.albumName = albumName;
    }
    
    public String printable(){
	return this.getName() + ";" + this.getArtist() + ";" + this.getStyle() + ";" + this.getLength() + ";" + this.getAlbumName();
    }
    
    public void printAllData() {
	System.out.println("Name: " + name + "\t" +
			    "Artist: " + artist + "\t" +
			    "Style: " + style + "\t" +
			    "Album: " + albumName
	);
    }
}
