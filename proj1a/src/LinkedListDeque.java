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

    public Node sentinel;

    int size;

    public LinkedListDeque(){
        sentinel = new Node("??",null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item){
        sentinel = new Node<T>(item, null,null);
        Node<T> n = new Node<T>(item, null,null);

        sentinel.prev = n;
        sentinel.next = n;

        n.next = sentinel;
        n.prev = sentinel;

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
    lld1.getRecursive(1);


    }
    @Override
    public void addFirst(T x) {

        size++;
        Node<T> n = new Node<T>(x, null,null);
        n.prev = sentinel;
        n.next = sentinel.next;
        sentinel.next.prev = n;
        sentinel.next = n;


    }


    @Override
    public void addLast(T x) {
        size++;
        Node<T> n = new Node<T>(x, null,null);
        n.prev = sentinel.prev;
        n.next = sentinel;
        sentinel.prev.next = n;
        sentinel.prev = n;
    }

    @Override
    public List<T> toList() {
        Node<T> items = new Node<>();
        List<T> ls = new ArrayList<>();
        Node n = sentinel;
        T k;
        int count = 0;

        while(count < size){

            n=n.next;
            items = n;
            k = items.item;
            ls.add(k);
            count++;

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

       Node<T> next = sentinel.next.next;
       sentinel.next = next;
       next.prev = sentinel;

       size--;
      return next.item;

    }

    @Override
    public T removeLast() {

        Node<T> last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;

        size--;
        return last.item;
    }

    @Override
    public T get(int index) {

        Node p = sentinel.next;
        Node<T> item = new Node<>();

        if(index >= size) {
            throw new IndexOutOfBoundsException();
        }

            while(index != -1){
                p = p.next;
                item = p;
                index--;

        }

        return item.item;
    }

    @Override
    public T getRecursive(int index) {
       Node <T> p = sentinel.next;
        T k;
        if(index >= size){
            throw new IndexOutOfBoundsException();
        }
            k =  helperRecursive(p,index);

        return k;
    }

    public T helperRecursive(Node<T> n, int idx){

        if(idx == 0){
            return n.item;
        }
        return (T) helperRecursive(n.next,idx-1);
    }
}

