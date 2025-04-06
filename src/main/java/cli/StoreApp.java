package cli;

import models.Product;
import services.StoreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoreApp {
    public static void main(String[] args) {
        List<Product> catalog = new ArrayList<>();
        catalog.add(new Product("Футболка", 1000));
        catalog.add(new Product("Книга", 500));
        catalog.add(new Product("Кружка", 300));

        StoreService store = new StoreService(catalog);
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("1. Показать каталог");
            System.out.println("2. Добавить товар в корзину");
            System.out.println("3. Применить скидку");
            System.out.println("4. Показать корзину");
            System.out.println("5. Выход");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> store.showCatalog();
                case "2" -> {
                    System.out.print("Введите название товара: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите количество: ");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    store.addProductToCart(name, quantity);
                }
                case "3" -> {
                    System.out.print("Введите процент скидки: ");
                    double percent = Double.parseDouble(scanner.nextLine());
                    store.applyDiscount(percent);
                }
                case "4" -> store.printCart();
                case "5" -> running = false;
                default -> System.out.println("Неверный ввод.");
            }
        }

        System.out.println("Спасибо за покупки!");
    }
}