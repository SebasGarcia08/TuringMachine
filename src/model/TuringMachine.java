package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class TuringMachine {
	
	public String INPUT_PATH = "data/inputs.txt";
	public String OUTPUT_PATH = "data/outputs.txt";
	
	public String INPUT_DEBUG_MODE_PATH = "data/in_debug.txt";
	public String OUTPUT_DEBUG_MODE_PATH = "data/out_debug.txt";
	public String LOGS_DEBUG_MODE_PATH = "data/logs_debug.txt";
	
	private DoublyLinkedList dll;
	private Node C0;
	private Node C1;
	private Node C2;
	
	public TuringMachine() {
		this.dll = new DoublyLinkedList(); 
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
		if(dll.isEmpty()) {
			return;
		} else {
			if( head == '0' ) {
				dll.delete( heads.get('0') );
				heads.put('0', dll.getFirst());
				if(head == '1' || dll.length % 2 == 0) 
					moveC1ToRight();
			} else if( head == '2' ) {
				dll.delete(heads.get('2'));
				heads.put('2', dll.getLast());
				if( head == '1' || dll.length % 2 != 0)
					moveC1ToLeft();
			} else {
				if( dll.length % 2 == 0)  
					moveC1ToRight();
				 else  
					 moveC1ToLeft();							
			}
		}
	}
	
	public void deleteC0() {
		dll.delete(C0.getNext());
		C0.setNext(dll.getFirst());
		if( dll.length % 2 == 0 )
			moveC1ToRight();
	}
	
	public void deleteC1() {
		if( dll.length % 2 == 0 ) moveC1ToRight();
		else moveC1ToLeft();
	}
	
	public void deleteC2() {
		dll.delete(C2.getNext());
		C2.setNext(dll.getLast());
		if( dll.length % 2 != 0)
			moveC1ToLeft();
	}	
	
	public void reset() {
		this.dll = new DoublyLinkedList(); 
		this.C0 = new Node('0');
		this.C1 = new Node('1');
		this.C2 = new Node('2');
	}
	
	public void add(char head, char letter) {
		if(dll.isEmpty()) {
			dll.prepend(letter);
			heads.forEach((id, cx) -> {
				cx.setNext(dll.getFirst());
			});
		} else {
			if(head == '0') {
				dll.prepend(letter);
				heads.get('0').setNext(dll.getFirst());
				if(dll.length % 2 != 0) {
					moveC1ToLeft();
				}
			}else if( head == '2' ){
				dll.append(letter);
				heads.get('2').setNext( dll.getLast());
				if( dll.length % 2 == 0 ) {
					moveC1ToRight();
				}
			} else {
				if( dll.length % 2 == 0 ) {
					dll.addAfter(letter, heads.get('1').getNext());
					moveC1ToRight();
				} else {
					dll.addAfter(letter, heads.get('1').getNext());
				}			
			}
		}
	}
	
	public void moveC1ToRight() {
		if( C1 != dll.getLast() )
			C1.setNext(C1.getNext().getNext());
	}
	
	public void moveC1ToLeft(){
		if( C1 != dll.getFirst() )
			C1.setNext(C1.getNext().getPrev());
	}
	
	/*	
	public void runInDebugMode() throws IOException{
		BufferedReader br = new BufferedReader( new FileReader( new File( INPUT_DEBUG_MODE_PATH )));
		BufferedWriter output_bw = new BufferedWriter(new FileWriter(new File( OUTPUT_DEBUG_MODE_PATH )));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File( LOGS_DEBUG_MODE_PATH )));
		String line;
		while((line = br.readLine()) != null) {
			final String chars = line;
			int i = 0;
			while( i < chars.length()) {
				char head = chars.charAt(i);
				char op = chars.charAt(i+1);
				char letter = ' ';
				if( op == '1' ) {
					letter = chars.charAt(i+2);
					add(head, letter);
					i += 3;
				} else {
					if( op == '2' ) {
						delete(head);
					}else {
						bw.write("=>" + heads.get(head).getNext() + "\n" );
						output_bw.write(heads.get(head).getNext() + "\n");
					}
					i+=2;
				}
// 				Instruction
				bw.write(String.valueOf(head) + String.valueOf(op) + String.valueOf(letter) + "\n");
//				Heads state
				heads.forEach((k,v) -> {
					try {
						bw.write( "C"+k + ": " + v.getNext() + "\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
//				List
				bw.write(dll.toString() + "\n");
				bw.write("Length: " + dll.length+"\n");
				bw.write("First: "+dll.getFirst().getData() +"\n");
				bw.write("Last: "+dll.getLast().getData()+"\n");
				bw.write("\n");
			}
			reset();
		}
		bw.close();
		br.close();
		output_bw.close();
	}
*/

}
