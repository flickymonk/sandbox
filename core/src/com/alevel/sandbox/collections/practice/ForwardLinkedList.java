package com.alevel.sandbox.collections.practice;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinkedList<T> implements Iterable<T> {

    private Node<T> head;

    private int size = 0;

    public void add(T element) {
        if (isEmpty()) {
            head = new Node<>(element);
        } else {
            tail().link(new Node<>(element));
        }
        size++;
    }

    public void add(int index, T element) {
        Node<T> added;
        if (index == 0) {
            added = new Node<>(element, head);
            head = added;
        } else {
            Node<T> previous = navigate(index - 1);
            Node<T> next = previous.next();
            added = new Node<>(element, next);
            previous.link(added);
        }
        size++;
    }

    public T remove(int index) {
        if (index == size) {
            throw new IndexOutOfBoundsException("Can't remove element, index is equal to size (" + size + ")");
        }
        Node<T> removed;
        if (index == 0) {
            removed = head;
            head = head.next();
        } else {
            Node<T> previous = navigate(index - 1);
            removed = previous.next();
            Node<T> next = removed.next();
            previous.link(next);
        }
        size--;
        return removed.getValue();
    }

    public boolean contains(T element) {
        if (isEmpty()) {
            return false;
        }

        Node<T> n = head;

        do {
            if (Objects.equals(element, n.getValue())) {
                return true;
            }
            n = n.next();
        } while (n.hasNext());

        return false;
    }

    public T get(int index) {
        return navigate(index).getValue();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    private Node<T> navigate(int index) {
        Objects.checkIndex(index, size);
        Node<T> n = head;
        for (int i = 1; i <= index; i++) {
            n = n.next();
        }
        return n;
    }

    private Node<T> tail() {
        Node<T> n = head;
        while (n.hasNext()) {
            n = n.next();
        }
        return n;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForwardLinkedList<?> that = (ForwardLinkedList<?>) o;
        return size == that.size && Objects.equals(that.head, head);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(head);
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");
        sb.append(head.getValue());

        Node<T> n = head;
        while (n.hasNext()) {
            n = n.next();
            sb.append(", ").append(n.getValue());
        }

        return sb.append("]").toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr<>(this);
    }

    private static final class Itr<E> implements Iterator<E> {

        private Node<E> previous;

        private Node<E> current;

        Itr(ForwardLinkedList<E> list) {
            this.current = new Node<>(null, list.head);
            this.previous = new Node<>(null, current);
        }

        @Override
        public boolean hasNext() {
            return current.hasNext();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> next = current.next();
            previous = current;
            current = next;
            return next.getValue();
        }

        @Override
        public void remove() {
            Node<E> next = current.next();
            previous.link(next);
        }
    }

    private static final class Node<E> {

        private E value;

        private Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }

        public void link(Node<E> next) {
            this.next = next;
        }

        public void unlink() {
            this.next = null;
        }

        public Node<E> next() {
            return next;
        }

        public boolean hasNext() {
            return next != null;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value) &&
                    Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, next);
        }
    }

}
