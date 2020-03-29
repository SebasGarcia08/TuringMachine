package model;

public class Node {
	private Node next;
	private Node prev;
	private char data;
	public Node(char d){
		prev = null;
		next = null;
		this.data = d;
	}

	/**
	 * @return the next
	 */
	public Node getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(Node next) {
		this.next = next;
	}

	/**
	 * @return the prev
	 */
	public Node getPrev() {
		return prev;
	}

	/**
	 * @param prev the prev to set
	 */
	public void setPrev(Node prev) {
		this.prev = prev;
	}

	/**
	 * @return the data
	 */
	public char getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(char data) {
		this.data = data;
	}

	public String toString() {
		return String.valueOf(data);
	}
}