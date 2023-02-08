
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
        ad.addFirst('e');
        ad.addFirst('f');
        ad.addLast('g');
        ad.addLast('h');
        ad.removeFirst();
        ad.removeLast();
        System.out.println(ad.toList());
        ad.addLast('t');

        ad.addFirst('A');
        ad.addFirst('B');
        ad.addFirst('C');
        ad.addFirst('D');
        ad.addFirst('E');
        ad.addFirst('F');
        ad.addLast('G');
        ad.addLast('H');

        System.out.println(ad.toList());

        Deque<Integer> lld2 = new ArrayDeque<>();

        lld2.addFirst(0);
        lld2.addLast(1);
        lld2.removeLast();
        lld2.get(0);
    }

    public ArrayDeque() {
        size = 0;
        nextFirst = list.length - 1;
        nextLast = 0;
    }

    public void resize(int cap) {

        T[] newL = (T[]) new Object[cap];

        System.arraycopy(list, nextLast, newL, 0, size - nextLast);
        System.arraycopy(list, 0, newL, size - nextFirst - 1, nextLast);
        list = newL;
        nextFirst = list.length - 1;
        nextLast = size;

    }


    @Override
    public void addFirst(T x) {
        double ratio = 1.20;
        if (size == list.length) {
            resize(Integer.valueOf((int) round(size * ratio)));
        }

        size++;
        list[nextFirst] = x;
        nextFirst--;
    }

    @Override
    public void addLast(T x) {
        if (size == list.length) {
            resize(size*2);
        }

        size++;
        list[nextLast] = x;
        nextLast++;
    }
    @Override
    public List<T> toList() {
        List<T> nList = new ArrayList<>();
        if (nextFirst <= list.length - 1) {
            for (int x = nextFirst - 1; x != list.length; x++) {
                if (list[x] != null) {
                    nList.add(list[x]);
                }
            }
        }

        if (nextLast != 0) {
            for (int x = 0; x < nextFirst; x++) {
                if (list[x] != null) {
                    nList.add(list[x]);
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
        if (nextFirst == list.length - 1) {
            return null;
        } else {
            T item = list[nextFirst + 1];
            list[nextFirst + 1] = null;
            nextFirst++;

            size--;
            return item;
        }

    }

    @Override
    public T removeLast() {

        if (nextLast == 0) {
            return null;
        } else {
            T item = list[nextLast - 1];
            list[nextLast - 1] = null;
            nextLast--;
            size--;

            return item;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        if(nextLast == 0){
            return list[nextFirst - 1];
        }
        return list[index];
    }
}


