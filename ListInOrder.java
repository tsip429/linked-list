package listAndSublist;

import listAndSublist.ListNoOrder.Node;

/* Tsipora Stone
 * 114110213
 * 0108
 * I pledge on my honor that I have not given or received
 * any unauthorized assistance on this assignment*/

// extends ListNoOrder class - contains lists with elements in 
// non decreasing order
public class ListInOrder<T extends Comparable<T>> extends ListNoOrder<T> {
	// override add and numOccurancesOfElt and positionOfElement??


	@Override
	public void add(T newElt){
		Node<T> curr = head;
		Node<T> prev = null;
		Node<T> elt = new Node<T>(newElt);
		//Node newCurr = null;
		// if newElt is null throws IllegalArgumentException
		if (newElt == null){
			throw new IllegalArgumentException();
		}
		// if head is not null - not the first element adds elt to the list
		if(head == null){
			head = elt;
			//System.out.println(this.length());
			return;
		} 
		while (curr != null && curr.data.compareTo(elt.data) <= 0){
			prev = curr;
			//System.out.println(curr.data);
			curr = curr.next;
		}
		// if adding an element to the head 
		if (prev == null){
			elt.next = head;
			head = elt;
		}
		else{
			elt.next = curr;
			prev.next = elt;
		}
	}

	// overrides numOccurancesOfElement to not go through the whole list
	// returns the number of times the element is in the list
	@Override
	public int numOccurrencesOfElement(T element) {
		int match = 0;
		Node<T> curr = head;
		// checks that element is not null and throws exception if it is
		if (element == null){
			throw new IllegalArgumentException();
		}
		// while not at the end of the list and while the curr data is less 
		// than the element if element matches curr data match incremented
		while (curr != null && curr.data.compareTo(element) < 0){
			curr = curr.next;
		}
		// while current data is equal to element increment match
		while (curr != null && curr.data.compareTo(element) == 0){
			match++;
			curr = curr.next;
			}
		return match;
	}

	@Override
	public int positionOfElement(T element) {
		Node<T> curr = head;
		int pos = 0;
		// checks that element is not null and throws exception if it is
		if (element == null){
			throw new IllegalArgumentException();
		}
		// while not at end of list the position is incremented
		if (curr != null){
			while (curr.next != null && curr.data.compareTo(element) < 0){
				curr = curr.next;
				pos++;
			}
			// if curr data is equal to element return that position
			if (curr.data.compareTo(element) == 0){
				return pos;
			}
			else {
				pos = -1;
			}
		}
		// if element doesn't exist in list return -1
		return -1;
	}
}
