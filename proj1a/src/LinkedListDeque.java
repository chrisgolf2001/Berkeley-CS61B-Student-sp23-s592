import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {

    private class Node<T> {
        private Node prev;
        private T item;
        private Node next;

        public Node() {
            prev = null;
            item = null;
            next = null;
        }

        public Node(T i, Node n, Node p) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;

    int size;

    public LinkedListDeque() {
        sentinel = new Node(0, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new Node<T>(item, null, null);
        Node<T> n = new Node<T>(item, null, null);
        sentinel.prev = n;
        sentinel.next = n;

        n.next = sentinel;
        n.prev = sentinel;

        size = 1;
    }

    public static void main(String[] args) {

    }

    @Override
    public void addFirst(T x) {
        size++;
        Node<T> n = new Node<T>(x, null, null);
        n.prev = sentinel;
        n.next = sentinel.next;
        sentinel.next.prev = n;
        sentinel.next = n;

    }


    @Override
    public void addLast(T x) {
        size++;
        Node<T> n = new Node<T>(x, null, null);
        n.prev = sentinel.prev;
        n.next = sentinel;
        sentinel.prev.next = n;
        sentinel.prev = n;
    }

    @Override
    public List<T> toList() {

        List<T> ls = new ArrayList<>();
        Node n = sentinel;
        int count = 0;

        while (count < size) {
            ls.add((T) n.next.item);
            n = n.next;
            count++;
        }
        return ls;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        return (T) remove(sentinel.next);

    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        return (T) remove(sentinel.prev);
    }

    public T remove(Node<T> n) {
        size--;
        n.prev.next = n.next;
        n.next.prev = n.prev;

        return n.item;
    }

    @Override
    public T get(int index) {

        Node p = sentinel;
        Node<T> item = new Node<>();

        if (index >= size || index < 0) {
            return null;
        }

        while (index != -1) {
            p = p.next;
            item = p;
            index--;

        }
        return item.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return (T) helperRecursive(sentinel.next, index);
    }

    public T helperRecursive(Node<T> n, int idx) {
        if (idx == 0) {
            return n.item;
        }
        return (T) helperRecursive(n.next, idx - 1);
    }
}

