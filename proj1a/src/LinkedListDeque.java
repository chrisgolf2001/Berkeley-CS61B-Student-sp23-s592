import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {

    private class Node<T>{


        public Node prev;
        public T item;
        public Node next;


        public Node(){


            prev = null;
            item = null;
            next = null;
        }
        public Node(T i, Node n, Node p){
            prev = p;
            item = i;
            next = n;
        }

    }


    public Node sentinelF;
    public Node sentinelB;

    public Node sentinel;

    int size;

    public LinkedListDeque(){
        sentinel = new Node("??",null, null);
        size = 0;
    }

    public LinkedListDeque(T item){
        sentinel = new Node<T>(item, null,null);
        sentinel.next = new Node<T>(item, null, null);
        size = 1;
    }
    public static void main(String[] args) {
        Deque<Integer> lld1 = new LinkedListDeque<>();
    lld1.addFirst(4);
    lld1.addFirst(3);
        lld1.addFirst(2);
        lld1.addFirst(1);
        lld1.addLast(5);

    lld1.toList();
    lld1.removeFirst();
    lld1.removeLast();
    lld1.get(1);


    }
    @Override
    public void addFirst(T x) {
        size++;
        sentinel.next = new Node(x, sentinel.next,sentinel.prev);
        sentinel.next.prev = sentinel.prev;

    }


    @Override
    public void addLast(T x) {
        size++;
        Node p = sentinel;

        while(p.next != null){
            p = p.next;
        }
        p.next = new Node(x, null, null);

    }

    @Override
    public List<T> toList() {
        Node<T> items = new Node<>();
        List<T> ls = new ArrayList<>();
        Node n = sentinel;
        T k;
        while(n.next != null){
            n=n.next;
            items = n;
            k = items.item;
            ls.add(k);

        }
        return ls;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
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
        Node<T> first = new Node<>();
        first = sentinel.next;
        sentinel.next = sentinel.next.next;

        size--;
        return first.item;

    }

    @Override
    public T removeLast() {

        Node p = sentinel;
        Node<T> last = new Node<>();

        while(p.next.next != null){
            p = p.next;
        }
        last = p.next;
        p = sentinel.next;


        size--;
        return last.item;
    }

    @Override
    public T get(int index) {

        Node p = sentinel.next;
        Node<T> item = new Node<>();
        if(index < size){
            while(index != -1){
                p = p.next;
                item = p;
                index--;
            }
        }

        return item.item;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}

