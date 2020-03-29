package model;

public class DoublyLinkedList2 {
    int length =0;
    private Node first;
    private Node last;

    public Node prepend(char data){
        Node n = new Node(data);
        if(length==0){
            first = n;
            last = n;
        }else{
            n.setNext(first);
            first.setPrev(n);
            first = n;
        }
        length++;
        return n;
    }

    public Node append(char data){
        Node n = new Node(data);
        if(length==0){
            first = n;
            last = n;
        }else{
            last.setNext(n);
            n.setPrev(last);
            last = n;
        }
        length++;
        return n;
    }
    
    public Node append(Node n){
        if(length==0){
            first = n;
            last = n;
        }else{
            last.setNext(n);
            n.setPrev(last);
            last = n;
        }
        length++;
        return n;
    }

    
//    Node after which new node to be added cannot be null
    public Node addAfter(char data, Node prevNode){
        if(prevNode==null){
            return null;
        }else if(prevNode==last){//check if it a last node
            return append(data);
        }else{
            //create a new node
            Node n = new Node(data);
            //store the next node of prevNode
            Node nextNode = prevNode.getNext();
            //make new node next points to prevNode
            n.setNext(nextNode);
            //make prevNode next points to new Node
            prevNode.setNext(n);
            //make nextNode previous points to new node
            nextNode.setPrev(n);
            //make  new Node previous points to prevNode
            n.setPrev(prevNode);
            length++;
            return n;
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
			length++;
		}
	}


    public void deleteFromStart(){
        if(length==0){
        	return;
        }else{
            first = first.getNext();
            length--;
        }
    }

    public void deleteFromEnd(){
        if(length==0){
        	return;
        }else if(length==1){
            deleteFromStart();
        }else{
            //store the 2nd last node
            int x = last.getData();
            Node prevLast = last.getPrev();

            //detach the last node
            last = prevLast;
            last.setNext(null);
            length--;
        }
    }
    
    public void delete(Node n) {
    	if( n == first ) {
    		deleteFromStart();
    	} else if( n == last ) {
    		deleteFromEnd();
    	} else {
    		n.getPrev().setNext(n.getNext());
    		n.getNext().setPrev(n.getPrev());
    		length--;
    	}
    }
    
	public String toString() {
		String res = "";
		Node curr = first;
		if(curr != null) {
			do {
				res += "<-"+ curr.getData() + "->";
				curr = curr.getNext();
			} while(curr != null);				
		}
		return res;
	}

    
    public boolean isEmpty() {
    	return length == 0;
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

	public int elementAt(int index){
        if(index>length){
            return -1;
        }
        Node n = first;
        while(index-1!=0){
            n= n.getNext();
            index--;
        }
        return n.getData();
    }

    //get Size
    public int getSize(){
        return length;
    }
}
