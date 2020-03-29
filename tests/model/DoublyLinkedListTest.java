package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoublyLinkedListTest {

	private DoublyLinkedList dll; 
	
	public void emptyListSetup() {
		dll = new DoublyLinkedList(); 
	}
	
	public void nonEmptyListSetup() {
		dll = new DoublyLinkedList(); 
		dll.append('A');
		dll.append('B');
		dll.append('C');
		dll.append('D');
	}
	
	@Test
	public void prependTest() {
		nonEmptyListSetup();
		dll.prepend('A');
		dll.prepend('B');
		dll.prepend('C');
		dll.prepend('D');
		String expected = "<-D-><-C-><-B-><-A-><-A-><-B-><-C-><-D->";
		int expectedLength = 8;
		int actualLength = dll.length;
		String length_err_msg = "Length is " + actualLength + ", but should be " + expectedLength;
		String string_msg = "List should be " + expected + " but is " + dll.toString();
		
		assertTrue(length_err_msg, expectedLength == actualLength);
		assertTrue(string_msg, expected.equals(dll.toString()));
	}
	
	@Test
	public void appendTest() {
		nonEmptyListSetup();
		dll.append('A');
		dll.append('B');
		dll.append('C');
		dll.append('D');
		
		String expected = "<-A-><-B-><-C-><-D-><-A-><-B-><-C-><-D->";
		int expectedLength = 8;
		
		int actualLength = dll.length;
		
		String length_err_msg = "Length is " + actualLength + ", but should be " + expectedLength;
		String string_msg = "List should be " + expected + " but is " + dll.toString();
		
		assertTrue(length_err_msg, expectedLength == actualLength);
		assertTrue(string_msg, expected.equals(dll.toString()));
	}
	
	@Test
	public void deleteTest() {
		emptyListSetup();
		Node A = new Node('A');
		Node B = new Node('B');
		Node C = new Node('C');
		Node D = new Node('D');
		dll.append(A); dll.append(B); dll.append(C); dll.append(D); 
		dll.delete(B); dll.delete(A); dll.delete(D);
		String expected = "<-C->";
		String string_msg = "List should be " + expected + "but is " + dll.toString();
		
		int expected_length = 1;
		String length_msg = "Length must be " + expected_length + ", but is" + dll.length;
		assertTrue(string_msg, dll.toString().equals("<-C->"));
		assertTrue(length_msg, dll.length == expected_length);
	}
	
	@Test
	public void addAfterTest() {
		nonEmptyListSetup();
		Node E = new Node('E');
		dll.append(E);
		dll.append('G');
		dll.addAfter('F', E);
		String expected_str = "<-A-><-B-><-C-><-D-><-E-><-F-><-G->";
		String actual_str =  dll.toString();
		String error_msg = "List should be " + expected_str + " but is " + actual_str;
		assertTrue(error_msg,  actual_str.equals(expected_str) );
	}
	
	@Test
	public void addBeforeTest() {
		nonEmptyListSetup();
		Node G = new Node('G');
		dll.append('E'); 
		dll.append(G);
		dll.addBefore('F', G);
		String expected_str = "<-A-><-B-><-C-><-D-><-E-><-F-><-G->";
		String actual_str = dll.toString();
		String error_msg = "List should be " + expected_str + " but is " + actual_str;
		assertTrue(error_msg,  actual_str.equals(expected_str) );
	}
}
