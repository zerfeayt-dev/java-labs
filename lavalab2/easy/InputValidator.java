package journal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class InputValidator {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    // Прізвище або ім'я: з великої літери, українські або латинські символи, апостроф, дефіс
    public static boolean isValidName(String input) {
        return input != null && input.matches("[А-ЯІЇЄҐA-Z][а-яіїєґa-z'’-]{1,40}");
    }

    // Реальна дата у форматі ДД.ММ.РРРР (не з майбутнього і не старіша за 1950 рік)
    public static boolean isValidBirthDate(String input) {
        if (input == null) return false;
        try {
            LocalDate date = LocalDate.parse(input, DATE_FORMATTER);
            return !date.isAfter(LocalDate.now()) && date.getYear() >= 1950;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Український номер телефону у форматі +380XXXXXXXXX
    public static boolean isValidPhone(String input) {
        return input != null && input.matches("\\+380\\d{9}");
    }

    // Назва вулиці (мінімум 2 символи, літери, цифри, пробіли, крапки, дефіси)
    public static boolean isValidStreet(String input) {
        return input != null && input.matches("[А-ЯІЇЄҐа-яіїєґA-Za-z0-9\\s.'’-]{2,60}");
    }

    // Номер будинку (наприклад: 15, 15А, 12/4)
    public static boolean isValidBuilding(String input) {
        return input != null && input.matches("\\d+[А-ЯІЇЄҐа-яіїєґA-Za-z/-]*");
    }

    // Номер квартири (додатне ціле число)
    public static boolean isValidApartment(String input) {
        return input != null && input.matches("[1-9]\\d*");
    }
}
