package hardtask;

import java.util.Objects;

public class MyLinkedList implements MyList {
    private static class Node {
        Object item;
        Node next;
        Node prev;

        Node(Node prev, Object element, Node next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node head;
    private Node tail;
    private int size = 0;

    private Node node(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index < (size >> 1)) {
            Node x = head;
            for (int i = 0; i < index; i++) x = x.next;
            return x;
        } else {
            Node x = tail;
            for (int i = size - 1; i > index; i--) x = x.prev;
            return x;
        }
    }

    @Override
    public void add(Object e) {
        Node l = tail;
        Node newNode = new Node(l, e, null);
        tail = newNode;
        if (l == null) head = newNode;
        else l.next = newNode;
        size++;
    }

    @Override
    public void add(int index, Object element) {
        if (index == size) {
            add(element);
            return;
        }
        Node succ = node(index);
        Node pred = succ.prev;
        Node newNode = new Node(pred, element, succ);
        succ.prev = newNode;
        if (pred == null) head = newNode;
        else pred.next = newNode;
        size++;
    }

    @Override
    public void addAll(Object[] c) {
        addAll(size, c);
    }

    @Override
    public void addAll(int index, Object[] c) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (c == null || c.length == 0) return;
        int i = index;
        for (Object o : c) {
            add(i++, o);
        }
    }

    @Override
    public Object get(int index) {
        return node(index).item;
    }

    @Override
    public Object remove(int index) {
        Node x = node(index);
        Object element = x.item;
        Node next = x.next;
        Node prev = x.prev;

        if (prev == null) head = next;
        else prev.next = next;

        if (next == null) tail = prev;
        else next.prev = prev;

        size--;
        return element;
    }

    @Override
    public void set(int index, Object element) {
        node(index).item = element;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        for (Node x = head; x != null; x = x.next) {
            if (Objects.equals(o, x.item)) return index;
            index++;
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
        int i = 0;
        for (Node x = head; x != null; x = x.next) {
            result[i++] = x.item;
        }
        return result;
    }
}
