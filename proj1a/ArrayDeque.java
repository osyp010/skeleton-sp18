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
			nextFirst += items.length;
		}
		else if (nextFirst == items.length) {
			nextFirst = 0;
		}
	}

	/** loop back-point(nextLack) front when it is out */
	private void checkLast() {
		if (nextLast == items.length) {
			nextLast = 0;
		}
		else if (nextLast == -1) {
			nextLast += items.length;
		}
	}

	/** half the deque-size when usage is lower than the proper rate */
	private void checkLoiter() {
		// Attention: int / int is always a  rounded int
		if (items.length > 16 && size < LOITER_FACTOR * items.length) {
			shrink((int) (items.length / 2));
		}
	}

	/** enlarger the deque-size when size if full */
	private void checkFull() {
		if (size == items.length) {
			larger((int) (size * FULL_FACTOR));
		}
	}

	/** smaller array*/
	private void shrink(int capacity) {
		T[] newArr = (T[]) new Object[capacity];
		int first = itemIdx(0);
		//
		System.out.println("shrinking to " + capacity);
		//
		if (first > itemIdx(size - 1)) {
			int newFirst = first + capacity - items.length;
			System.out.println("nl: " + nextLast);
			System.arraycopy(items, 0, newArr, 0, nextLast);
			System.arraycopy(items, first, newArr, newFirst, items.length - first);
			nextFirst = newFirst - 1;
		} else {
			System.arraycopy(items, first, newArr, 0, size);
			nextFirst = capacity - 1;
			nextLast = size;
		}
		items = newArr;
		checkFirst();
		checkLast();
	}

	/** larger array */
	private void larger(int capacity) {
		T[] newArr = (T[]) new Object[capacity];
		int first = itemIdx(0);
		int newFirst = first + capacity - items.length;
		System.arraycopy(items, 0, newArr, 0, nextLast);
		System.arraycopy(items, first, newArr, newFirst, items.length - first);
		nextFirst = newFirst - 1;

		items = newArr;
		checkFirst();
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
		items[nextFirst] = item;
		nextFirst -= 1;
		checkFirst();
		size += 1;	
		checkFull();
	} 

	/** Adds an item of type T to the back of the deque. */
	public void addLast(T item) {
		items[nextLast] = item;
		nextLast += 1;
		checkLast();
		size += 1;
		checkFull();		
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
			T record = get(-1);
			items[itemIdx(-1)] = null;
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
		if (index > size - 1) {
			return null;
		}
		return items[itemIdx(index)];
	}

}
