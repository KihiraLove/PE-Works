package com.pb8jv3.java1.webshopapp.datamanager.data;

import java.util.Random;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public enum DisplayType {
    LCD,
    LED,
    OLED;
    
    public static DisplayType getRandomType() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
}
