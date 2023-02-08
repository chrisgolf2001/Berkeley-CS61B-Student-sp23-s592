import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;


public class ArrayDeque<T> implements Deque<T> {


    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] list = (T[])new Object[8];

    public static void main (String[] args) {
        Deque<Character> ad = new ArrayDeque<>();
        ad.addFirst('a');
        ad.addFirst('b');

        ad.removeFirst();
        ad.removeLast();

    }

    public ArrayDeque () {
        size = 0;
        nextFirst = 0;
        nextLast = 0;
    }

    public void resize (int cap) {

        T[] newL = (T[])new Object[cap];
        System.arraycopy(list, nextFirst, newL, 0, size);
        list = newL;
        nextFirst = 0;
        nextLast = size - 1;
    }

    public void add (T value) {

        T[] newL = (T[])new Object[list.length];
        System.arraycopy(list, 0, newL, 1, size);
        newL[nextFirst] = value;
        list = newL;

    }

    public void addCheckSize () {
        if (size == list.length && list.length >= 8 ) {
            if (list.length < 16) {
                resize(16);
            }
            resize((int)round(size * 1.20));
        }

    }

    @Override
    public void addFirst (T x) {

        addCheckSize();
        nextFirst--;
        if (nextFirst >= - 1) {
            nextFirst = 0;
            add(x);
        } else {
            list[nextFirst] = x;
            nextFirst--;
        }

        nextLast++;
        size++;
    }

    @Override
    public void addLast (T x) {

        addCheckSize();
        list[nextLast] = x;
        nextLast++;
        size++;

    }

    @Override
    public List<T> toList () {
        List<T> nList = new ArrayList<>();
        if (size == 0) {
            return nList;
        } else {
            for (int i = 0;i < size;i++) {
                if (list[i] != null) {
                    nList.add(list[i]);
                }
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

    @Override
    public T removeFirst () {
        if (size == 0) {
            return null;
        }
        T item = list[nextFirst];

        if (list.length > round(size * 1.20) && list.length > 8) {
            resize(size);
        }
        nextFirst++;
        list[nextFirst - 1] = null;
        size--;

        return item;

    }

    @Override
    public T removeLast () {
        if (size == 0) {
            return null;
        }
        T item = list[nextLast - 1];

        if (list.length > round(size * 1.20) & list.length >= 16) {
            resize((int)round(size * 1.20));
        }

        list[nextLast - 1] = null;
        nextLast--;
        this.size--;
        if (size == 0) {
            nextLast = 0;
        }
        return item;
    }

    @Override
    public T get (int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return list[index];
    }
}

