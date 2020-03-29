package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.TuringMachine;

public class Main {

	public String INPUT_PATH = "data/inputs.txt";
	public String OUTPUT_PATH = "data/outputs.txt";
	
	public String INPUT_DEBUG_MODE_PATH = "data/in_debug.txt";
	public String OUTPUT_DEBUG_MODE_PATH = "data/out_debug.txt";
	public String LOGS_DEBUG_MODE_PATH = "data/logs_debug.txt";

	private TuringMachine tm;

	public Main() {
		
	}
	
	public static void main(String[] args) {
		
	}
	
	public void run() throws IOException {
		BufferedReader br = new BufferedReader( new FileReader( new File( INPUT_PATH )));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File( OUTPUT_PATH )));
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
					tm.add(head, letter);
					i += 3;
				} else {
					if( op == '2' ) {
						tm.delete(head);
					}else {
//						bw.write( heads.get(head).getNext() + "\n" );	
					}
					i+=2;
				}
			}
			tm.reset();
		}
		bw.close();
		br.close();
	}
	
	public void readDebug( String input_path, String output_path,  int debug_mode) throws IOException {
		BufferedReader br = new BufferedReader( new FileReader( new File( input_path )));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File( output_path )));
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
					if(debug_mode == 2)
						tm.add(head, letter);
					i += 3;
				} else if(op == '2') {
						if(debug_mode == 2)
							tm.delete(head);
						i+=2;
				} else {
					if(debug_mode == 1) {
						//TODO write method
					} else {
						//TODO debug mode 2 method pending
					}
					i+=2;
				}
				String res = String.valueOf(head) + String.valueOf(op) + String.valueOf(letter) + "\n";
				bw.write(res);	
			}
			if(debug_mode == 0) 
				tm.reset();				
		}
		bw.close();
		br.close();
	}
}
