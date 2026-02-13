package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;   // remove final
    private int size = 0;          // remove final

    public CircularlyLinkedList() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        if (i < 0 || i >= size) return null;
        Node<E> curr = tail.next; // head
        for (int k = 0; k < i; k++) {
            curr = curr.next;
        }
        return curr.data;
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        if (i < 0 || i > size) return;

        if (i == 0) {
            addFirst(e);
            return;
        }
        if (i == size) {
            addLast(e);
            return;
        }

        // insert in middle
        Node<E> prev = tail.next; // head
        for (int k = 0; k < i - 1; k++) {
            prev = prev.next;
        }
        prev.next = new Node<>(e, prev.next);
        size++;
    }

    @Override
    public E remove(int i) {
        if (i < 0 || i >= size) return null;

        if (i == 0) return removeFirst();
        if (i == size - 1) return removeLast();

        Node<E> prev = tail.next; // head
        for (int k = 0; k < i - 1; k++) {
            prev = prev.next;
        }
        Node<E> target = prev.next;
        prev.next = target.next;
        size--;
        return target.data;
    }

    public void rotate() {
        if (tail != null) {
            tail = tail.next; // move tail forward => head advances by 1
        }
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;
        boolean firstPass = true;

        @Override
        public boolean hasNext() {
            if (tail == null) return false;
            if (firstPass) return true;
            return curr != tail;
        }

        @Override
        public E next() {
            curr = curr.next;      // start from head when first called
            firstPass = false;
            E res = curr.data;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) return null;

        Node<E> head = tail.next;
        E res = head.data;

        if (size == 1) {
            tail = null;
            size = 0;
            return res;
        }

        tail.next = head.next; // bypass old head
        size--;
        return res;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) return null;

        Node<E> head = tail.next;

        if (size == 1) {
            E res = tail.data;
            tail = null;
            size = 0;
            return res;
        }

        // find node before tail
        Node<E> prev = head;
        while (prev.next != tail) {
            prev = prev.next;
        }

        E res = tail.data;
        prev.next = tail.next; // keep circular
        tail = prev;
        size--;
        return res;
    }

    @Override
    public void addFirst(E e) {
        if (isEmpty()) {
            tail = new Node<>(e, null);
            tail.next = tail;
            size = 1;
            return;
        }

        Node<E> head = tail.next;
        Node<E> newest = new Node<>(e, head);
        tail.next = newest;
        size++;
    }

    @Override
    public void addLast(E e) {
        addFirst(e);
        tail = tail.next; // new node becomes tail
    }

    // Q9: merge two sorted linked lists
    public CircularlyLinkedList<E> sortedMerge(CircularlyLinkedList<E> other) {
        CircularlyLinkedList<E> result = new CircularlyLinkedList<>();

        int n1 = this.size;
        int n2 = other.size;

        Node<E> p1 = (this.tail == null) ? null : this.tail.next;   // head of this
        Node<E> p2 = (other.tail == null) ? null : other.tail.next; // head of other

        while (n1 > 0 && n2 > 0) {
            E a = p1.data;
            E b = p2.data;

            @SuppressWarnings("unchecked")
            int cmp = ((Comparable<? super E>) a).compareTo(b);

            if (cmp <= 0) {
                result.addLast(a);
                p1 = p1.next;
                n1--;
            } else {
                result.addLast(b);
                p2 = p2.next;
                n2--;
            }
        }

        while (n1 > 0) {
            result.addLast(p1.data);
            p1 = p1.next;
            n1--;
        }

        while (n2 > 0) {
            result.addLast(p2.data);
            p2 = p2.next;
            n2--;
        }

        return result;
    }




    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
