package project20280.stacksqueues;

import project20280.interfaces.Queue;
import project20280.list.DoublyLinkedList;

public class LinkedQueue<E> implements Queue<E> {

    private DoublyLinkedList<E> ll;

    public static void main(String[] args) {
        // test logic
        LinkedQueue<Integer> q = new LinkedQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        System.out.println("Queue: " + q);       // [1, 2]
        System.out.println("First: " + q.first()); // 1
        System.out.println("Dequeue: " + q.dequeue()); // 1
        System.out.println("After: " + q);       // [2]
    }

    public LinkedQueue() {
        // TODO
        ll = new DoublyLinkedList<>();
    }

    @Override
    public int size() {
        return ll.size();
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        // TODO
        ll.addLast(e);
    }

    @Override
    public E first() {
        // TODO
        if (isEmpty()) return null;
        return ll.first();
    }

    @Override
    public E dequeue() {
        // TODO
        if (isEmpty()) return null;
        return ll.removeFirst();
    }

    public String toString() {
        return ll.toString();
    }
}