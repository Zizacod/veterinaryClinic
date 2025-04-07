package app;

import app.controller.OwnerController;
import app.controller.PetController;
import app.controller.VetController;
import app.controller.VisitController;
import app.domain.Owner;
import app.domain.Pet;
import app.domain.Vet;
import app.exception.ownerExceptions.OwnerSaveException;
import app.exception.vetExceptions.VetSaveException;
import app.service.OwnerService;
import app.service.PetService;
import app.service.VetService;
import app.service.VisitService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Client {

    private static OwnerController ownerController;
    private static PetController petController;
    private static VetController vetController;
    private static VisitController visitController;
    private static Scanner scanner;

    public static void main(String[] args) {


        try {
            // Создаём объекты контроллеров для взаимодействия с приложением
            petController = new PetController();
            ownerController = new OwnerController();
            vetController = new VetController();
            visitController = new VisitController();

            scanner = new Scanner(System.in);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        while (true) {
            System.out.println("Ветеринарная клиника - ГЛАВНОЕ МЕНЮ");
            System.out.println("1 - операции с питомцами");
            System.out.println("2 - операции с владельцами");
            System.out.println("3 - операции с ветеринарами");
            System.out.println("4 - операции с визитами");
            System.out.println("0 - выход");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    petOperations();
                    break;
                case "2":
                    ownerOperations();
                    break;
                case "3":
                    vetOperations();
                    break;
                case "4":
                    visitOperations();
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
                    case "0":
                        return;
                    default:
                        System.out.println("Некорректный ввод!");
                        break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void ownerOperations() {

        while (true) {
            try {
                System.out.println("Выберите желаемую операцию с владельцами:");
                System.out.println("1 - Сохранить владельца");
                System.out.println("2 - Показать всех владельцев");
                System.out.println("3 - Показать владельца по id");
                System.out.println("4 - Изменить данные владельца по id");
                System.out.println("5 - Удалить владельца по id");
                System.out.println("6 - Удалить владельца по имени");
                System.out.println("7 - Восстановить владельца по id");
                System.out.println("8 - Показать количество владельцев зарегистрированных в клинике");
                System.out.println("9 - Показать всех животных владельца по id владельца");
                System.out.println("10 - Добавить нового питомца в список животных владельца");
                System.out.println("11 - Удалить питомца по id из списка животных владельца по id");
                System.out.println("12 - Удалить всех питомцев из списка животных владельца по id");
                System.out.println("0 - Выход");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        System.out.println("Введите имя владельца:");
                        String name = scanner.nextLine();
                        System.out.println("Введите mail владельца:");
                        String mail = scanner.nextLine();
                        System.out.println("Введите телефон владельца:");
                        String phone = scanner.nextLine();
                        ownerController.save(name, mail, phone);
                        break;

                    case "2":
                        ownerController.getAllActiveOwners().forEach(System.out::println);
                        break;

                    case "3":
                        System.out.println("Введите id владельца:");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println(ownerController.getActiveOwnerById(id));
                        break;

                    case "4":
                        System.out.println("Введите id владельца:");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Обновленный mail владельца:");
                        mail = scanner.nextLine();
                        System.out.println("Обновленный phone владельца:");
                        phone = scanner.nextLine();
                        ownerController.update(id, mail, phone);
                        break;

                    case "5":
                        System.out.println("Введите id владельца:");
                        id = Integer.parseInt(scanner.nextLine());
                        ownerController.deleteById(id);
                        break;

                    case "6":
                        System.out.println("Введите имя владельца:");
                        name = scanner.nextLine();
                        ownerController.deleteByName(name);
                        break;

                    case "7":
                        System.out.println("Введите id владельца:");
                        id = Integer.parseInt(scanner.nextLine());
                        ownerController.restoreById(id);
                        break;

                    case "8":
                        System.out.println("Количество владельцев зарегистрированных в клинике - "
                                + ownerController.getActiveOwnersNumber());
                        break;

                    case "9":
                        System.out.println("Количество всех животных владельца - ");
                        id = Integer.parseInt(scanner.nextLine());
                        ownerController.getTotalPetsByOwnerId(id);
                        break;

                    case "10":
                        System.out.println("Введите id владельца:");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Добавьте питомец:");
                        int petId = Integer.parseInt(scanner.nextLine());
                        ownerController.addNewPetToOwner(id, petId);
                        break;

                    case "11":
                        System.out.println("Введите id владельца:");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите id питомца:");
                        petId = Integer.parseInt(scanner.nextLine());
                        ownerController.removePetFromOwnersList(id, petId);
                        break;

                    case "12":
                        System.out.println("Введите id владельца:");
                        id = Integer.parseInt(scanner.nextLine());
                        ownerController.clearOwnersPetList(id);
                        break;

                    case "0":
                        return;

                    default:
                        System.out.println("Некорректный ввод!");
                        break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void vetOperations() {

        while (true) {
            try {
                System.out.println("Выберите желаемую операцию с ветеринарами:");
                System.out.println("1 - Сохранить ветеринара");
                System.out.println("2 - Показать всех ветеринаров");
                System.out.println("3 - Показать ветеринара по id");
                System.out.println("4 - Изменить данные ветеринара по id");
                System.out.println("5 - Удалить ветеринара по id");
                System.out.println("6 - Удалить ветеринара по имени");
                System.out.println("7 - Восстановить ветеринара по id");
                System.out.println("8 - Показать количество ветеринаров работающих в клинике");
                System.out.println("9 - Показать всех ветеринаров раньше работающих в клинике");
                System.out.println("10 - Показать ветеринаров по конкретной должности");
//                System.out.println("12 - Показать работает ли ветеринар в клинике по его id");
//                System.out.println("13 - Показать работает ли ветеринар в клинике по его имени");
                System.out.println("0 - Выход");

                String input = scanner.nextLine();

                switch (input) {

                    case "1":
                        System.out.println("Введите имя ветеринара:");
                        String name = scanner.nextLine();
                        System.out.println("Введите должность ветеринара:");
                        String role = scanner.nextLine();
                        vetController.save(name, role);
                        break;
                    case "2":
                        System.out.println("Все ветеринары: " + vetController.getAllActiveVets());
                        break;
                    case "3":
                        System.out.println("Введите id ветеринара:");
                        int id = Integer.parseInt(scanner.nextLine());
                        vetController.getActiveVetById(id);
                        break;
                    case "4":
                        System.out.println("Введите id ветеринара:");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите новое имя ветеринара");
                        name = scanner.nextLine();
                        System.out.println("Введите новую должность ветеринара");
                        role = scanner.nextLine();
                        vetController.update(id, name, role);
                        break;
                    case "5":
                        System.out.println("Введите id ветеринара:");
                        id = Integer.parseInt(scanner.nextLine());
                        vetController.deleteById(id);
                        break;
                    case "6":
                        System.out.println("Введите имя ветеринара:");
                        name = scanner.nextLine();
                        vetController.deleteByName(name);

                        break;
                    case "7":
                        System.out.println("Введите id ветеринара:");
                        id = Integer.parseInt(scanner.nextLine());
                        vetController.restoreById(id);
                        break;

                    case "8":
                        System.out.println("Общее количество всех ветеринаров - " + vetController.getActiveVetsNumber());
                        break;
                    case "9":
                        System.out.println("Все ветеринары раньше работающие в клинике: " + vetController.getAllInactiveVets());
                        break;
                    case "10":
                        System.out.println("Введите должность ветеринара:");
                        role = scanner.nextLine();
                        System.out.println(vetController.getVetsByRole(role));
                        break;
//                    case "11":
//                        break;
//                    case "12":
//                        break;
//                    case "13":
//                        break;
//                    case "14":
//                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Некорректный ввод!");
                        break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void visitOperations() {

        while (true) {
            try {
                System.out.println("Выберите желаемую операцию с визитами:");
                System.out.println("1 - Добавление визита");
                System.out.println("2 - Показать список всех визитов в базе");
                System.out.println("3 - Показать визит по id");
                System.out.println("4 - Показать медицинскую историю питомца по его id");
                System.out.println("5 - Показать все визиты по id ветеринара");
                System.out.println("6 - Удалить визит по id");
                System.out.println("7 - Восстановить визит по id");
                System.out.println("8 - Удалить все визиты питомца по его id");
//                System.out.println("9 - Добавление визита");
//                System.out.println("10 - Добавление визита");
                System.out.println("0 - Выход");

                String input = scanner.nextLine();

                switch (input) {

                    case "1":
                        System.out.println("Введите id владельца");
                        int id = Integer.parseInt(scanner.nextLine());
                        Owner owner = ownerController.getActiveOwnerById(id);

                        System.out.println("Введите id питомца");
                        id = Integer.parseInt(scanner.nextLine());
                        Pet pet = petController.getActivePetById(id);

                        System.out.println("Введите id ветеринара");
                        id = Integer.parseInt(scanner.nextLine());
                        Vet vet = vetController.getActiveVetById(id);


                        System.out.println("Введите описания симптомов питомца");
                        String description = scanner.nextLine();

                        visitController.save(owner, pet, vet, LocalDateTime.now(), description);
                        break;
                    case "2":
                        System.out.println("Введите id визита");
                        id = Integer.parseInt(scanner.nextLine());
                        visitController.getActiveVisitById(id);
                        break;
                    case "3":
                        System.out.println("Все активные визиты в базе - " + visitController.getAllActiveVisits());
                        break;
                    case "4":
                        System.out.println("Введите id питомца");
                        int petId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Отсортированные визиты по дате - " + visitController.getHistoryByPetId(petId));
                        break;
                    case "5":
                        System.out.println("Введите id ветеринара");
                        int vetId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Все визиты ветеринара - " + visitController.getVisitsByVetId(vetId));
                        break;
                    case "6":
                        System.out.println("Введите id визита");
                        id = Integer.parseInt(scanner.nextLine());
                        visitController.deleteById(id);
                        break;
                    case "7":
                        break;
                    case "8":
                        break;
                    case "9":
                        break;
                    case "10":
                        break;
                    case "11":
                        break;
                    case "12":
                        break;
                    case "13":
                        break;
                    case "14":
                        break;
                    case "0":
                        return;
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
