package com.pb8jv3.java1.webshopapp.filemanager;

import com.pb8jv3.java1.webshopapp.datamanager.data.DisplaySize;
import com.pb8jv3.java1.webshopapp.datamanager.data.DisplayType;
import com.pb8jv3.java1.webshopapp.datamanager.data.Monitor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class ReadFromFile {
    private ReadFromFile(){
    }
    
    public static Map<Integer, Monitor> readProducts(String location) throws FileNotFoundException{
	File managerFile = new File(location);
	Map<Integer, Monitor> products = new LinkedHashMap<>();
	Scanner fileScanner = new Scanner(managerFile);
	while(fileScanner.hasNextLine()) {
	    String line = fileScanner.nextLine();
	    if(!line.isEmpty()){
		String [] splitString = line.split(";");
	        products.put(Integer.parseInt(splitString[0]),
			new Monitor(splitString[1],
				splitString[2], splitString[3],
				new DisplaySize(Integer.parseInt(splitString[4]),Integer.parseInt(splitString[5])),
				Integer.parseInt(splitString[6]),
				DisplayType.valueOf(splitString[7]),
				Integer.parseInt(splitString[8]),
				Integer.parseInt(splitString[9])));
	    }
	}
	return products;
    }
}
