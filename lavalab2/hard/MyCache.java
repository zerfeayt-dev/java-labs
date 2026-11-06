package hardtask;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

public class MyCache<K, V> {
    private static class CacheEntry<V> {
        final V value;
        final long expireAt;

        CacheEntry(V value, long ttlMillis) {
            this.value = value;
            this.expireAt = System.currentTimeMillis() + ttlMillis;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expireAt;
        }
    }

    private final int maxCapacity;
    private final long defaultTtlMillis;
    private final Map<K, CacheEntry<V>> storage = new HashMap<>();
    private final LinkedList<K> accessOrder = new LinkedList<>();

    public MyCache(int maxCapacity, long defaultTtlMillis) {
        if (maxCapacity <= 0 || defaultTtlMillis <= 0) {
            throw new IllegalArgumentException("Capacity and TTL must be positive");
        }
        this.maxCapacity = maxCapacity;
        this.defaultTtlMillis = defaultTtlMillis;
    }

    public void put(K key, V value) {
        Objects.requireNonNull(key, "Cache key must not be null");
        Objects.requireNonNull(value, "Cache value must not be null");

        cleanUpExpired();

        if (!storage.containsKey(key) && storage.size() >= maxCapacity) {
            K evictedKey = accessOrder.removeFirst();
            storage.remove(evictedKey);
        }

        accessOrder.remove(key);
        accessOrder.addLast(key);
        storage.put(key, new CacheEntry<>(value, defaultTtlMillis));
    }

    public V get(K key) {
        Objects.requireNonNull(key, "Cache key must not be null");

        CacheEntry<V> entry = storage.get(key);
        if (entry == null) return null;

        if (entry.isExpired()) {
            storage.remove(key);
            accessOrder.remove(key);
            return null;
        }

        accessOrder.remove(key);
        accessOrder.addLast(key);
        return entry.value;
    }

    public int size() {
        cleanUpExpired();
        return storage.size();
    }

    private void cleanUpExpired() {
        accessOrder.removeIf(key -> {
            CacheEntry<V> entry = storage.get(key);
            if (entry != null && entry.isExpired()) {
                storage.remove(key);
                return true;
            }
            return false;
        });
    }
}
