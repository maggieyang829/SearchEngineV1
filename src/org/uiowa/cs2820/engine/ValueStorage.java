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
	
	public static Areas get(long areaNum) throws IOException{
		byte[] byteResult = DiskSpace.read(areaNum);
		Areas a = (Areas) Field.revert(byteResult);
		return a;
	}
	
	public static ArrayList<String> load(long start) throws IOException{
		ArrayList<String> Identifiers = new ArrayList<String>();
		Areas myArea = get(start);
		
		while(myArea.next != -1){
			byte[] name = myArea.myValue;
			String name1 = (String) Field.revert(name);
			Identifiers.add(name1);
			
			byte[] mybyte = DiskSpace.read(myArea.next);
			Areas nextArea = (Areas) Field.revert(mybyte);
			myArea = nextArea;
		}
		byte[] byteValue = myArea.myValue;
		String name2 = (String) Field.revert(byteValue);
		Identifiers.add(name2);
		
		return Identifiers;
	}
	//Takes ArrayList of identifiers
    public static void store(ArrayList<String> lst, long area) throws IOException{
    	byte[] byteArray = Field.convert(lst);
    	DiskSpace.write(area, byteArray);
    		
	}
    
    public static void clear(long startpoint) throws IOException{
    	if(size == 0) return;
		Areas current = get(head);
		while(current.next != -1){
			Areas temp = get(current.next);
			Allocate.free(current.addr);
			current = temp;
		}
		Allocate.free(current.addr);		
		head = tail = -1;
		size = 0;

    }
    
//    public static String listAll(long startpoint) throws IOException{
//		String s = "";
//		if(size == 0) s = "[]";
//		else {
//		    Areas current = get(head);
//			while(current.next != -1){
//				s += current. + '\n';
//				current = get(current.next);
//			}
//			s += current.key.getFieldName();
//		}
//		return s;
//	}

}
