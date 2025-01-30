package com.pb8jv3.java1.webshopapp.datamanager;

import com.pb8jv3.java1.webshopapp.filemanager.ReadFromFile;
import com.pb8jv3.java1.webshopapp.filemanager.utility.FileLocation;
import java.io.FileNotFoundException;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class WishlistManager extends Manager{
    
    public WishlistManager() throws FileNotFoundException {
	super(ReadFromFile.readProducts(FileLocation.WISHLIST_DATA_FILE_LOCATION));
    }
}
