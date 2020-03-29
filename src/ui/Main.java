package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import model.TuringMachine;

public class Main {

	public static String INPUT_PATH = "/home/sebastian/Downloads/in_turing.txt";
	public static String OUTPUT_PATH = "data/ans_turing.txt";
	
	public String INPUT_DEBUG_MODE_PATH = "data/in_debug.txt";
	public String OUTPUT_DEBUG_MODE_PATH = "data/out_debug.txt";
	public String LOGS_DEBUG_MODE_PATH = "data/logs_debug.txt";

	public static void main(String[] args) throws IOException {
		TuringMachine tm = new TuringMachine();
		BufferedReader br = new BufferedReader( new FileReader( new File( INPUT_PATH )));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File( OUTPUT_PATH )));
		String line;
		long start = System.currentTimeMillis();
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
						bw.write( tm.getHeadString(head) + "\n" );	
					}
					i+=2;
				}			
			}
			tm.reset();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		bw.close();
		br.close();
	}
	
	public void readDebug( String input_path, String output_path, int debug_mode) throws IOException {
		TuringMachine tm = new TuringMachine();
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
