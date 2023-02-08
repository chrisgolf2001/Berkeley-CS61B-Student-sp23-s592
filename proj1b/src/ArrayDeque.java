import java.util.ArrayList;
import java.util.List;


public class ArrayDeque<T> implements Deque<T> {


    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] list = (T[])new Object[8];

    public static void main (String[] args) {
        Deque<Character> ad = new ArrayDeque<>();
        ad.addFirst('a');
        ad.addFirst('b');
        ad.addFirst('c');
        ad.addFirst('d');
        ad.addFirst('e');
        ad.addFirst('f');
        ad.addLast('g');
        ad.addLast('h');
        ad.removeFirst();
        ad.removeLast();
        System.out.println(ad.toList());
        ad.addLast('t');


    }

    public ArrayDeque () {
        size = 0;
        nextFirst = 0;
        nextLast = 0;
    }

    public void resize (int cap, int nSize) {

        T[] newL = (T[])new Object[cap];
        System.arraycopy(list, 0, newL, 0, nSize);
        list = newL;

    }

    public void add (int idx, int place, T value) {
        T[] newL = (T[])new Object[list.length];
        System.arraycopy(list, 0, newL, idx, size);
        newL[place] = value;
        list = newL;
        nextLast++;
        size++;
    }

    @Override
    public void addFirst (T x) {
        if (size == list.length) {
            resize(size + 4, size);
        }

        add(1, 0, x);


    }

    @Override
    public void addLast (T x) {
        if (size == list.length) {
            resize(size + 1, size);
        }
        add(0, nextLast, x);


    }

    @Override
    public List<T> toList () {
        List<T> nList = new ArrayList<>();
        if (size == 0) {
            return nList;
        } else {
            for (int i = 0;i < size;i++) {
                nList.add(list[i]);
            }
        }


        return nList;
    }

    @Override
    public boolean isEmpty () {
        return size == 0;
    }

    @Override
    public int size () {
        return size;
    }

    public void remove (int idx, int size) {
        T[] newL = (T[])new Object[list.length];
        System.arraycopy(list, idx, newL, 0, size - 1);
        list = newL;
        resize(size - 1, size - 1);
        nextLast--;
        this.size--;
    }

    @Override
    public T removeFirst () {
        if (nextFirst == list.length) {
            return null;
        } else {
            T item = list[0];
            nextFirst = 0;
            remove(1, size);
            return item;
        }

    }

    @Override
    public T removeLast () {

        if (nextLast == 0) {
            return null;
        } else {
            T item = list[nextLast - 1];
            remove(0, size);
            return item;
        }
    }

    @Override
    public T get (int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return list[index];
    }

}

