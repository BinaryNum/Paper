package com.javalec.paper.command;

import java.util.ArrayList;
import java.util.Collections;


public class test {
public static void main(String[] args) {
	ArrayList<Integer> ranNumber = new ArrayList<Integer>();
	for(int i=0; i <4;i++){
	ranNumber.add(i);
	}
	
	Collections.shuffle(ranNumber);

	System.out.println(ranNumber);


	
}
}
