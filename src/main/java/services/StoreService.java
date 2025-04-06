package services;

import models.Cart;
import models.CartItem;
import models.Product;

import java.util.List;

public class StoreService {
    private final List<Product> catalog;
    private final Cart cart;

    public StoreService(List<Product> catalog) {
        this.catalog = catalog;
        this.cart = new Cart();
    }

    public void showCatalog() {
        for (Product p : catalog) {
            System.out.println(p.getName() + " - " + p.getPrice() + " руб.");
        }
    }

    public void addProductToCart(String name, int quantity) {
        for (Product p : catalog) {
            if (p.getName().equalsIgnoreCase(name)) {
                cart.addItem(p, quantity);
                System.out.println("Добавлено: " + name + " x" + quantity);
                return;
            }
        }
        System.out.println("Товар не найден: " + name);
    }

    public void applyDiscount(double percent) {
        cart.applyDiscount(percent);
    }

    public void printCart() {
        for (CartItem item : cart.getItems()) {
            System.out.println(item.getProduct().getName() + " x" + item.getQuantity() + " = " + item.getTotalPrice());
        }
        System.out.println("Итого со скидкой: " + cart.calculateTotal());
    }
}