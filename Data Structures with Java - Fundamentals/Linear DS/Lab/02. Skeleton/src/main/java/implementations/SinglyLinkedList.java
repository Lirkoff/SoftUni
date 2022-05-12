package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private int size;

    private static class Node<E> {
        private E value;
        private Node<E> next;

        Node(E element) {
            this.value = element;
            this.next = null;
        }
    }

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);

        newNode.next = this.head;
        this.head = newNode;

        this.size++;
    }


    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.isEmpty()) {
            addFirst(newNode.value);
        } else {
            Node<E> current = this.head;
            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
            this.size++;

        }

    }

    @Override
    public E removeFirst() {
        ensureNonEmpty();
        Node<E> current = this.head;

        this.head = current.next;

        this.size--;

        return current.value;
    }

    @Override
    public E removeLast() {
        ensureNonEmpty();

        if (this.size == 1) {
            E value = this.head.value;
            this.head = null;

            return value;
        }

        Node<E> preLast = this.head;
        Node<E> toRemove = this.head.next;

        while (toRemove.next != null) {
            preLast = toRemove;
            toRemove = toRemove.next;
        }

        preLast.next = null;

        this.size--;

        return toRemove.value;
    }


    @Override
    public E getFirst() {
        ensureNonEmpty();
        return this.head.value;
    }

    private void ensureNonEmpty() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    public E getLast() {
        ensureNonEmpty();
        Node<E> current = this.head;

        while (current.next != null) {
            current = current.next;
        }

        return current.value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current.next != null;
            }

            @Override
            public E next() {
                E element = current.value;
                current = current.next;
                return element;
            }
        };
    }
}
