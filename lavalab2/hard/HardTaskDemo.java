package hardtask;

import java.util.Arrays;

public class HardTaskDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Перевірка MyArrayList та MyLinkedList ---");
        MyList arrayList = new MyArrayList();
        arrayList.add("A");
        arrayList.add("C");
        arrayList.add(1, "B");
        System.out.println("MyArrayList: " + Arrays.toString(arrayList.toArray()));

        MyList linkedList = new MyLinkedList();
        linkedList.addAll(new Object[]{10, 20, 30});
        linkedList.remove(1);
        System.out.println("MyLinkedList: " + Arrays.toString(linkedList.toArray()));

        System.out.println("\n--- Перевірка MyLinkedHashSet (збереження порядку без дублікатів) ---");
        MyLinkedHashSet<String> set = new MyLinkedHashSet<>();
        set.add("Перший");
        set.add("Другий");
        set.add("Перший"); // дублікат ігнорується
        set.add("Третій");
        System.out.println("MyLinkedHashSet: " + Arrays.toString(set.toArray()));

        System.out.println("\n--- Перевірка MyCache (Eviction + Expiry) ---");
        MyCache<String, String> cache = new MyCache<>(2, 1500); // макс. 2 елементи, час життя 1.5 сек
        cache.put("k1", "Значення 1");
        cache.put("k2", "Значення 2");
        cache.put("k3", "Значення 3"); // витіснить k1 (Eviction)
        System.out.println("k1 (має бути витіснено -> null): " + cache.get("k1"));
        System.out.println("k2 (наявне): " + cache.get("k2"));

        Thread.sleep(1600); // чекаємо завершення часу життя (Expiry)
        System.out.println("k2 після 1.6 сек (протерміновано -> null): " + cache.get("k2"));
    }
}  
