public class ArrayDequeTest {
    public static ArrayDeque<Integer> a;
    private static final String sep = "* * * * * * * * * *";

    public ArrayDequeTest(ArrayDeque arr) {
        a = arr;
    }

    public static void add(int first, int last) {
        //System.out.println("addFirst-" + first + "-numbers");
        for (int i = first; i > 0; i--) {
            a.addFirst(i);
        }
        //System.out.println("addLast-" + last + "-numbers");
        for (int i = first + 1; i < last + first; i++) {
            a.addLast(i);
        }
        System.out.println("add-" + (last + first) + "-numbers");
    }

    public static void remove(int first, int last) {
        //System.out.println("removeFirst-" + first + "-numbers");
        for (int i = 0; i < first; i++) {
            a.removeFirst();
        }
        //System.out.println("removelast-" + last + "-numbers");
        for (int i = 0; i < last; i++) {
            a.removeLast();
        }
        System.out.println("remove-" + (last + first) + "-numbers");
    }

    public static void getAll() {
        System.out.println("got-" + size() + "-numbers");
        for (int i = 0; i < size(); i++) {
            System.out.print(a.get(i) + " ");
            if (a.get(i) == null) {
                System.out.println("#Got null#");
                System.out.println("printing deque:");
                a.printDeque();
                System.out.println("printing array:");
                System.out.println(java.util.Arrays.toString(a.items));
                assert(false);
            }
        }
        System.out.println();
    }

    public static void showInfo() {
        System.out.println("showing-" + a.size() + "-numbers");
        getAll();
        System.out.println(sep);
    }

    private static int randInt(int limit) {
        return (int) (Math.random() * limit);
    }

    public static void randAdd(int f, int l) {
        add(randInt(f),randInt(l));
    }

    public static void randRmv() {
        int f = randInt(size());
        int l = randInt(size() - f);
        remove(f,l);
    }

    public static int size() {
        return a.size();
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        ArrayDequeTest tst1 = new ArrayDequeTest(a);
        for (int i = 0; i < 50; i++) {
             randAdd(20, 40);
             randRmv();
             showInfo();
        }
        System.out.println("!!!ALLPASSED!!!");
    }
}