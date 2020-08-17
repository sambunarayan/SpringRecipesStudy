package com.apress.springrecipes.bookshop;

public interface BookShop {
	void purchase(String isbn, String username);
	public void increaseStock(String isbn, int stock);
	public int checkStock(String isbn);
}
