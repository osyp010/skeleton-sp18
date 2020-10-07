public class ArrayDeque<T> {
	/*The amount of memory that your program uses at any given time must be proportional to the number of items.*/
	/* add and remove must take constant time */
	private int size;
	private T[] items;
	private int nextFirst;
	private int nextLast;
	private final double FULL_FACTOR = 2;
	private final double LOITER_FACTOR = 0.25;

	/** Given the (valid) index from user's view, return its according position in items represented by index */
	private int itemIdx(int index) {
		if (index < 0) {
			int itemIdx = index + nextLast;
			if (itemIdx < 0) itemIdx += items.length;
			return itemIdx;
		}	
		int itemIdx = index + nextFirst + 1;
		if (itemIdx > items.length-1) itemIdx -= items.length;
		return itemIdx;
	}

	/** loop front-pointer(nextFirst) back when it is out */
	private void checkFirst() {
		if (nextFirst == -1) nextFirst = items.length-1;
	}

	/** loop back-point(nextLack) front when it is out */
	private void checkLast() {
		if (nextLast == items.length) nextLast = 0;
	}

	/** half the deque-size when usage is lower than the proper rate */
	private void checkLoiter() {
		// Attention: int / int is always a  rounded int
		if (size > 16 && size < LOITER_FACTOR * items.length) resize((int)(items.length/2));
	}

	/** enlarger the deque-size when size if full */
	private void checkFull() {
		if (size == items.length) resize((int)(size * FULL_FACTOR));
	}

	/** Creates an empty array deque. */	
	public ArrayDeque() {
		size = 0;
		nextFirst = 4;
		nextLast = 5;
		items = (T[]) new Object[8];
	}

	/** Change the Deque size*/
	public void resize(int capacity) {
		T[] newArr = (T[]) new Object[capacity];
		if (nextFirst > nextLast || size == items.length) {
			int first = itemIdx(0);
			int newFirst = first + capacity - items.length;
			System.arraycopy(items, 0, newArr, 0, nextLast);
			System.arraycopy(items, first, newArr, newFirst, items.length - first);
			nextFirst = newFirst - 1;
		}
		else System.arraycopy(items, 0, newArr, 0, size);
		items = newArr;
	} 

	/** Adds an item of type T to the front of the deque. */
	public void addFirst(T item) {
		size += 1;	
		checkFull();
		items[nextFirst] = item;
		nextFirst -= 1;
		checkFirst();
	} 

	/** Adds an item of type T to the back of the deque. */
	public void addLast(T item) {
		size += 1;
		checkFull();
		items[nextLast] = item;
		nextLast += 1;
		checkLast();
	}

	/** Returns true if deque is empty, false otherwise. */
	public boolean isEmpty() {
		return (size==0? true:false);
	}

	/** Returns the number of items in the deque. */
	public int size() {
		return size;
	}

	/** Prints the items in the deque from first to last, separated by a space. */
	public void printDeque() {
		if (this.isEmpty()) {
			System.out.println("Empty Deque!");
		}
		else {
			for (int i=0; i<size; i++) {
				System.out.print(items[itemIdx(i)]);
				System.out.print(" ");
				
			}
			//System.out.println(Arrays.toString(items));
		System.out.println();
		} 
	}

	/** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
	public T removeFirst() {
		if (this.isEmpty()) {
			return null;
		}
		else {
			size -= 1;
			checkLoiter();
			T record = get(0);
			items[itemIdx(0)] = null;
			nextFirst += 1;
			checkFirst();
			return record;
		}		
	}

	/** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
	public T removeLast() {
		if (this.isEmpty()) {
			return null;
		}
		else {
			size -= 1;
			checkLoiter();
			T record = get(-1);
			items[itemIdx(-1)] = null;
			nextLast -= 1;
			checkLast();
			return record;	
		}
	}

	/** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque! */
	public T get(int index) {	
		if (index > items.length-1) {
			return null;
		}
		return items[itemIdx(index)];
	}

	/** Test the class */
	public static void main(String[] args) {
		
		System.out.println("===Testing addFirst===");
			ArrayDeque<Integer> a1 = new ArrayDeque<> ();
			a1.addFirst(10);
			a1.addFirst(9);
			a1.addFirst(8);
			a1.addFirst(7);
			a1.addFirst(6);
			a1.addFirst(5);
			a1.addFirst(4);
			a1.addFirst(3);
			a1.addFirst(2);
			a1.addFirst(1);
			a1.addFirst(0);
			a1.printDeque();
		System.out.println("===Testing get(i)===");
			System.out.println(a1.get(0));
			System.out.println(a1.get(1));
			System.out.println(a1.get(-1));
			System.out.println(a1.get(-2));
			System.out.println(a1.get(a1.size-1));
			System.out.println(a1.get(-a1.size));
		System.out.println("===Testing removeFirst===");
			System.out.println(a1.removeFirst()+" removed");
			System.out.println(a1.removeFirst()+" removed");
			System.out.println(a1.removeFirst()+" removed");
			a1.printDeque();

		// System.out.println("===Testing addLast===");
		// 	ArrayDeque<Integer> a2 = new ArrayDeque<> ();	
		// 	a2.addLast(0);
		// 	a2.addLast(1);
		// 	a2.addLast(2);
		// 	a2.addLast(3);
		// 	a2.addLast(4);
		// 	a2.addLast(5);
		// 	a2.addLast(6);
		// 	a2.addLast(7);
		// 	a2.addLast(8);
		// 	a2.addLast(9);
		// 	a2.addLast(10);
		// System.out.println("===Testing get(i)===");
		// 	System.out.println(a2.get(0));
		// 	System.out.println(a2.get(1));
		// 	System.out.println(a2.get(a2.size()-1));
		// 	System.out.println(a2.get(-a2.size()));
		// System.out.println("===Testing removeLast===");
		// 	System.out.println(a2.removeLast()+" removed");
		// 	System.out.println(a2.removeLast()+" removed");
		// 	System.out.println(a2.removeLast()+" removed");

	}
}