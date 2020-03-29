package model;

public class TuringMachine {
	
	private DoublyLinkedList2 dll;
	private Node C0;
	private Node C1;
	private Node C2;
	
	public TuringMachine() {
		this.dll = new DoublyLinkedList2(); 
		this.C0 = new Node('0');
		this.C1 = new Node('1');
		this.C2 = new Node('2');
	}
	
	/**
	 * If a new node will be appended, there are two cases:
	 * 1. If current length is even (i.e., the new length will be odd). 
	 * Then there are 2 cases:
	 * 		a. If new node will be appended to the right, C1 will also move to the right.
	 * 		b. If new node will be appended to the left, C1 will stay as is.
	 * 2. If current length is odd (i.e., the new length will be even).
	 * Then there are 2 cases:
	 * 		a. If new node will be appended to the right, C1 will be stay as is.
	 * 		b. If new node will be appended to the left, C1 will also move to the left.
	 */
	public void delete(char head) {
		if(dll.length == 0) return;
		if(dll.length == 1) reset();
		if( dll.length > 1 ) {
			if( head == '0') deleteC0();
			else if( head == '1' ) deleteC1();
			else deleteC2();
		}			
	}
	
	public void deleteC0() {
		moveC0ToRight();
		if( dll.length % 2 == 0) {
			moveC1ToRight();
		} 
		dll.deleteFromStart();
	}

	public void deleteC1() {
		if( dll.length > 2) {
			if(dll.length % 2 == 0) {
				Node deleted = C1.getNext();
				dll.delete( deleted );				
				C1.setNext(  deleted.getNext() ); //Move right
			}else {
				Node deleted = C1.getNext();
				dll.delete( deleted );
				C1.setNext(deleted.getPrev()); // Move left
			}
		} else { //If dll.length equals 2
			moveC1ToRight();
			moveC0ToRight();
			dll.deleteFromStart();
		}	
	}
	
	public void deleteC2() {
		moveC2ToLeft();
		if( dll.length % 2 != 0) {
			moveC1ToLeft();
		} 
		dll.deleteFromEnd();			
	}	
		
	public void add(char head, char letter) {
		if(dll.isEmpty()) {
			dll.append(letter);
			C0.setNext(dll.getFirst());
			C1.setNext(dll.getFirst());
			C2.setNext(dll.getFirst());
		} else {
			if(head=='0') addC0(letter);
			else if(head=='1') addC1(letter);
			 else addC2(letter);
		}
	}
	
	public void addC0(char letter) {
		dll.prepend(letter);
		C0.setNext(dll.getFirst());
		if( dll.length % 2 == 0) { 
			moveC1ToLeft();
		}
	}
	
	public void addC1(char letter) {
		if( dll.length % 2 == 0 ) {
			dll.addAfter(letter, C1.getNext());
			moveC1ToRight();
		}else {	
			dll.addAfter(letter, C1.getNext().getPrev());
			moveC1ToLeft();
			if( dll.length == 2 )
				moveC0ToLeft();
		}
	}
	
	public void addC2(char letter) {
		dll.append(letter);
		C2.setNext( dll.getLast());
		if( dll.length % 2 != 0 )
			moveC1ToRight();
	}
	
	public void moveC0ToRight() {
		if(C0 != dll.getLast())
			C0.setNext( C0.getNext().getNext() );
	}
	
	public void moveC0ToLeft() {
		if(C0 != dll.getFirst())
			C0.setNext( dll.getFirst() );
	}
	
	public void moveC2ToLeft() {
		if(C2 != dll.getFirst())
			C2.setNext( dll.getLast().getPrev() );
	}
	
	public void moveC1ToRight() {
		if( C1 != dll.getLast() )
			C1.setNext(C1.getNext().getNext());
	}
	
	public void moveC1ToLeft(){
		if( C1 != dll.getFirst() )
			C1.setNext(C1.getNext().getPrev());
	}
	
	public void reset() {
		this.dll = new DoublyLinkedList2(); 
		this.C0 = new Node('0');
		this.C1 = new Node('1');
		this.C2 = new Node('2');
	}
	
	@Override
	public String toString() {
		String res = dll.toString() + "\n";
		res += "C0: " + ((C0.getNext() == null ) ? "null" : C0.getNext().toString()) + "\n";
		res += "C1: " + ((C1.getNext() == null ) ? "null" : C1.getNext().toString()) + "\n";
		res += "C2: " + ((C2.getNext() == null ) ? "null" : C2.getNext().toString()) + "\n";		
		return res;
	} 
	
	public String getHeadString(char head) {
		if( dll.isEmpty() ) return "#";
		if(head == '0') return C0.getNext().toString();
		else if(head == '1') return C1.getNext().toString();
		else return C2.getNext().toString();
	}

	/**
	 * @return the dll
	 */
	public DoublyLinkedList2 getDll() {
		return dll;
	}

	/**
	 * @param dll the dll to set
	 */
	public void setDll(DoublyLinkedList2 dll) {
		this.dll = dll;
	}

	/**
	 * @return the c0
	 */
	public Node getC0() {
		return C0;
	}

	/**
	 * @return the c1
	 */
	public Node getC1() {
		return C1;
	}

	/**
	 * @return the c2
	 */
	public Node getC2() {
		return C2;
	}
}
