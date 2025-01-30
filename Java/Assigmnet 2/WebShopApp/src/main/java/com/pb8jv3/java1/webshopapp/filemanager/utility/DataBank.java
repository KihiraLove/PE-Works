package com.pb8jv3.java1.webshopapp.filemanager.utility;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class DataBank {

    public static final String[] MANUFACTURERS = {"ASUS", "LG", "BenQ", "AOC Monitors", "Dell",
				    "Samsung Engineering", "Acer Inc.",
				    "Sony Corporation", "ViewSonic", "HP",
				    "Eizo", "Philips", "Iiyama", "Apple"};
	
    public static final String[] ASPECT_RATIOS = {"1024x768", "1600x1200", "1280x1024",
				    "2160x1440", "1920x1080", "2560x1080",
				    "5120x1440"};
	
    public static final Integer[] REFRESH_RATES = {60, 75, 144, 165, 240, 100, 120};
    
    private DataBank() {
    }
    
    public static List<String> getManufacturersAsList(){
	return Arrays.asList(MANUFACTURERS);
    }
    
        public static List<String> getAspectRatiosAsList(){
	return Arrays.asList(ASPECT_RATIOS);
    }
	
    public static List<Integer> getRefreshRatesAsList(){
	return Arrays.asList(REFRESH_RATES);
    }
}
