package services;

import enums.Commands;
import enums.PromoCodes;
import models.Cart;
import models.CartItem;
import models.Product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StoreService {
    private final List<Product> catalog;
    private final Cart cart;
    private final Set<PromoCodes> usedPromoCodes;

    public StoreService(List<Product> catalog) {
        this.catalog = catalog;
        this.cart = new Cart();
        this.usedPromoCodes = new HashSet<>();
    }

    public void showCatalog() {
        for (Product p : catalog) {
            System.out.println(p.name() + " - " + p.price() + " руб.");
        }
    }

    public void addProductToCart(String name, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        for (Product p : catalog) {
            if (p.name().equalsIgnoreCase(name)) {
                List<CartItem> items = cart.getItems();
                for (CartItem item : items) {
                    if (item.getProduct().name().equalsIgnoreCase(name)) {
                        item.setQuantity(item.getQuantity() + quantity);
                        return;
                    }
                }
                cart.addItem(p, quantity);
                System.out.println("Добавлено: " + name + " x" + quantity);
                return;
            }
        }
        System.out.println("Товар не найден: " + name);
    }

    public void applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100.");
        }
        cart.setDiscount(percent);
    }

    public void printCart() {
        for (CartItem item : cart.getItems()) {
            System.out.println(item.getProduct().name() + " x" + item.getQuantity() + " = " + item.getTotalPrice());
        }
        System.out.println("Итого со скидкой: " + calculateTotal());
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem item : cart.getItems()) {
            total += item.getTotalPrice();
        }
        double discountAmount = total * cart.getDiscountPercent() / 100;
        return total - discountAmount;
    }

    public void applyPromoCode(String promoCode) {
        PromoCodes code = PromoCodes.valueOf(promoCode);
        if (usedPromoCodes.contains(code)) {
            throw new IllegalArgumentException("Promo code " + promoCode + " is already used.");
        }
        try {
            usedPromoCodes.add(code);
            System.out.println("Промокод применен! Ваша скидка: " + code.getDiscount() + "%");
            applyDiscount(code.getDiscount());
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Promo code does not exist.");
        }
    }
}