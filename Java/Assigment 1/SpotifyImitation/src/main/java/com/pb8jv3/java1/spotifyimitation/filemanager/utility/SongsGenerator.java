package com.pb8jv3.java1.spotifyimitation.filemanager.utility;

import com.pb8jv3.java1.spotifyimitation.datamanager.data.Song;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class SongsGenerator {
    private SongsGenerator(){
    }
    
    public static List<Song> generateSongs() {
	System.out.println("Generating random song library");
	List<Song> songs = new ArrayList<>();
	songs.add(new Song("Perfect", "Ed Sheeran", "Pop", 263, "Perfect Duett"));
	songs.add(new Song("London Boy", "Taylor Swift", "Pop", 190, "Lover"));
	songs.add(new Song("I Forgot That You Existed", "Taylor Swift", "Pop", 171, "Lover"));
	songs.add(new Song("Cruel Summer", "Taylor Swift", "Pop", 178, "Lover"));
	songs.add(new Song("Lover", "Taylor Swift", "Pop", 231, "Lover"));
	songs.add(new Song("The Man", "Taylor Swift", "Pop", 190, "Lover"));
	songs.add(new Song("The Archer", "Taylor Swift", "Pop", 221, "Lover"));
	songs.add(new Song("I Think He Knows", "Taylor Swift", "Pop", 173, "Lover"));
	songs.add(new Song("Paper Rings", "Taylor Swift", "Pop", 222, "Lover"));
	songs.add(new Song("Cornelia Street", "Taylor Swift", "Pop", 287, "Lover"));
	songs.add(new Song("False God", "Taylor Swift", "Pop", 200, "Lover"));
	songs.add(new Song("On Sight", "Kanye West", "Rap", 156, "Yeezus"));
	songs.add(new Song("Black Skinhead", "Kanye West", "Rap", 188, "Yeezus"));
	songs.add(new Song("I Am A God", "Kanye West", "Rap", 231, "Yeezus"));
	songs.add(new Song("New Slaves", "Kanye West", "Rap", 256, "Yeezus"));
	songs.add(new Song("Blood on the Leaves", "Kanye West", "Rap", 360, "Yeezus"));
	songs.add(new Song("Guilt Trip", "Kanye West", "Rap", 243, "Yeezus"));
	songs.add(new Song("Send It Up", "Kanye West", "Rap", 178, "Yeezus"));
	songs.add(new Song("Bound 2", "Kanye West", "Rap", 229, "Yeezus"));
	songs.add(new Song("Eye of the Tiger", "Survivor", "Rock", 245, "Eye of the Tiger"));
	songs.add(new Song("The Search", "NF", "Rap", 291, "The Search"));
	songs.add(new Song("Believer", "Imagine Dragons", "Pop", 204, "Evolve"));
	songs.add(new Song("Thunder", "Imagine Dragons", "Pop", 187, "Evolve"));
	songs.add(new Song("Take Me Home, Country Roads", "John Denver", "Country", 207, "Poems, Prayers and Promises"));
	songs.add(new Song("Shut Up and Dance", "WALK THE MOON", "Pop", 199, "TALKING IS HARD"));
	songs.add(new Song("Somebody That I Used To Know", "Gotye", "Pop", 243, "Making Mirrors"));
	songs.add(new Song("Dead Man's Party", "Oingo Boingo", "New wave", 383, "Dead Man's Party"));
	songs.add(new Song("Insanity", "Oingo Boingo", "Alternative rock", 558, "Boingo"));
	songs.add(new Song("Hey!", "Oingo Boingo", "Alternative rock", 543, "Boingo"));
	songs.add(new Song("Mary", "Oingo Boingo", "Alternative rock", 388, "Boingo"));
	songs.add(new Song("I Am the Walrus", "Oingo Boingo", "Alternative rock", 249, "Boingo"));
	Collections.shuffle(songs);
	System.out.println("Random song library generated");
	return songs;
    }
}
