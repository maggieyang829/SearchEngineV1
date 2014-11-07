package org.uiowa.cs2820.engine;

import java.io.IOException;
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
	
	public static ArrayList<String> load(long start) throws IOException{
		ArrayList<String> Identifiers = new ArrayList<String>();
		byte[] fileName = DiskSpace.read(start);
		
		Areas myArea = (Areas) Field.revert(fileName);
		while(myArea.next != -1){
			byte[] name = myArea.myValue;
			String name1 = (String) Field.revert(name);
			Identifiers.add(name1);
			myArea = myArea.next;
		}
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
