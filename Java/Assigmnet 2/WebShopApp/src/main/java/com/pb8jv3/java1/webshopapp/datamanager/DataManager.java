package com.pb8jv3.java1.webshopapp.datamanager;

import java.io.FileNotFoundException;

/**
 *
 * @author Kertesz Domonkos PB8JV3
 */
public class DataManager {
    public CartManager cartManager;
    public WishlistManager wishlistManager;
    public ProductManager productManager;

    public DataManager() throws FileNotFoundException {
	this.cartManager = new CartManager();
	this.wishlistManager = new WishlistManager();
	this.productManager = new ProductManager();
    }

    public CartManager getCartManager() {
	return cartManager;
    }

    public CartManager setCartManager(CartManager cartManager) {
	this.cartManager = cartManager;
	return cartManager;
    }

    public WishlistManager getWishlistManager() {
	return wishlistManager;
    }

    public WishlistManager setWishlistManager(WishlistManager wishlistManager) {
	this.wishlistManager = wishlistManager;
	return wishlistManager;
    }

    public ProductManager getProductManager() {
	return productManager;
    }

    public ProductManager setProductManager(ProductManager productManager) {
	this.productManager = productManager;
	return productManager;
    }
}
