package listAndSublist;

import java.lang.IndexOutOfBoundsException;

/* Tsipora Stone
 * 114110213
 * 0108
 * I pledge on my honor that I have not given or received
 * any unauthorized assistance on this assignment*/

// a class that adds elements of any type to a linked list and contains methods
// that alter the list in different ways
public class ListNoOrder<T extends Comparable<T>>
implements Comparable<ListNoOrder<T>> {

	protected class Node<T>{
		T data;
		Node<T> next;

		public Node(T data){
			this.data = data;
			next = null;
		}
	}
	Node<T> head;



	// constructor creates empty list
	public ListNoOrder() {
		head = null;
	}

	// adds newElt to the end of the list if it is not null
	public void add(T newElt) {
		Node<T> curr = head;
		Node<T> elt = new Node(newElt);
		// if newElt is null throws IllegalArgumentException
		if (newElt == null){
			throw new IllegalArgumentException();
		}
		// if head is not null - not the first element adds elt to the list
		if (head != null){
			while (curr.next != null){
				curr = curr.next;
			}
			curr.next = elt;
		}
		// if head is null - it is the first element head gets elt
		else{
			head = elt;

		}
	}

	// returns the length of the list by going through and incrementing size
	public int length() {
		Node<T> curr = head;
		int size = 0;
		// if head is not null and curr is not null (not the end of the list)
		// increment size and go to next element
		if (head != null){
			while (curr != null){
				size++;
				curr = curr.next;
			}
		}
		return size;
	}

	// returns a String that includes all the data of elements in the list
	public String toString() {
		Node<T> curr = head;
		String data = "";
		// if the list isn't empty and curr is not at the last element 
		// adds the data to String with a comma and space
		if (this.length() > 0){
			while (curr.next != null){
				data += curr.data + ", ";
				curr = curr.next;
			}
			// if curr is the last element just add data of the element to String
			data += curr.data;
		}
		return data;
	}

	// resets list by removing all elements in list
	public void reset() {
		Node<T> curr = head;
		if (this.length() > 0){
			while(curr != null){
				head = head.next;
				curr = curr.next;
			}
		}
		if (head == null){
			curr = null;
		}
	}

	// counts number of times element exists in list and returns number
	public int numOccurrencesOfElement(T element) {
		int match = 0;
		Node<T> curr = head;
		// checks that element is not null and throws exception if it is
		if (element == null){
			throw new IllegalArgumentException();
		}
		// while not at the end of the list if element matches curr data
		// match incremented
		while (curr != null){
			if (element.compareTo(curr.data) == 0){
				match++;
			}
			curr = curr.next;
		}
		return match;
	}

	// finds the position of element in list and returns position
	public int positionOfElement(T element) {
		Node<T> curr = head;
		int pos = 0;
		// checks that element is not null and throws exception if it is
		if (element == null){
			throw new IllegalArgumentException();
		}
		// while not at end of list the position is incremented
		while (curr != null && curr.data.compareTo(element) != 0){
			pos++;
			// if curr data is equal to element return that position
			if (curr.data == element){
				return pos;
			}
			curr = curr.next;
		}
		// if element doesn't exist in list return -1
		if (curr == null){
			pos = -1;
		}
		return pos;
	}

	// finds the element at position in list and returns that element
	public T elementAtPosition(int position)
			throws IndexOutOfBoundsException {
		if (position<0||position>=length()){
			throw new IndexOutOfBoundsException();
		}

		Node<T> curr = head.next;
		int pos = 0;
		T element = null;
		if (curr == null){
			pos++;
			curr = head.next;
		}
		// keeps track of pos by incrementing the var
		while (curr != null && pos != position){
			pos++;
			// if the pos matches the position passed in return the curr data
			if (pos == position){
				return curr.data;
			}
			curr = curr.next;
		}
		// if position doesn't match pos ever throw IndexOutOfBounds exception
		if(curr == null)
			element=head.data;
		else
			element = curr.data;
		return element;
	}

	// adds elements after element to a new list object
	public ListNoOrder<T> elementsAfter(T element) {
		ListNoOrder<T> newList = new ListNoOrder<T>();
		Node<T> curr = head;
		// checks that element is not null and throws exception if it is
		if (element == null){
			throw new IllegalArgumentException();
		}
		// goes through to end of list and checks that curr data is not element
		while (curr != null && curr.data != element){
			curr = curr.next;
		}
		// this happens when curr data is element - adds the next element on 
		// to the new list
		while (curr != null && curr.next != null){
			newList.add(curr.next.data);
			curr = curr.next;
		}
		return newList;
	}

	// returns the distance between element1 and element2 in the list
	public int distanceBetween(T element1, T element2) {
		Node<T> curr = head;
		int distance = 0;
		int pos1 = 0;
		int pos2 = 0;
		if (element1 == null || element2 == null){
			throw new IllegalArgumentException();
		}
		if (element1.compareTo(element2) == 0){
			distance = 0;
		}
		// goes through while not at the end of the list and curr data 
		// isn't element1 and increments pos1
		while (curr != null && curr.data != element1) {
			curr = curr.next;
			pos1++;
		}
		// checks that element 1 is in the list then goes through rest of the list
		// to find element2 incrementing pos2
		if (curr != null && curr.data == element1){
			pos2 = pos1;
			while (curr != null && curr.data != element2) {
				curr = curr.next;
				pos2++;
			}
		}
		else{
			distance = -1;
		}
		// if they both exist the distance is pos2 - pos1
		if (curr != null && curr.data == element2) {
			distance = pos2 - pos1;
		}
		else {
			distance = -1;
		}
		return distance;
	}

	// removes the element at position in the list
	public void removeElementAtPosition(int position)
			throws IndexOutOfBoundsException {
		int pos = 0;
		Node<T> prev = null;
		Node<T> curr = head;
		// if position is not a valid position throw exception
		if (position < 0 || position >= this.length()){
			throw new IndexOutOfBoundsException(); 
		}
		// checks that not at the end of the list and that the next pos 
		// is not equal to position and increments pos
		while (curr.next != null && pos != position){
			prev = curr;
			curr = curr.next;
			pos++;
		}
		// sets curr to the next element in the list after prev
		// if pos = position removes the element from the list 
		if(prev == null)
			head = curr.next;
		else if (pos == position){
			prev.next = curr.next;
		}
	}

	// compares two lists by each element and returns negative or positive int
	// depending if the element of the paramater list that doesn't match 
	// is greater or less than the the element of the other list or if one of 
	// the lists is longer than the other
	public int compareTo(ListNoOrder<T> otherList) {
		if (otherList == null){
			throw new IllegalArgumentException();
		}
		int compare = 0;
		Node<T> curr = head;
		Node<T> otherListCurr = otherList.head;
		// updates curr and otherListCurr to next element while they are equal
		while (curr != null && otherListCurr != null && 
				curr.data == otherListCurr.data){
			curr = curr.next;
			otherListCurr = otherListCurr.next;
		}
		// if curr list is shorter than the param list compare is < 0
		if (curr == null && otherListCurr != null){
			compare = -2;
		}
		// if curr list is longer than the param list compare is > 0
		else if (curr != null && otherListCurr == null){
			compare = 2;
		}
		// if the curr object is less than the param object value compare is < 0
		else if (curr != null && curr.data.compareTo(otherListCurr.data) < 0){
			compare = -1;
		}
		// if the curr objects is greater than the param object 
		// value compare is > 0
		else if (curr != null && curr.data.compareTo(otherListCurr.data) > 0){
			compare = 1; 
		}
		return compare;
	}
}
