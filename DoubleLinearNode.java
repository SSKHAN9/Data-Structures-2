package edu.ser222.m01_03;

public class DoubleLinearNode<Item> {

	private DoubleLinearNode<Item> next = null;
	private DoubleLinearNode<Item> prev = null;
	private Item elem;
	
	public DoubleLinearNode() {
		next =null;
		prev =null;
		this.elem = null;
	}
	
	public DoubleLinearNode(Item item) {
		next =null;
		prev =null;
		this.elem = item;
	}
	
	public DoubleLinearNode<Item> getPrev(){
		return prev;
	}
	
	
	public void setPrev(DoubleLinearNode<Item> item){
		if(this.prev != null) {
            this.prev.next = item;
            item.prev = this.prev;
        }
        this.prev = item;
        if(item != null) {
            item.next = this;
        }
	}
	
	public DoubleLinearNode<Item> getNext(){
		return next;
	}
	
	public void setNext(DoubleLinearNode<Item> item){
		if(this.next != null) {
            this.next.prev = item;
            item.next = this.next;
        }
        this.next = item;
        if(item != null)
            item.prev = this;
	}
	
	public Item getElement() {
		return elem;
	}
	
	public void setElement(Item element) {
		this.elem = element;
	}
	
	public Item remove(){
        if(this.prev != null)
            this.prev.next = this.next;
        if(this.next != null)
            this.next.prev = this.prev;
        return this.elem;
    }
}

