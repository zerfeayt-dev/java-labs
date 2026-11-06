package hardtask;

import java.util.Objects;
import java.util.RandomAccess;

public class MyArrayList implements MyList, RandomAccess {
    private Object[] elements = new Object[10];
    private int size = 0;

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = Math.max(elements.length * 2, minCapacity);
            Object[] newArr = new Object[newCapacity];
            System.arraycopy(elements, 0, newArr, 0, size);
            elements = newArr;
        }
    }

    private void checkIndex(int index, boolean allowEnd) {
        int max = allowEnd ? size : size - 1;
        if (index < 0 || index > max) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void add(Object e) {
        ensureCapacity(size + 1);
        elements[size++] = e;
    }

    @Override
    public void add(int index, Object element) {
        checkIndex(index, true);
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public void addAll(Object[] c) {
        addAll(size, c);
    }

    @Override
    public void addAll(int index, Object[] c) {
        checkIndex(index, true);
        if (c == null || c.length == 0) return;
        ensureCapacity(size + c.length);
        System.arraycopy(elements, index, elements, index + c.length, size - index);
        System.arraycopy(c, 0, elements, index, c.length);
        size += c.length;
    }

    @Override
    public Object get(int index) {
        checkIndex(index, false);
        return elements[index];
    }

    @Override
    public Object remove(int index) {
        checkIndex(index, false);
        Object oldValue = elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return oldValue;
    }

    @Override
    public void set(int index, Object element) {
        checkIndex(index, false);
        elements[index] = element;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, elements[i])) return i;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(elements, 0, result, 0, size);
        return result;
    }
}
