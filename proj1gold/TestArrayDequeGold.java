import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void test() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        for(int i = 0; i < 100; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.25) {
                sad1.addLast(i);
                ads1.addLast(i);
                assertEquals("addLast(" + i + ")\n", ads1.getLast(), sad1.get(sad1.size()-1));
            } else {
                sad1.addFirst(i);
                ads1.addFirst(i);
                assertEquals("addFirst(" + i + ")\n", ads1.getFirst(), sad1.get(0));
            }
        }

        for(int i = 0; i < 100; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.25) {
                assertEquals("removeLast()\n", ads1.removeLast(), sad1.removeLast());
            } else {
                assertEquals("removeFirst()\n", ads1.removeFirst(), sad1.removeFirst());
            }
        }
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestArrayDequeGold.class);
    }
}
