package edu.ser222.m01_03;

/**
 * CompletedOrderedList represents an implementation of an ordered list that builds on
 * CompletedList.
 *
 * @author Saad Khan, Acuna
 * @version (version)
 */
public class CompletedOrderedList<T extends Comparable<T>> extends CompletedList<T>
         implements OrderedListADT<T> {

    //TODO: implement this

	public CompletedOrderedList() {
        super();
    }
	
	@Override
	public void add(T element) throws NullPointerException {
        if(element == null)
            throw new NullPointerException("Element cannot be null");
        if(isEmpty()) {
            head = new DoubleLinearNode<>(element);
            tail = head;
            count++;
            modChange++;
        }else{
            count++;
            modChange++;
            DoubleLinearNode<T> top = head;
            DoubleLinearNode<T> bottom = tail;
            DoubleLinearNode<T> node = new DoubleLinearNode<>(element);
            if(element.compareTo(top.getElement()) <= 0) {
                node.setNext(top);
                head = node;
                return;
            }else if(element.compareTo(bottom.getElement()) >= 0) {
                node.setPrev(bottom);
                tail = node;
                return;
            }
            while((top = top.getNext()) != (bottom = bottom.getPrev())) {
                if(element.compareTo(top.getElement()) <= 0) {
                    top.setPrev(node);
                    return;
                }else if(element.compareTo(bottom.getElement()) >= 0) {
                    bottom.setNext(node);
                    return;
                }
                if(bottom.getPrev() == null) {
                    System.out.println("Back is null!!");
                }
            }
            
            if (element.compareTo(top.getElement()) == -1) {
            	top.getPrev().setNext(node);
            } else if(element.compareTo(top.getElement()) == 0) {
            	top.setPrev(node);
            } else if (element.compareTo(top.getElement()) == 1 ) {
            	top.getNext().setPrev(node);
            }
            
        }
    }
}
	

