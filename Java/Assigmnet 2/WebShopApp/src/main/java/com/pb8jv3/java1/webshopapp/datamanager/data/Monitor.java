package com.pb8jv3.java1.webshopapp.datamanager.data;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class Monitor {
    String manufacturer;
    String modelNumber;
    DisplayType type;
    String resolution;
    Integer refreshRate;
    DisplaySize size;
    Integer price;
    Integer inStock;

    public Monitor(String manufacturer, String modelNumber, String resolution, DisplaySize size, Integer price, DisplayType type, Integer refreshRate, Integer inStock) {
	this.manufacturer = manufacturer;
	this.modelNumber = modelNumber;
	this.resolution = resolution;
	this.size = size;
	this.price = price;
	this.type = type;
	this.refreshRate = refreshRate;
	this.inStock = inStock;
    }

    public String getManufacturer() {
	return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
	this.manufacturer = manufacturer;
    }

    public String getModelNumber() {
	return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
	this.modelNumber = modelNumber;
    }

    public String getResolution() {
	return resolution;
    }

    public void setResolution(String aspectRatio) {
	this.resolution = aspectRatio;
    }


    public DisplaySize getSize() {
	return size;
    }

    public void setSize(DisplaySize size) {
	this.size = size;
    }

    public Integer getPrice() {
	return price;
    }

    public void setPrice(Integer price) {
	this.price = price;
    }

    public DisplayType getType() {
	return type;
    }

    public void setType(DisplayType type) {
	this.type = type;
    }

    public Integer getRefreshRate() {
	return refreshRate;
    }

    public void setRefreshRate(Integer refreshRate) {
	this.refreshRate = refreshRate;
    }

    public Integer getInStock() {
	return inStock;
    }

    public void setInStock(Integer inStock) {
	this.inStock = inStock;
    }
    
    public String printable(){
	return manufacturer + ";" + modelNumber + ";" + resolution + ";" + size.getWidth() + ";" + size.getHeight() + ";" + price + ";" + type + ";" + refreshRate + ";" + inStock;
    }
    
    public void printMonitorData(){
	System.out.println("Manufacturer: " + manufacturer + "\n" +
			    "Model Number: " + modelNumber + "\n" +
			    "Aspect Ratio: " + resolution + "\n" +
			    "Width: " + size.getWidth() + "\n" +
			    "Height: " + size.getHeight() + "\n" +
			    "Price: " + price + "\n" +
			    "Type: " + type + "\n" +
			    "Refresh Rate: " + refreshRate + "\n" +
			    "Number In stock: " + inStock + "\n" +
			    "--------------------------------------------------"
	);
    }
}

