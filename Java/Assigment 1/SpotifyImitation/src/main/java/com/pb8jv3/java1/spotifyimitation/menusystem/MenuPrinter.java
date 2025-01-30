package com.pb8jv3.java1.spotifyimitation.menusystem;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class MenuPrinter {
    private MenuPrinter(){
    }
    
    public static void printMainMenu(){
	MenuPrinter.separatorLine();
	System.out.println("1. Open play menu" + "\n" +
			    "2. Open data editor menu" + "\n" +
			    "3. Exit"
	);
    }
    
    public static void printPlayMenu(){
	MenuPrinter.separatorLine();
	System.out.println("1. Open play all submenu" + "\n" +
			    "2. Open playlist submenu" + "\n" +
			    "3. Open album submenu" + "\n" +
			    "4. Exit" + "\n"
	);
    }
    
    public static void printPlayAllSubmenu(){
	MenuPrinter.separatorLine();
	System.out.println("1. Play all songs ordered by newest first" + "\n" +
			    "2. Play all songs ordered by oldest first" + "\n" +
			    "3. Play all songs ordered by random" + "\n" +
			    "4. Exit" + "\n"
	);
    }
    
    public static void printPlaylistSubmenu(){
	MenuPrinter.separatorLine();
	System.out.println("Please chose a playlist, type its name to play it" + "\n" +
			    "1. Exit"
	);
    }
    
    public static void printAlbumSubmenu(){
	MenuPrinter.separatorLine();
	System.out.println("Please chose an album, type its name to play it" + "\n" +
			    "1. Exit"
	);
    }
    
    public static void printDataEditorMenu(){
	MenuPrinter.separatorLine();
	System.out.println("1. Open song data editor submenu" + "\n" +
			    "2. Open playlist data editor submenu" + "\n" +
			    "3. Exit"
	);
    }
    
    public static void printSongDataEditorSubmenu(){
	MenuPrinter.separatorLine();
	System.out.println("1. Add new song" + "\n" +
			    "2. Exit"
	);
    }
    
    public static void printPlaylistDataEditorSubmenu(){
	MenuPrinter.separatorLine();
	System.out.println("Please chose a playlist, type its name to enter it" + "\n" +
			    "1. Add a new playlist" + "\n" +
			    "2. Exit"
	);
    }
    
    public static void printPlaylistDataEditorSubmenuWhenNoPlaylist(){
	MenuPrinter.separatorLine();
	System.out.println("There are no playlists. Do you want to create a new playlist?" + "\n" +
			    "1. Yes" + "\n" +
			    "2. No"
	);
    }
    
    public static void separatorLine() {  
	System.out.println("------------------------------------------------------------------------");
    } 
}// copy paste for faster work "" + "\n" +