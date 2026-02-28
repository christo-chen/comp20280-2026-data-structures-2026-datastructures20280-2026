package project20280.stacksqueues;

import project20280.interfaces.Deque;

public class ArrayDeque<E> implements Deque<E> {

    private static final int CAPACITY = 100;
    private E[] data;
    private int f = 0;
    private int sz = 0;

    public ArrayDeque() {
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayDeque(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return sz;
    }

    @Override
    public boolean isEmpty() {
        return sz == 0;
    }

    @Override
    public E first() {
        if (isEmpty()) return null;
        return data[f];
    }

    @Override
    public E last() {
        if (isEmpty()) return null;
        // (f + sz - 1) % data.length
        int back = (f + sz - 1) % data.length;
        return data[back];
    }

    @Override
    public void addFirst(E e) {
        if (sz == data.length) throw new IllegalStateException("Deque is full");

        f = (f - 1 + data.length) % data.length;
        data[f] = e;
        sz++;
    }

    @Override
    public void addLast(E e) {
        if (sz == data.length) throw new IllegalStateException("Deque is full");

        int avail = (f + sz) % data.length;
        data[avail] = e;
        sz++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) return null;
        E answer = data[f];
        data[f] = null;
        f = (f + 1) % data.length;
        sz--;
        return answer;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) return null;
        int back = (f + sz - 1) % data.length;
        E answer = data[back];
        data[back] = null;
        sz--;
        return answer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int k = 0; k < sz; k++) {
            if (k > 0) sb.append(", ");
            sb.append(data[(f + k) % data.length]);
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(10);
        deque.addFirst(5);
        deque.addLast(15);
        System.out.println(deque); // [5, 10, 15]
        deque.removeFirst();
        System.out.println(deque); // [10, 15]
        deque.removeLast();
        System.out.println(deque); // [10]
    }
}