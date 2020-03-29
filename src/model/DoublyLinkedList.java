package model;

public class DoublyLinkedList {
		private Node first;
		private Node last;
		public int length = 0;
		
		public void prepend(char d) {
			Node newNode = new Node(d);
			if(first == null) {
				newNode.setNext(last);
				last = newNode;
				last.setPrev(newNode);
			} else {
				newNode.setNext(first);
				first.setPrev(newNode);
			}
			first = newNode;
			length++;
		}
			
		public void append(char d) {
			Node newNode = new Node(d);
			if(first == null) {
				first = newNode;
				first.setNext(last);
			}else {
				newNode.setPrev(last);
				last.setNext(newNode);
			}
			last = newNode;
			length++;
		}
		
		public void append(Node newNode) {
			if(first == null) {
				first = newNode;
				first.setNext(last);
			}else {
				newNode.setPrev(last);
				last.setNext(newNode);
			}
			last = newNode;
			length++;
		}
		
		public void prepend(Node newNode) {
			if(first == null) {
				newNode.setNext(last);
				last = newNode;
				last.setPrev(newNode);
			} else {
				newNode.setNext(first);
				first.setPrev(newNode);
			}
			first = newNode;
			length++;
		}
		
		public void addAfter(char data, Node node) {
			Node next = node.getNext();
			if(next == null) append( data );
			else {
				Node newNode = new Node(data);
				newNode.setPrev(node);
				newNode.setNext(next);
				next.setPrev(newNode);
				node.setNext(newNode);
				length++;
			}
		}
		
		public void addBefore(char data, Node another) {
			Node prev = another.getPrev();
			if(prev == null) prepend(data);
			else {
				Node newNode = new Node(data);
				newNode.setPrev(prev);
				prev.setNext(newNode);
				newNode.setNext(another);
				another.setPrev(newNode);
			}
		}
		
		public Node delete(Node node){
			// When list is empty
	        if (first == null || node == null) { 
	            return null; 
	        } 
	  
	        // If node to be deleted is the first node
	        if (first == node) { 
	            first = node.getNext(); 
	        } 
	  
	        // Change next only if node to be deleted 
	        // is NOT the last node 
	        if (node.getNext() != null) { 
	            node.getNext().setPrev(node.getPrev()); 
	        } 
	  
	        // Change previous node only if node to be deleted 
	        // is NOT the first node 
	        if (node.getPrev() != null) { 
	            node.getPrev().setNext(node.getNext()); 
	        }
	        length--;
	        return node;
		}
				
		public boolean isEmpty() {
			return length == 0;
		}
		
		public String toString() {
			String res = "";
			Node curr = first;
			do {
				res += "<-"+ curr.getData() + "->";
				curr = curr.getNext();
			} while(curr != null);
			return res;
		}

		/**
		 * @return the first
		 */
		public Node getFirst() {
			return first;
		}

		/**
		 * @param first the first to set
		 */
		public void setFirst(Node first) {
			this.first = first;
		}

		/**
		 * @return the last
		 */
		public Node getLast() {
			return last;
		}

		/**
		 * @param last the last to set
		 */
		public void setLast(Node last) {
			this.last = last;
		}
}	
