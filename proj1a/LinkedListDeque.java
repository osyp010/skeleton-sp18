public class LinkedListDeque<T> {

	private class TNode {
		private TNode prev;
		private T item;
		private TNode next;

		private TNode(TNode p, T i, TNode n) {
			prev = p;
			item = i;
			next = n;
		}
	}

	/* The first item is always at sentFront.next, 
		The last item is always at sentBack.prev
	*/
	private TNode sentFront;
	private TNode sentBack;
	private int size;


	/** Creates an empty array deque. */	
	public LinkedListDeque() {
		size = 0;
		sentFront = new TNode(null, null, null);
		sentBack = new TNode(null, null, null);

		sentFront.next = sentBack;
		sentBack.prev = sentFront;
	}

	/** Adds an item of type T to the front of the deque. */
	public void addFirst(T item) {
		size += 1;
		sentFront.next = new TNode(sentFront, item, sentFront.next);
		sentFront.next.next.prev = sentFront.next;

	} 

	/** Adds an item of type T to the back of the deque. */
	public void addLast(T item) {
		size += 1;
		sentBack.prev = new TNode(sentBack.prev, item, sentBack);
		sentBack.prev.prev.next = sentBack.prev;
	}

	/** Returns true if deque is empty, false otherwise. */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
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
			TNode p = sentFront.next;
			while (p.item != null) {
				System.out.printf((String) p.item);
				System.out.printf(" ");
				p = p.next;
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
			size -= 1;
			TNode p = sentFront.next;
			T record = p.item;
			sentFront.next = p.next;
			p.next.prev = sentFront;
			return record;	
		}
	}

	/** Removes and returns the item at the back of the deque.
	 If no such item exists, returns null. */
	public T removeLast() {
		if (this.isEmpty()) {
			return null;
		} else {
			size -= 1;
			TNode p = sentBack.prev;
			T record = p.item;
			sentBack.prev = p.prev;
			p.prev.next = sentBack;
			return record;	
		}
	}

	/** Gets the item at the given index, where 0 is the 
	front, 1 is the next item, and so forth. If no such item 
	exists, returns null. Must not alter the deque! */
	public T get(int index) {	
		if ((index > size - 1) || (index < 0)) {
			return null;
		}	
		TNode p = sentFront.next;
		for (int i = 0; i < index; i++) {
			p = p.next;
		}
		return p.item;
	}

	/** Same as get, but uses recursion. */
	public T getRecursive(int index) {
		if ((index > size - 1) || (index < 0)) {
			return null;
		}
		// for every call, p set to first node
		TNode p = sentFront.next;
		return recHelper(p, index);	
	}

	private T recHelper(TNode p, int index) {
		if (index == 0) {
			return p.item;
		} else {
			p = p.next;
			return recHelper(p, index - 1);
		}
	}

	/** Test the class */
	private static void main(String[] args) {
		LinkedListDeque<String> dl = new LinkedListDeque<>();

		//test addFirst() & addLast()
		dl.addFirst("I");
		dl.addFirst("Hello");
		dl.addLast("am");
		dl.addLast("here");

		System.out.printf("Current size: %d\n", dl.size());

		//test removeFirst() & removeLast()
		// System.out.println(dl.removeFirst());
		// System.out.println(dl.removeLast());
		dl.printDeque();

		//test get(i) & getRecursive(i)
		//System.out.println(dl.get(1));
		System.out.println(dl.getRecursive(0));
		System.out.println(dl.getRecursive(1));
		System.out.println(dl.getRecursive(2));
		System.out.println(dl.getRecursive(3));

	}
}
