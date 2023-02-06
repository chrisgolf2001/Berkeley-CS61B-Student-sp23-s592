
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    @DisplayName("LinkedListDeque has no fields besides nodes and primitives")
    void noNonTrivialFields() {
        Class<?> nodeClass = NodeChecker.getNodeClass(LinkedListDeque.class, true);
        List<Field> badFields = Reflection.getFields(LinkedListDeque.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(nodeClass) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not nodes or primitives").that(badFields).isEmpty();
    }

    @Test
    /** In this test, we have three different assert statements that verify that addFirst works correctly. */
    public void addFirstTestBasic() {
        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(lld1.toList()).containsExactly("back").inOrder();

        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
    }

    @Test
    /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
     *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
    public void addLastTestBasic() {
        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

//     Below, you'll write your own tests for LinkedListDeque.

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void getSize() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]


        assertThat(lld1.size()).isEqualTo(5);
    }
    @Test
    public void isLinkedListEmpty() {
        Deque<Integer> lld1 = new LinkedListDeque<>();
         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.isEmpty()).isEqualTo(false);
    }

    @Test
    public void LinkedListIsEmpty() {
        Deque<Integer> lld1 = new LinkedListDeque<>();
         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */

        assertThat(lld1.isEmpty()).isEqualTo(true);
    }

    @Test
    public void deleteLastTwo() {
        Deque<Character> lld1 = new LinkedListDeque<>();
         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast('A');   // [A]
        lld1.addLast('B');   // [A, B]
        lld1.addLast('C'); // [A, B, C]
        lld1.addLast('D');   // [A, B, C, D]
        lld1.removeLast();
        lld1.removeLast();


        assertThat(lld1.removeLast()).isEqualTo('B');
    }


    @Test
    public void getElement() {
        Deque<Character> lld1 = new LinkedListDeque<>();
         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast('A');   // [A]
        lld1.addLast('B');   // [A, B]
        lld1.addLast('C'); // [A, B, C]
        lld1.addLast('D');   // [A, B, C, D]
        lld1.addLast('E');
        lld1.addLast('F');


        assertThat(lld1.get(3)).isEqualTo('D');
    }

    @Test
    public void checkTillEmpty() {
        Deque<Character> lld1 = new LinkedListDeque<>();
         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast('A');   // [A]
        lld1.addLast('B');   // [A, B]
        lld1.addLast('C'); // [A, B, C]
        lld1.addLast('D');   // [A, B, C, D]
        lld1.addLast('E');
        lld1.addLast('F');

        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeFirst();



        assertThat(lld1.isEmpty()).isEqualTo(true);
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addLastTest() {
        Deque<String> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast("Matt");
        lld1.addLast("Alec");
        lld1.addLast("Martina");
        lld1.addLast("Sobec");
        lld1.addLast("Nicolas");

        assertThat(lld1.toList()).containsExactly("Matt", "Alec", "Martina", "Sobec", "Nicolas").inOrder();

        lld1.removeLast();
        lld1.addFirst("Kevin");
        assertThat(lld1.toList()).containsExactly("Kevin","Matt", "Alec", "Martina", "Sobec").inOrder();
    }

    @Test
    public void getElementNegative() {
        Deque<Integer> lld1 = new LinkedListDeque<>();
         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(-1);
        lld1.addLast(-200);
        lld1.addLast(10);
        lld1.addLast(0);
        lld1.addLast(78);
        lld1.addLast(-9);


        assertThat(lld1.get(-1)).isEqualTo(null);
    }
    @Test
    public void getElement2() {
        Deque<Character> lld1 = new LinkedListDeque<>();
         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast('A');   // [A]
        lld1.addLast('B');   // [A, B]
        lld1.addLast('C'); // [A, B, C]
        lld1.addLast('D');   // [A, B, C, D]
        lld1.addLast('E');
        lld1.addLast('F');
        lld1.addLast('Q');


        assertThat(lld1.get(6)).isEqualTo('Q');
    }
    @Test
    public void getElement3() {
        Deque<Character> lld1 = new LinkedListDeque<>();
         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast('A');   // [A]
        lld1.addLast('B');   // [A, B]
        lld1.addLast('C'); // [A, B, C]
        lld1.addLast('D');   // [A, B, C, D]
        lld1.addFirst('E');
        lld1.addLast('F');


        assertThat(lld1.get(0)).isEqualTo('E');
    }
    @Test
    public void getElementRecursiveNeg() {
        Deque<Integer> lld1 = new LinkedListDeque<>();
         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(-1);   // [A]
        lld1.addLast(100);   // [A, B]
        lld1.addLast(-8); // [A, B, C]
        lld1.addFirst(100);   // [A, B, C, D]
        lld1.addFirst(8);
        lld1.addLast(-9);


        assertThat(lld1.getRecursive(-1)).isEqualTo(null);
    }

    @Test
    public void getElementRecursiveNegOut() {
        Deque<Integer> lld1 = new LinkedListDeque<>();
         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(-1);   // [A]
        lld1.addLast(100);   // [A, B]
        lld1.addLast(-8); // [A, B, C]
        lld1.addFirst(100);   // [A, B, C, D]
        lld1.addFirst(8);
        lld1.addLast(-9);


        assertThat(lld1.getRecursive(-1)).isEqualTo(null);
    }
    @Test
    public void getElement5() {
        Deque<Character> lld1 = new LinkedListDeque<>();
         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast('A');   // [A]
        lld1.addLast('B');   // [A, B]
        lld1.addLast('C'); // [A, B, C]
        lld1.addLast('D');   // [A, B, C, D]
        lld1.addLast('E');
        lld1.addLast('F');


        assertThat(lld1.get(0)).isEqualTo('A');
        assertThat(lld1.get(1)).isEqualTo('B');
        assertThat(lld1.get(2)).isEqualTo('C');
        assertThat(lld1.get(3)).isEqualTo('D');
        assertThat(lld1.get(4)).isEqualTo('E');
        assertThat(lld1.get(5)).isEqualTo('F');


    }

    @Test
    public void removeElemTest() {
        Deque<Character> lld1 = new LinkedListDeque<>();
         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast('A');   // [A]
        lld1.addFirst('B');   // [A, B]
        lld1.addLast('C'); // [A, B, C]
        lld1.addLast('D');   // [A, B, C, D]
        lld1.addLast('E');
        lld1.addLast('F');

        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        lld1.addLast('K');



        assertThat(lld1.isEmpty()).isEqualTo(false);
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void getOutOfBoundsTest() {
        Deque<String> lld = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld.addFirst("Matt");
        lld.addFirst("Alec");
        lld.addFirst("Martina");
        lld.addFirst("Sobec");
        lld.addLast("Nicolas");

        assertThat(lld.toList()).containsExactly("Sobec", "Martina", "Alec", "Matt", "Nicolas").inOrder();

        lld.removeLast();
        lld.addFirst("Kevin");
        assertThat(lld.toList()).containsExactly("Kevin", "Sobec", "Martina", "Alec", "Matt").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void toListCheck() {
        Deque<String> lld = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld.addFirst("Matt");
        lld.addFirst("Alec");
        lld.addFirst("Martina");
        lld.addFirst("Sobec");
        lld.addLast("Nicolas");

        assertThat(lld.toList()).containsExactly ("Sobec", "Martina", "Alec", "Matt", "Nicolas").inOrder();

    }
    @Test
    public void getOutOfBoundsTestRecursive() {
        Deque<String> lld = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld.addFirst("Matt");
        lld.addFirst("Alec");
        lld.addFirst("Martina");
        lld.addFirst("Sobec");
        lld.addLast("Nicolas");

        assertThat(lld.getRecursive(100)).isEqualTo(null);

    }

    @Test
    public void toListEmpty() {
        Deque<String> lld = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld.addFirst("Matt");
        lld.addFirst("Alec");

        lld.removeLast();
        lld.removeLast();

        assertThat(lld.toList()).isEmpty();

    }

    @Test
    public void toListEmpty2() {
        Deque<String> lld = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */


        assertThat(lld.toList()).isEmpty();

    }

    @Test
    public void removeLastTest() {
        Deque<String> lld = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */

        lld.addFirst("Matt");
        lld.addFirst("Alec");


        assertThat(lld.removeLast()).isEqualTo("Matt");

    }

    @Test
    public void removeFirstTest() {
        Deque<String> lld = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */

        lld.addFirst("Matt");
        lld.removeFirst();

        assertThat(lld.toList()).isEmpty();

    }

    @Test
    public void removeFirstToEmptyTest() {
        Deque<String> lld = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */

        lld.addFirst("Matt");
        lld.removeFirst();
        assertThat(lld.toList ()).isEmpty();

    }
    @Test
    public void removeLastToEmptyTest() {
        Deque<String> lld5 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */

        lld5.addFirst("Matt");
        lld5.removeFirst ();
        assertThat(lld5.toList()).isEmpty();

    }


    @Test
    public void removeFirstTillEmpty() {
        Deque<String> lld5 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */

        lld5.addFirst("Matt");
        lld5.addFirst("Matt");
        lld5.addFirst("Matt");
        lld5.addFirst("Matt");
        lld5.addFirst("Matt");



        lld5.removeFirst();
        lld5.removeFirst();
        lld5.removeFirst();
        lld5.removeFirst();
        lld5.removeFirst();


        assertThat(lld5.toList()).isEmpty();

    }

    @Test
    public void removeLastTillEmpty() {
        Deque<String> lld5 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */


            lld5.addFirst("Matt");
            lld5.addFirst("Matt");
            lld5.addFirst("Matt");
            lld5.addFirst("Matt");
            lld5.addFirst("Matt");

            lld5.removeLast();
            lld5.removeLast();
            lld5.removeLast();
            lld5.removeLast();
            lld5.removeLast();




        assertThat(lld5.toList()).isEmpty();

    }
    @Test
    public void getOob_large() {
        Deque<Character> lld1 = new LinkedListDeque<>();
         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast('A');   // [A]
        lld1.addLast('B');   // [A, B]
        lld1.addLast('C'); // [A, B, C]
        lld1.addLast('D');   // [A, B, C, D]
        lld1.addLast('E');
        lld1.addLast('F');
        lld1.addLast('Q');


        assertThat(lld1.get(600)).isEqualTo(null);
    }

    @Test
    public void getRecursiveMany() {
        Deque<Character> lld1 = new LinkedListDeque<>();
         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast('A');   // [A]
        lld1.addLast('B');   // [A, B]
        lld1.addLast('C'); // [A, B, C]
        lld1.addLast('D');   // [A, B, C, D]
        lld1.addLast('E');
        lld1.addLast('F');
        lld1.addLast('Q');


        assertThat(lld1.getRecursive (0)).isEqualTo('A');
        assertThat(lld1.getRecursive (1)).isEqualTo('B');
        assertThat(lld1.getRecursive (2)).isEqualTo('C');
        assertThat(lld1.getRecursive (3)).isEqualTo('D');
        assertThat(lld1.getRecursive (600)).isEqualTo(null);
    }


}

