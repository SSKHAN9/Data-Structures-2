package edu.ser222.m01_03;

import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * CompletedList represents an implementation of a list.
 *
 * @author Saad Khan, Acuna
 * @version (version)
 */
public class CompletedList<T> implements ListADT<T>, Iterable<T> {

    //The following three variables are a suggested start if you are using a list implementation.
    protected int count;
    protected int modChange;
    protected DoubleLinearNode<T> head, tail;

    //TODO: implement this!
    
    CompletedList() {
    	this.count = 0;
    	modChange=0;
    	head = null;
    	tail = null;
    }
    
    public T removeFirst() throws NoSuchElementException {
    	if(isEmpty()) {
    		throw new NoSuchElementException();
    	}
    	T top = head.getElement();
    	if (head == tail) {
    		head = null;
    		tail = head;
    		} else if (head.getNext() == tail){
                head.setElement(null);
                head = tail;
    		}else {
    			head = head.getNext();
    			head.getPrev().setElement(null);
    			}
    	count--;
    	modChange++;
    	return top;
    }
    
    public T removeLast() throws NoSuchElementException {
    	if (isEmpty()) {
    		throw new NoSuchElementException();
    	} 
    	T bottom = tail.getElement();
    	if (head == tail) {
    		head = null;
    		tail = head;
    		} else if(head.getNext() == tail){
                tail.setElement(null);
                tail = head;
    		}else {
    			tail = tail.getPrev();
    			tail.remove();
    			}
    	count--;
    	modChange++;
        return bottom;
    }

    public T remove( T element ) {
        if(isEmpty())
            throw new NoSuchElementException();
        if(tail.getElement().equals(element)){
            return removeLast();
        }
        for (DoubleLinearNode<T> curr = head; curr != null; curr = curr.getNext()) {
            if(curr.getElement().equals(element)){
                if(curr == head){
                    return removeFirst();
                }else{
                    count--;
                    modChange++;
                    return curr.remove();
                }
            }
        }
        throw new NoSuchElementException();
    }
    
    public T first() {
    	if (isEmpty()) {
    		throw new NoSuchElementException();
    		}
    	return head.getElement();
    }
    
    public T last() {
    	if (isEmpty()) {
    		throw new NoSuchElementException("The list is empty.");
    		}
    	return tail.getElement();
    }
    
    public boolean contains(T target) {
    	if (isEmpty()) {
    		return false;
    		} else if(tail.getElement().equals(target)){
                return true;
            }
    	for (DoubleLinearNode<T> curr = head; curr != null; curr = curr.getNext()) {
            if(curr.getElement().equals(target)){
                return true;
            }
        }
    	return false;
    }
    
    public boolean isEmpty(){
    	if (this.size() == 0) {
    		return true;
    		}
    	return false;
    }
    
    public int size() {
    	return this.count;
    	}
    
    public Iterator<T> iterator(){
    	return new ListIterator();
    }
    
    public String toString() {
    	if (isEmpty()) {
    		return "empty";
    	}
    	DoubleLinearNode<T> cursor = head;
    	String str = "";
    	for (int i = 0; i < count; i++) {
    		str += cursor.getElement();
    		if (cursor != tail) {
    			str += " "; 
    		}
    		cursor = cursor.getNext();
    		}
    	return str;
    		}
  
    
    class ListIterator implements Iterator<T> {
    	
    	private final int modCounted = modChange;
    	private DoubleLinearNode<T> iter = head;
    	
    	public boolean hasNext() {
    		return iter != null;
    		}
    	
    	public T next() {
    		if (!hasNext()) {
    			throw new NoSuchElementException();
    		}
    		if (modChange != modCounted) {
    			throw new ConcurrentModificationException(); 
    		}
    			T element = iter.getElement(); 
    			iter = iter.getNext(); 
    			return element;
    		}
    	}
}