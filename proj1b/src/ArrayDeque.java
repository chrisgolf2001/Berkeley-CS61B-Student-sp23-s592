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

    public void resize(int cap) {

        T[] newL = (T[])new Object[cap];
        System.arraycopy(list, nextFirst, newL, 0, size);
        list = newL;
        nextFirst = 0;
        nextLast = size - 1;
    }

    public void add(T value) {

        T[] newL = (T[])new Object[list.length];
        System.arraycopy(list, 0, newL, 1, size);
        newL[nextFirst] = value;
        list = newL;
        size++;
    }

    @Override
    public void addFirst(T x) {
        if (size == list.length && list.length >= 8) {
            if (list.length < 16) {
                resize(16);
            }
            resize((int) round(size * 1.20));
        }

            nextFirst--;
        if (nextFirst == -1){
            nextFirst = 0;
            nextLast++;
            add(x);
        }else{
            list[nextFirst] = x;
            nextFirst--;
            nextLast++;
            size++;
        }

    }

    @Override
    public void addLast(T x) {
        if (size == list.length && list.length >= 8) {
            if (list.length < 16) {
                resize(16);
            }
            resize((int) round(size * 1.20));
        }

        list[nextLast] = x;
        nextLast++;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> nList = new ArrayList<>();
        if (size == 0) {
            return nList;
        } else {
            for (int i = 0; i < size; i++) {
                if(list[i] != null) {
                    nList.add(list[i]);
                }
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


    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = list[nextFirst];

        if (list.length  > round(size * 1.20) && list.length > 8) {
            resize(size);
        }
        nextFirst++;
        list[nextFirst - 1] = null;
        size--;

        return item;

    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = list[nextLast - 1];

        if (list.length  > round(size * 1.20) & list.length >= 16) {
            resize((int) round(size * 1.20));
        }

        list[nextLast - 1] = null;
        nextLast--;
        this.size--;
        return item;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return list[index];
    }
}

