package com.pb8jv3.java1.webshopapp.filemanager;

import com.pb8jv3.java1.webshopapp.filemanager.utility.FileLocation;
import com.pb8jv3.java1.webshopapp.datamanager.WishlistManager;
import com.pb8jv3.java1.webshopapp.datamanager.ProductManager;
import com.pb8jv3.java1.webshopapp.datamanager.CartManager;
import com.pb8jv3.java1.webshopapp.datamanager.DataManager;
import com.pb8jv3.java1.webshopapp.datamanager.data.Monitor;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class WriteToFile {
    
    private WriteToFile() {
    }
    
    public static void write(DataManager dataManager) {
	WriteToFile.write(dataManager.getProductManager());
	WriteToFile.write(dataManager.getCartManager());
	WriteToFile.write(dataManager.getWishlistManager());
    }
    
    public static void write(ProductManager productManager) {
	WriteToFile.writer(productManager.getProducts(), FileLocation.PRODUCT_DATA_FILE_LOCATION);
    }
    
    public static void write(CartManager cartManager) {
	WriteToFile.writer(cartManager.getProducts(), FileLocation.CART_DATA_FILE_LOCATION);
    }
    
    public static void write(WishlistManager wishlistManager) {
	WriteToFile.writer(wishlistManager.getProducts(), FileLocation.WISHLIST_DATA_FILE_LOCATION);
    }
    
    public static void write(Map<Integer, Monitor> products, String location) {
	WriteToFile.writer(products, location);
    }
    
    private static void writer(Map<Integer, Monitor> monitors, String fileLocation){
        try (FileWriter writer = new FileWriter(fileLocation)) {
	    monitors.entrySet().forEach(entry -> {
		try {
		    writer.write(entry.getKey() + ";" + entry.getValue().printable());
		    writer.write(System.lineSeparator());
		} catch (IOException e) {
		    System.out.println("An error occurred: " + e.getMessage());
		}
		});
	    System.out.println("Product data written to " + fileLocation);
	    writer.close();
	} catch (IOException e) {
	    System.out.println("An error occurred: " + e.getMessage());
	}
    }
}
