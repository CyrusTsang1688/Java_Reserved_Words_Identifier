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
    private ListNode head;
    private ListNode tail;
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
            head = tail = new LisforWord(item,lineCount);
        } else {
            head = new LisforWord(item, head,lineCount);
        }
    }

    public void addToTail(Object item) {
        if (isEmpty()) {
            head = tail = new LisforWord(item,lineCount);
        } else {
            tail.next = new LisforWord(item,lineCount);
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

    public Object removeFromTail() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        } 
        Object item = tail.data;
        if (head == tail) {   // there is only one node
            head = tail = null;
            return item;
        }
        LisforWord current = head;
        while (current.next != tail)
            current = current.next;
        tail = current;
        tail.next = null;
        return item;
    }

    public String toString() {
        String s = "";
        LisforWord current = head;
        while (current != null) {
            s += current.data +" \n";
            current = current.next;
        }
        return s + "";
    }

    public void insertInOrder (Object item ,Object lineCount) {
        if (isEmpty()) {
            head = tail = new ListNode (item);
        } else {
            if (comparator.isGreaterThan(head.data, item,lineCount)) {
                addToHead(item);
            } else if (comparator.isLessThan(tail.data, item ,lineCount)) {
                addToTail(item);
            } else {
                // insert in the middle
                ListNode current = head;
                while (current.next != null) {
					if (comparator.isEqualTo(current.data, item ,lineCount)){
						
						return;
					}
                    else if (comparator.isGreaterThan(current.next.data, item ,lineCount)) {
                        LisforWord newNode = new LisforWord(item);
                        newNode.next = current.next;
                        current.next = newNode;
                        return;
                    }
                    current = current.next;
                }
            }
        }
    }

    public void removeItem (Object item) throws ItemNotFoundException {
        if (isEmpty()) {
            throw new ItemNotFoundException();
        } 
        if (comparator.isEqualTo(head.data, item)) 
            removeFromHead();
        else if (comparator.isEqualTo(tail.data, item)) 
            removeFromTail();
        else {
            // remove a node in the middle
            ListNode current = head;
            while (current.next != null) {
                if (comparator.isEqualTo(current.next.data, item)) {
                    current.next = current.next.next;
                    return;
                }
                current = current.next;
            }
            throw new ItemNotFoundException();
        }
    }	

}
