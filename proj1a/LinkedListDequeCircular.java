public class LinkedListDequeCircular<T> {

	private class TNode {
		public TNode prev;
		public T item;
		public TNode next;

		public TNode(TNode p, T i, TNode n) {
			prev = p;
			item = i;
			next = n;
		}
	}

	/* The first item is always at sentinel.next, 
		The last item is always at sentinel.prev
	*/
	private TNode sentinel;
	private int size;


	/** Creates an empty array deque. */	
	public LinkedListDequeCircular() {
		size = 0;
		sentinel = new TNode(null, null, null);
		sentinel.prev = sentinel;
		sentinel.next = sentinel;
	}

	/** Adds an item of type T to the front of the deque. */
	public void addFirst(T item) {
		size += 1;
		sentinel.next = new TNode(sentinel, item, sentinel.next);
		sentinel.next.next.prev = sentinel.next;

	} 

	/** Adds an item of type T to the back of the deque. */
	public void addLast(T item) {
		size += 1;
		sentinel.prev = new TNode(sentinel.prev, item, sentinel);
		sentinel.prev.prev.next = sentinel.prev;
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
			TNode p = sentinel.next;
			while (p.item != null) {
				System.out.print(p.item);
				System.out.print(" ");
				p = p.next;
			}
		} 
		System.out.println();
	}

	/** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
	public T removeFirst() {
		if (this.isEmpty()) {
			return null;
		}
		else {
			size -=1 ;
			TNode p = sentinel.next;
			T record = p.item;
			sentinel.next = p.next;
			p.next.prev = sentinel;
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
			TNode p = sentinel.prev;
			T record = p.item;
			sentinel.prev = p.prev;
			p.prev.next = sentinel;
			return record;	
		}
	}

	/** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque! */
	public T get(int index) {	
		if ((index > size-1) || (index < 0)) {
			return null;
		}	
		TNode p = sentinel.next;
		for (int i=0; i<index; i++) {
			p = p.next;
		}
		return p.item;
	}

	/** Same as get, but uses recursion. */
	public T getRecursive(int index) {
		if ((index > size-1) || (index < 0)) {
			return null;
		}
		TNode p = sentinel.next;
		if (index == 0) {
			return p.item;
		}
		else {
			p = p.next;
			getRecursive((index-1));
		}
		return p.item;
	}

	/** Test the class */
	public static void main(String[] args) {
		LinkedListDequeCircular<String> dl = new LinkedListDequeCircular<> ();

		//test addFirst() & addLast()
		dl.addFirst("I");
		dl.addFirst("Hello");
		dl.addLast("am");
		dl.addLast("here");

		System.out.printf("Current size: %d\n", dl.size());

		//test removeFirst() & removeLast()
		System.out.println(dl.removeFirst());
		System.out.println(dl.removeLast());
		dl.printDeque();

		//test get(i) & 
		System.out.println(dl.get(1));
		System.out.println(dl.getRecursive(1));

	}
}