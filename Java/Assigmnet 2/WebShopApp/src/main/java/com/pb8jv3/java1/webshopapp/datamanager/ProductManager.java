package com.pb8jv3.java1.webshopapp.datamanager;

import com.pb8jv3.java1.webshopapp.filemanager.ReadFromFile;
import com.pb8jv3.java1.webshopapp.filemanager.utility.FileLocation;
import java.io.FileNotFoundException;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class ProductManager extends Manager {

    public ProductManager() throws FileNotFoundException {
	super(ReadFromFile.readProducts(FileLocation.PRODUCT_DATA_FILE_LOCATION));
    }
}
