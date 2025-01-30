package com.pb8jv3.java1.webshopapp.filemanager.utility;

import com.pb8jv3.java1.webshopapp.datamanager.data.DisplayType;
import com.pb8jv3.java1.webshopapp.datamanager.data.DisplaySize;
import com.pb8jv3.java1.webshopapp.datamanager.data.Monitor;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class MonitorsGenerator {
    
    private MonitorsGenerator() {
    }
    
    public static Map<Integer, Monitor> generateMonitors(){
	Map<Integer, Monitor> monitors = new LinkedHashMap<>();

	for(int i = 0; i < 100; i++){
	    monitors.put(i, generateMonitor());
	}
	
	System.out.println("Data for 100 monitors generated");
	
	return monitors;
    }
    
    private static Monitor generateMonitor(){
	Random random = new Random();
	
	return new Monitor(DataBank.MANUFACTURERS[random.nextInt(DataBank.MANUFACTURERS.length)],
		    random.ints(48, 123)
			    .filter(ri -> (ri <= 57 || ri >= 65) && (ri <= 90 || ri >= 97))
			    .limit(8)
			    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
			    .toString(),
		    DataBank.ASPECT_RATIOS[random.nextInt(DataBank.ASPECT_RATIOS.length)],
		    new DisplaySize(random.nextInt(40) + 60, random.nextInt(30) + 20),
		    random.nextInt(98000) + 2000,
		    DisplayType.getRandomType(),
		    DataBank.REFRESH_RATES[random.nextInt(DataBank.REFRESH_RATES.length)],
		    random.nextInt(600));
    }
}