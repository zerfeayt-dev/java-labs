import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть рядок зі словами:");
        String input = scanner.nextLine();

        String[] uniqueWords = findWordsWithUniqueChars(input);

        System.out.println("Слова, що складаються тільки з різних символів:");
        System.out.println(Arrays.toString(uniqueWords));
    }

    public static String[] findWordsWithUniqueChars(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new String[0];
        }

        // Розбиваємо рядок на слова за пробілами та символами "white space"
        String[] words = text.trim().split("\\s+");
        List<String> resultList = new ArrayList<>();

        for (String word : words) {
            if (hasAllUniqueChars(word)) {
                resultList.add(word);
            }
        }

        // Конвертуємо список у масив String[]
        return resultList.toArray(new String[0]);
    }

    private static boolean hasAllUniqueChars(String word) {
        for (int i = 0; i < word.length(); i++) {
            for (int j = i + 1; j < word.length(); j++) {
                if (word.charAt(i) == word.charAt(j)) {
                    return false; // Знайдено повторюваний символ
                }
            }
        }
        return true;
    }
}
