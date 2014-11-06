package org.uiowa.cs2820.engine;

import java.util.ArrayList;

public class ValueStorage {
	public static long head;
	public static int size;
	public static long tail;

	
	public static void init(){
		head = -1;
		size = 0;
		tail = -1;
	}
	
	public static ArrayList<String> load(long start){
		ArrayList<String> Identifiers = new ArrayList<String>();
		byte[] fileName = start.myValue;
		String fileName = (String)Field.revert(fileName);
		Identifiers.add(fileName);
		return Identifiers;
	}
	
    public static void store(ArrayList<String> lst){
		
	}
    
    public static void clear(long startpoint){
    	
    }
    
    public static String listAll(long startpoint){
    	return "";
    }
}
