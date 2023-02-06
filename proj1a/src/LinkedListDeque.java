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
        sentinel = new Node("??", null, null);
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

        Deque<Character> lld2 = new LinkedListDeque<>();
        lld2.addLast('A');
        lld2.addLast('B');
        lld2.addLast('C');
        lld2.addLast('D');
        lld2.addLast('E');
        lld2.addLast('F');
        System.out.println(lld2.toList());

        lld2.removeLast();
        lld2.removeLast();
        System.out.println(lld2.toList());
        lld2.removeFirst();
        lld2.removeFirst();
        lld2.removeFirst();
        lld2.removeFirst();
        System.out.println(lld2.toList());
    }
    @Override
    public void addFirst(T x) {
        size++;
        Node<T> n = new Node<T> (x, null,null);
        n.prev = sentinel;
        n.next = sentinel.next;
        sentinel.next.prev = n;
        sentinel.next = n;

    }


    @Override
    public void addLast(T x) {
        size++;
        Node <T> n = new Node <T> (x, null, null);
        n.prev = sentinel.prev;
        n.next = sentinel;
        sentinel.prev.next = n;
        sentinel.prev = n;
    }

    @Override
    public List <T> toList() {

        List <T> ls = new ArrayList<>();
        Node n = sentinel;
        int count = 0;

        while (count < size){

            ls.add((T) n.next.item);
            n = n.next;
            count++;
        }

        return ls;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0){
            throw new IndexOutOfBoundsException();
        }

       Node <T> next = sentinel.next.next;
       sentinel.next = next;
       next.prev = sentinel;
       size--;

       return next.item;

    }

    @Override
    public T removeLast() {

    if (size == 0){
        throw new IndexOutOfBoundsException();
    }

    Node <T> last = sentinel.prev;
    sentinel.prev = last.prev;
    last.prev.next = sentinel;
    size--;

    return last.item;
    }

    @Override
    public T get(int index) {

        Node p = sentinel;
        Node <T> item = new Node<>();

        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }

        while (index != -1){
            p = p.next;
            item = p;
            index --;

        }

        return item.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }else{
            return (T) helperRecursive(sentinel.next, index);
        }

    }

    public T helperRecursive(Node<T> n, int idx) {
        if (idx == 0){
            return n.item;
        }
        return (T) helperRecursive(n.next, idx - 1);
    }
}

