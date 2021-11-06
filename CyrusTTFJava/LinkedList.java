/**
* class XRef - Cross Reference Map
*
* I understand the meaning of academic dishonesty, in particular plagiarism, copyright infringement
* and collusion. I am aware of the consequences if found to be involved in these misconducts. I hereby
* declare that the work submitted for the "ITP4510 Data Structures & Algorithms" is authentic record
* of my own work.
*
* @Name : Tsang Tsz Fung
* @StdID: 200539567
* @Class: IT114105/1A
* @2021-import java.io.*;
*/
public class LinkedList {
    public ListNode head;
    public ListNode tail;
    private Comparator comparator;

    public LinkedList(Comparator comparator) {
        head = tail = null;
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return (head==null);
    }

    public void addToHead(Object item) {
        if (isEmpty()) {
            head = tail = new ListNode(item);
        } else {
            head = new ListNode(item, head);
        }
    }

    public void addToTail(Object item) {
        if (isEmpty()) {
            head = tail = new ListNode(item);
        } else {
            tail.next = new ListNode(item);
            tail =  tail.next;
        }
    }

    public Object removeFromHead() throws EmptyListException {
        Object item = null;
        if (isEmpty()) {
            throw new EmptyListException();
        } 
        item = head.data;
        if (head == tail)      // there's only one single node
            head = tail = null;
        else
            head = head.next;
        return item;

    }

    public void insertInOrder (Object item ) {
        if (isEmpty()) {
            head = tail = new ListNode (item);
        }else{
            if (comparator.isGreaterThan(head.data, item)) {
                addToHead(item);
            } else if (comparator.isLessThan(tail.data, item)) {
                addToTail(item);
            } else {
                // insert in the middle
                ListNode current = head;
                while (current.next != null) {
					if (comparator.isGreaterThan(current.next.data, item)) {
						ListNode newNode = new ListNode(item);
						newNode.next = current.next;
						current.next = newNode;
						return;
					}
					current = current.next;
                }
            }
        }
    }
	
	public ListNode findItem(Object target) {
		ListNode current = this.head;
		if(current == null) return null;
		do {
			if(comparator.isEqualTo(current.data, target)) return current;
		} while((current = current.next) != null);
		return null;
	}



}
