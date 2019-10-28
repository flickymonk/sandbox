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

    public T set(int index, T value) {
        return navigate(index).replaceValue(value);
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
        if (size != that.size) return false;
        Iterator<?> thatItr = that.iterator();
        Iterator<T> thisItr = iterator();
        while (thisItr.hasNext() && thatItr.hasNext()) {
            if (!Objects.equals(thisItr.next(), thatItr.next()))
                return false;
        }
        return !(thatItr.hasNext() || thisItr.hasNext());
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (T e : this)
            hashCode = 31 * hashCode + Objects.hashCode(e);
        return hashCode;
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
        return new Itr();
    }

    private final class Itr implements Iterator<T> {

        private Node<T> previous;

        private Node<T> current;

        Itr() {
            this.current = new Node<>(null, ForwardLinkedList.this.head);
            this.previous = new Node<>(null, current);
        }

        @Override
        public boolean hasNext() {
            return current.hasNext();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> next = current.next();
            previous = current;
            current = next;
            return next.getValue();
        }

        @Override
        public void remove() {
            Node<T> next = current.next();
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

        public E replaceValue(E value) {
            E old = this.value;
            this.value = value;
            return old;
        }
    }

}
