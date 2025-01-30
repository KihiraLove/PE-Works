package com.pb8jv3.java1.webshopapp.filemanager.utility;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class FileLocation {
    
    public static final String DATA_DIR_LOCATION = "/data/";
    
    public static final String PRODUCT_DATA_FILE_NAME = "product.txt";
    public static final String WISHLIST_DATA_FILE_NAME = "wishlist.txt";
    public static final String CART_DATA_FILE_NAME = "cart.txt";
    
    public static final String PRODUCT_DATA_FILE_LOCATION = "data/product.txt";
    public static final String WISHLIST_DATA_FILE_LOCATION = "data/wishlist.txt";
    public static final String CART_DATA_FILE_LOCATION = "data/cart.txt";

    private FileLocation() {
    }
    
    public static Path getDataPath() {
	return Paths.get(new File("").getAbsolutePath().concat(DATA_DIR_LOCATION));
    }

    public static Path getProductDataPath() {
	return Paths.get(new File("").getAbsolutePath().concat(PRODUCT_DATA_FILE_LOCATION));
    }
    
    public static Path getWishlistDataPath() {
	return Paths.get(new File("").getAbsolutePath().concat(WISHLIST_DATA_FILE_LOCATION));
    }
    
    public static Path getCartDataPath() {
	return Paths.get(new File("").getAbsolutePath().concat(CART_DATA_FILE_LOCATION));
    }
}
