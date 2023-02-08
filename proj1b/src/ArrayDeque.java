import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;


public class ArrayDeque<T> implements Deque<T> {


    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] list = (T[]) new Object[8];

    public static void main(String[] args) {
        Deque<Character> ad = new ArrayDeque<>();
        ad.addFirst('a');
        ad.addFirst('b');
        ad.addFirst('c');
        ad.addFirst('d');
        ad.removeLast();
        ad.removeLast();
        for(int x = 100; x >= 0; x--){
            ad.addFirst('e');
            ad.addLast('p');
        }
        ad.addFirst('e');
        ad.addFirst('f');
        ad.addLast('g');
        ad.addLast('k');
        ad.addLast('l');
        ad.addLast('m');
        ad.addLast('n');
        ad.addLast('o');
        ad.addLast('p');


        ad.removeFirst();
        ad.removeFirst();ad.removeFirst();
        ad.addLast('h');
        ad.removeFirst();
        ad.removeLast();
        ad.removeLast();
        System.out.println(ad.toList());
        ad.addLast('t');
        System.out.println(ad.toList());
        ad.addFirst('f');
        ad.addFirst('f');
        System.out.println(ad.toList());
        ad.addLast('u');
        ad.addFirst('x');
        System.out.println(ad.toList());

    }

    public ArrayDeque() {
        size = 0;
        nextFirst = 0;
        nextLast = 0;
    }

    public void resize(int cap, int nSize) {

        T[] newL = (T[])new Object[cap];
        System.arraycopy(list, 0, newL, 0, nSize);
        list = newL;
    }

    public void add(int idx, int place, T value) {
        if (size == list.length && list.length >= 8) {
            if (list.length < 16) {
                resize(16, size);
            }
            resize((int) round(size * 1.20), size);
        }
        T[] newL = (T[])new Object[list.length];
        System.arraycopy(list, 0, newL, idx, size);
        newL[place] = value;
        list = newL;
        nextLast++;
        size++;
    }

    @Override
    public void addFirst(T x) {
        add(1, 0, x);
    }

    @Override
    public void addLast(T x) {
        add(0, nextLast, x);
    }

    @Override
    public List<T> toList() {
        List<T> nList = new ArrayList<>();
        if (size == 0) {
            return nList;
        } else {
            for (int i = 0; i < size; i++) {
                nList.add(list[i]);
            }
        }
        return nList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {

        return size;

    }

    public void remove(int idx, int size) {
        T[] newL = (T[])new Object[list.length];
        System.arraycopy(list, idx, newL, 0, size - 1);
        list = newL;
        if ((list.length - size) > round(size * 1.20) && list.length >= 16) {
            resize((int) round(size * 1.20), size - 1);
        }
        nextLast--;
        this.size--;
    }

    @Override
    public T removeFirst() {
        if (nextFirst == list.length) {
            return null;
        } else {
            T item = list[0];
            remove(1, size);
            return item;
        }
    }

    @Override
    public T removeLast() {
        if (nextLast == 0) {
            return null;
        } else {
            T item = list[nextLast - 1];
//            remove(0, size);
            size--;
            nextLast--;
            return item;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return list[index];
    }
}

