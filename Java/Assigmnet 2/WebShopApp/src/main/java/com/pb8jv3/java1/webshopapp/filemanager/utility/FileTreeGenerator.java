package com.pb8jv3.java1.webshopapp.filemanager.utility;

import com.pb8jv3.java1.webshopapp.filemanager.WriteToFile;
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
            Files.createDirectories(FileLocation.getDataPath());
            System.out.println("Data directory created or already exists");
        } catch (IOException e) {
	    throw new FailedDirectoryCreationException("Failed to create directory! " + e.getMessage());
        }
	
	try {
	    File productFile = new File(FileLocation.PRODUCT_DATA_FILE_LOCATION);
	    if(productFile.createNewFile()){
		System.out.println("File created in data directory: product.txt");
		WriteToFile.write(MonitorsGenerator.generateMonitors(), FileLocation.PRODUCT_DATA_FILE_LOCATION);
	    } else {
		System.out.println("File already exists: product.txt");
	    }
	} catch (IOException e) {
	    throw new FailedFileCreationException("Failed to create file: product.txt "+ e.getMessage());
	}
	
	try {
	    File wishlistFile = new File(FileLocation.WISHLIST_DATA_FILE_LOCATION);
	    if(wishlistFile.createNewFile()){
		System.out.println("File created in data directory: wishlist.txt");
	    } else {
		System.out.println("File already exists: wishlist.txt");
	    }
	} catch (IOException e) {
	    throw new FailedFileCreationException("Failed to create file: wishlist.txt "+ e.getMessage());
	}
	try {
	    File productFile = new File(FileLocation.CART_DATA_FILE_LOCATION);
	    if(productFile.createNewFile()){
		System.out.println("File created in data directory: cart.txt");
	    } else {
		System.out.println("File already exists: cart.txt");
	    }
	} catch (IOException e) {
	    throw new FailedFileCreationException("Failed to create file: cart.txt "+ e.getMessage());
	}
    }
}
