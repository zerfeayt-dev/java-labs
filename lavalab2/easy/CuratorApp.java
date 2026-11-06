package journal;

import journal.model.Address;
import journal.model.JournalRecord;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class CuratorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        List<JournalRecord> journal = new ArrayList<>();

        while (true) {
            System.out.println("\n=== ЖУРНАЛ КУРАТОРА ===");
            System.out.println("1 - Додати новий запис");
            System.out.println("2 - Показати всі записи");
            System.out.println("0 - Вихід");
            System.out.print("Оберіть дію: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    JournalRecord record = createRecordFromConsole(scanner);
                    journal.add(record);
                    System.out.println(">> Запис успішно перевірено та додано до журналу!");
                    break;
                case "2":
                    printJournal(journal);
                    break;
                case "0":
                    System.out.println("Роботу завершено.");
                    return;
                default:
                    System.out.println("Невідома команда. Введіть 1, 2 або 0.");
            }
        }
    }

    private static JournalRecord createRecordFromConsole(Scanner scanner) {
        System.out.println("\n--- Введення даних студента ---");

        // Кожне правильно введене поле зберігається у змінній; при помилці просимо повторити лише поточне поле
        String lastName = readValidField(scanner,
                "Прізвище (з великої літери, напр. Коваленко): ",
                InputValidator::isValidName);

        String firstName = readValidField(scanner,
                "Ім'я (з великої літери, напр. Олена): ",
                InputValidator::isValidName);

        String birthDate = readValidField(scanner,
                "Дата народження (ДД.ММ.РРРР, напр. 14.05.2006): ",
                InputValidator::isValidBirthDate);

        String phone = readValidField(scanner,
                "Телефон (+380XXXXXXXXX, напр. +380501234567): ",
                InputValidator::isValidPhone);

        String street = readValidField(scanner,
                "Вулиця (напр. Політехнічна): ",
                InputValidator::isValidStreet);

        String building = readValidField(scanner,
                "Будинок (напр. 14 або 14Б): ",
                InputValidator::isValidBuilding);

        String apartment = readValidField(scanner,
                "Квартира (число, напр. 42): ",
                InputValidator::isValidApartment);

        // Коли всі поля гарантовано валідні — передаємо їх у класи моделі
        Address address = new Address(street, building, apartment);
        return new JournalRecord(lastName, firstName, birthDate, phone, address);
    }

    private static String readValidField(Scanner scanner, String prompt, Predicate<String> validator) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (validator.test(input)) {
                return input;
            }
            System.out.println("[Помилка формату] Попередні поля збережено. Введіть це поле ще раз.");
        }
    }

    private static void printJournal(List<JournalRecord> journal) {
        System.out.println("\n--- Усі записи в журналі ---");
        if (journal.isEmpty()) {
            System.out.println("Журнал наразі порожній.");
            return;
        }
        for (int i = 0; i < journal.size(); i++) {
            System.out.println((i + 1) + ". " + journal.get(i));
        }
    }
}
