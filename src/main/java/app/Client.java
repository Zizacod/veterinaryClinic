package app;

import app.controller.PetController;
import app.domain.Pet;
import app.exception.ownerExceptions.OwnerNotFoundException;
import app.exception.petExceptions.PetNotFoundException;
import app.service.OwnerService;
import app.service.PetService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Client {

    private static PetController petController;
    private static Scanner scanner;

    public static void main(String[] args) {

        try {
            OwnerService ownerService = new OwnerService();
            PetService petService = new PetService();

            ownerService.setPetService(petService);
            petService.setOwnerService(ownerService);


        } catch (Exception e) {
            System.err.println("Ошибка - " + e.getMessage());
        }


        try {
            // Создаём объекты контроллеров для взаимодействия с приложением
            petController = new PetController();
            // ownerController
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        while (true) {
            System.out.println("Ветеринарная клиника - ГЛАВНОЕ МЕНЮ");
            System.out.println("1 - операции с питомцами");
            System.out.println("0 - выход");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    petOperations();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Некорректный ввод!");
                    break;
            }
        }
    }

    private static void petOperations() {
        while (true) {
            try {
                System.out.println("Выберите желаемую операцию с питомцами:");
                System.out.println("1 - Сохранить питомца");
                System.out.println("2 - Показать всех животных");
                System.out.println("3 - Выбрать питомца по id");
                System.out.println("4 - Изменить данные питомца по id");
                System.out.println("5 - Удалить питомца по id");
                System.out.println("6 - Восстановить питомца по id");
                System.out.println("7 - Показать количество питомцев в клинике");
                System.out.println("8 - Показать историю всех визитов конкретного питомца");
                System.out.println("9 - Показать список всех врачей, лечивших данного питомца");
                System.out.println("10 - Удалить все визиты питомца по id питомца");
                System.out.println("0 - Выход");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        System.out.println("Введите имя питомца:");
                        String name = scanner.nextLine();
                        System.out.println("Введите породу питомца:");
                        String breed = scanner.nextLine();
                        System.out.println("Введите цвет питомца:");
                        String color = scanner.nextLine();
                        System.out.println("Введите возраст питомца:");
                        int age = Integer.parseInt(scanner.nextLine());
                        System.out.println(petController.save(name, breed, color, age));
                        break;

                    case "2":
                        petController.getAllActivePets().forEach(System.out::println);
                        break;

                    case "3":
                        System.out.println("Введите id питомца:");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println(petController.getActivePetById(id));
                        break;

                    case "4":
                        System.out.println("Введите id питомца:");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Обновленный возраст питомца:");
                        age = Integer.parseInt(scanner.nextLine());
                        petController.update(id, age);
                        break;

                    case "5":
                        System.out.println("Введите id питомца:");
                        id = Integer.parseInt(scanner.nextLine());
                        petController.deleteById(id);
                        break;

                    case "6":
                        System.out.println("Введите id питомца:");
                        id = Integer.parseInt(scanner.nextLine());
                        petController.restoreById(id);
                        break;

                    case "7":
                        System.out.println("Количество всех животных в клинике - " + petController.getActivePetsNumber());
                        break;

                    case "8":
                        break;
                    case "9":
                        break;
                    case "10":
                        break;
                    case "11":
                        break;
                    default:
                        System.out.println("Некорректный ввод!");
                        break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
