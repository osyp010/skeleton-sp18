public class ArrayDeque<T> {
	private int size;
	private T[] items;
	private int nextFirst;
	private int nextLast;
	private final double FULL_FACTOR = 2;
	private final double LOITER_FACTOR = 0.25;

	/** Given the (valid) index from user's view, return its 
	according position in items represented by index */
	private int itemIdx(int index) {
		if (index < 0) {
		//negative index is not included in this projext
			int itemIdx = index + nextLast;
			if (itemIdx < 0) {
				itemIdx += items.length;
			}
			return itemIdx;
		}	
		int itemIdx = index + nextFirst + 1;
		if (itemIdx > items.length - 1) {
			itemIdx -= items.length;
		}
		return itemIdx;
	}

	/** loop front-pointer(nextFirst) back when it is out */
	private void checkFirst() {
		if (nextFirst == -1) {
			nextFirst = items.length - 1;
		}
	}

	/** loop back-point(nextLack) front when it is out */
	private void checkLast() {
		if (nextLast == items.length) {
			nextLast = 0;
		}
	}

	/** half the deque-size when usage is lower than the proper rate */
	private void checkLoiter() {
		// Attention: int / int is always a  rounded int
		if (size > 16 && size < LOITER_FACTOR * items.length) {
			resize((int) (items.length / 2));
		}
	}

	/** enlarger the deque-size when size if full */
	private void checkFull() {
		if (size == items.length) {
			resize((int) (size * FULL_FACTOR));
		}
	}

	/** Change the Deque size*/
	private void resize(int capacity) {
		T[] newArr = (T[]) new Object[capacity];
		if (nextFirst > nextLast || size == items.length) {
			int first = itemIdx(0);
			int newFirst = first + capacity - items.length;
			System.arraycopy(items, 0, newArr, 0, nextLast);
			System.arraycopy(items, first, newArr, newFirst, items.length - first);
			nextFirst = newFirst - 1;
		} else {
			System.arraycopy(items, 0, newArr, 0, size);
		}
		items = newArr;
	}

	/** Creates an empty array deque. */	
	public ArrayDeque() {
		size = 0;
		nextFirst = 4;
		nextLast = 5;
		items = (T[]) new Object[8];
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
		return (size == 0 ? true : false);
	}

	/** Returns the number of items in the deque. */
	public int size() {
		return size;
	}

	/** Prints the items in the deque from first to last, separated by a space. */
	public void printDeque() {
		if (this.isEmpty()) {
			System.out.println("Empty Deque!");
		} else {
			//System.out.println("total length: " + items.length);
			for (int i = 0; i < size; i++) {
				System.out.print(items[itemIdx(i)]);
				System.out.print(" ");	
			}
		}
		System.out.println();
	}

	/** Removes and returns the item at the front of the 
	deque. If no such item exists, returns null. */
	public T removeFirst() {
		if (this.isEmpty()) {
			return null;
		} else {
			T record = get(0);
			items[itemIdx(0)] = null;
			nextFirst += 1;
			checkFirst();
			size -= 1;
			checkLoiter();
			return record;
		}		
	}

	/** Removes and returns the item at the back of the deque. 
	If no such item exists, returns null. */
	public T removeLast() {
		if (this.isEmpty()) {
			return null;
		} else {
			//T record = get(-1);
			T record = get(size-1);
			items[itemIdx(size-1)] = null;
			nextLast -= 1;
			checkLast();
			size -= 1;
			checkLoiter();
			return record;	
		}
	}

	/** Gets the item at the given index, where 0 is the 
	front, 1 is the next item, and so forth. If no such item 
	exists, returns null. Must not alter the deque! */
	public T get(int index) {	
		if (index > items.length - 1) {
			return null;
		}
		return items[itemIdx(index)];
	}

	/** Test the class */
	private static void main(String[] args) {
		final int TST0 = 20;
		final int TST1 = 50;
		System.out.println("===Testing addFirst===");
		ArrayDeque<Integer> a1 = new ArrayDeque<>();
		for (int i = 0; i < TST0; i++) {
			a1.addFirst(i);
		}
		System.out.println("===Testing size===");
		System.out.println("size: " + a1.size());
		System.out.println("===Printing total items then the deque===");
		System.out.println(java.util.Arrays.toString(a1.items));
		System.out.println("nextFirst:" + a1.nextFirst + " nextLast:" + a1.nextLast);
		a1.printDeque();
		
		System.out.println("===Testing get===");
		for (int i = 0; i < TST0; i++) {
			System.out.print(a1.get(i)+" ");
		}
		System.out.println();
		System.out.println("===Testing removeFirst===");
		for (int i = 0; i < TST0; i++) {
			a1.removeFirst();
		}
		System.out.println("===Testing isEmpty===");
		a1.printDeque();



		System.out.println("===Testing addLast===");
		for (int i = 0; i < TST1; i++) {
			a1.addLast(i);
		}
		System.out.println("===Testing size===");
		System.out.println("size: " + a1.size());
		System.out.println("===Printing total items and the deque===");
		System.out.println(java.util.Arrays.toString(a1.items));
		System.out.println("nextFirst:" + a1.nextFirst + " nextLast:" + a1.nextLast);
		a1.printDeque();
		System.out.println("===Testing get===");
		for (int i = 0; i < TST1; i++) {
			System.out.print(a1.get(i)+" ");
		}
		System.out.println();
		System.out.println("===Testing removeLast===");
		for (int i = 0; i < TST1; i++) {
			a1.removeLast();
		}
		System.out.println("===Testing isEmpty===");
		a1.printDeque();
	}
}
