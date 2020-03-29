package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TuringMachineTest {

	private TuringMachine tm;
	
	public void emptySetup() {
		this.tm = new TuringMachine();
	}
	
	public void oddNodesSetup(int n) {
		this.tm = new TuringMachine();
		if( n % 2 != 0 ) {
			for(int i = 0; i < n; i++) {
				char head = String.valueOf(Math.floor(Math.random() * 3)).charAt(0);
				char letter = (char) (i + 65);
				tm.add( head, letter );
			}					
		} else throw new IllegalArgumentException(n + " is not odd!");
	}
	
	public void evenNodesSetup(int n) {
		this.tm = new TuringMachine();
		if( n % 2 == 0 ) {
			for(int i = 0; i < n; i++) {
				char head = String.valueOf(Math.floor(Math.random() * 3)).charAt(0);
				char letter = (char) (i + 65);
				tm.add( head, letter );
			}					
		} else throw new IllegalArgumentException(n + " is not even!");
	}
	
	@Test
	public void addEmptyTest() {
		emptySetup();
		tm.add('0', 'A');
		Node expected = tm.getDll().getFirst();
		Node actual_c0 = tm.getC0().getNext();
		Node actual_c1 = tm.getC1().getNext();
		Node actual_c2 = tm.getC2().getNext();
		
		assertTrue("C0 should be pointing to the first node",  expected.equals(actual_c0));
		assertTrue("C1 should be pointing to the first node", expected.equals(actual_c1));
		assertTrue("C2 should be pointing to the first node", expected.equals(actual_c2) );
	}
		
	@Test
	public void addEvenTestWith2() {
		evenNodesSetup(2);
		assertTrue("C0 should be pointing to the first node", tm.getC0().getNext().equals(tm.getDll().getFirst()));
		assertTrue("C1 should be pointing to the first node", tm.getC1().getNext().equals(tm.getDll().getFirst()));
		assertTrue("C2 should be pointing to the last node", tm.getC2().getNext().equals(tm.getDll().getLast()));
	}
	
	@Test
	public void addOddTestWith3() {
		oddNodesSetup(3);
		Node first = tm.getDll().getFirst();
		Node middle = first.getNext();
		Node last = tm.getDll().getLast();
		assertTrue("C0 should be pointing to the first node", tm.getC0().getNext().equals( first ));
		assertTrue("C1 should be pointing to the middle node", tm.getC1().getNext().equals( middle ));
		assertTrue("C2 should be pointing to the last node", tm.getC2().getNext().equals( last ));
	}

	@Test
	public void addEvenTestWith4() {
		evenNodesSetup(4);
		Node first = tm.getDll().getFirst();
		Node middle = first.getNext();
		Node last = tm.getDll().getLast();
		assertTrue("C0 should be pointing to the first node", tm.getC0().getNext().equals( first ));
		assertTrue("C1 should be pointing to the middle node", tm.getC1().getNext().equals( middle ));
		assertTrue("C2 should be pointing to the last node", tm.getC2().getNext().equals( last ));
	}
	
	@Test
	public void addOddWith5() {
		oddNodesSetup(5);
		Node first = tm.getDll().getFirst();
		Node middle = first.getNext().getNext();
		Node last = tm.getDll().getLast();
		assertTrue("C0 should be pointing to the first node", tm.getC0().getNext().equals( first ));
		assertTrue("C1 should be pointing to the middle node", tm.getC1().getNext().equals( middle ));
		assertTrue("C2 should be pointing to the last node", tm.getC2().getNext().equals( last ));		
	}
	
//	Base case
	@Test
	public void deleteBaseCaseTest() {
		emptySetup();
		tm.delete('0');
		assertTrue("0", tm.getC0().getNext() == null &&  tm.getC1().getNext() == null && tm.getC2().getNext() == null );
		tm.delete('1');
		assertTrue("", tm.getC0().getNext() == null &&  tm.getC1().getNext() == null && tm.getC2().getNext() == null );
		tm.delete('2');
		assertTrue("", tm.getC0().getNext() == null &&  tm.getC1().getNext() == null && tm.getC2().getNext() == null );
	}
	
	@Test
	public void deleteWith2ElementsTest() {
		char[] heads = {'0', '1', '2'};
			for(char c : heads) {
				System.out.println("Deleting on head: " + c);
				evenNodesSetup(2);
				System.out.println("Before");
				System.out.println(tm);
				tm.delete(c);
				System.out.println("After");
				System.out.println(tm);
				System.out.println();
				Node expected = tm.getDll().getFirst();
				Node actual_c0 = tm.getC0().getNext();
				Node actual_c1 = tm.getC1().getNext();
				Node actual_c2 = tm.getC2().getNext();
				
				assertTrue("C0 should be pointing to the first node",  expected.equals(actual_c0));
				assertTrue("C1 should be pointing to the first node", expected.equals(actual_c1));
				assertTrue("C2 should be pointing to the first node", expected.equals(actual_c2) );
		}			
	}
	
	@Test
	public void deleteWith3ElementsTest() {
		char[] heads = {'0', '1', '2'};
		for(char c : heads) {
			System.out.println("Deleting on head: " + c);
			System.out.println("Before");
			oddNodesSetup(3);
			System.out.println(tm);
			tm.delete(c);
			System.out.println("After");
			System.out.println(tm);
			System.out.println();
			Node first = tm.getDll().getFirst();
			Node last = tm.getDll().getLast();
			Node actual_c0 = tm.getC0().getNext();
			Node actual_c1 = tm.getC1().getNext();
			Node actual_c2 = tm.getC2().getNext();
			assertTrue("C0 should be pointing to the first node",  first.equals(actual_c0));
			assertTrue("C1 should be pointing to the first node", first.equals(actual_c1));
			assertTrue("C2 should be pointing to the last node", last.equals(actual_c2) );
		}			
	}
	
	@Test
	public void deleteWith4ElementsTest() {
		char[] heads = {'0', '1', '2'};
			for(char c : heads) {
				System.out.println("Deleting on head: " + c);
				evenNodesSetup(4);
				System.out.println("Before");
				System.out.println(tm);
				tm.delete(c);
				System.out.println("After");
				System.out.println(tm);
				System.out.println();
				Node expected_c0 = tm.getDll().getFirst();
				Node expected_c1 = tm.getDll().getFirst().getNext();
				Node expected_c2 = tm.getDll().getLast();
				Node actual_c0 = tm.getC0().getNext();
				Node actual_c1 = tm.getC1().getNext();
				Node actual_c2 = tm.getC2().getNext();
				
				assertTrue("C0 should be pointing to the first node",  expected_c0.equals(actual_c0));
				assertTrue("C1 should be pointing to the first node", expected_c1.equals(actual_c1));
				assertTrue("C2 should be pointing to the first node", expected_c2.equals(actual_c2) );
		}			
	}
	
	@Test
	public void deleteWith5ElementsTest() {
		char[] heads = {'0', '1', '2'};
		for(char c : heads) {
			System.out.println("Deleting on head: " + c);
			System.out.println("Before");
			oddNodesSetup(5);
			System.out.println(tm);
			tm.delete(c);
			System.out.println("After");
			System.out.println(tm);
			System.out.println();
			Node first = tm.getDll().getFirst();
			Node last = tm.getDll().getLast();
			Node mid = first.getNext();
			Node actual_c0 = tm.getC0().getNext();
			Node actual_c1 = tm.getC1().getNext();
			Node actual_c2 = tm.getC2().getNext();
			assertTrue("C0 should be pointing to the first node",  first.equals(actual_c0));
			assertTrue("C1 should be pointing to the middle node", mid.equals(actual_c1));
			assertTrue("C2 should be pointing to the last node", last.equals(actual_c2) );
		}			
	}

}
