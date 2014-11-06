package org.uiowa.cs2820.engine;

import java.io.IOException;

public class KeyStorage {
	
	public static long head;
	public static int size;
	public static long tail;
		
	public static void init(){
		head = tail = -1; 
		size = 0;
	}
	
	public static Node get(long areaNum) throws IOException{
		byte[] byteResult = DiskSpace.read(areaNum);
		Node n = (Node) Field.revert(byteResult);
		return n;
	}
	
	public static boolean put(long areaNum, Node n) throws Exception {
		try{
			byte[] b = Field.convert(n);
			DiskSpace.write(areaNum, b);
		}catch (Exception e) {
			System.out.println("Put Node Failed!");
			return false;
		}
		
		return true;
	}
	
	//add Node n to the end of the linked list and write into file
	public static boolean add(Node n) throws Exception { 
		n.next = -1;
		try{
			if(size == 0) {
				head = tail = Allocate.allocate();
				n.addr = head;
				put(head, n);	
			} else {
				n.addr = Allocate.allocate();
				Node last = get(tail);  //get the node from file to modify
				last.next = n.addr;
				put(tail, last);        //put the modified node back in file
				tail = n.addr;
				put(n.addr, n);
		      }
			size++;
		}
		catch(Exception e){
			System.out.println("Add Node Failed!");
			return false;
			}
		return true;
	}
	
	/*delete a node from file. Node can be the head, tail or in the middle
	may need modification, don't know if we need to check n is in file or not*/
	public static boolean delete(Node n) throws Exception{ 
		Node before = null;
		try{
			if(size == 0) return true;             //delete from empty list
			
			Node current = get(head);
			while(current.next != -1){
				if (current.key.equals(n.key)) break;
				before = current;
				current = get(current.next);				
			}
			if (current.addr == tail){
				if(!current.key.equals(n.key)) return false;    //Node n is not in the storage
			}
			
			/*found node n, re-link pointer*/
			if(current.addr == head) {
				head = get(current.next).addr;
			} else if(current.addr == tail){
				before.next = -1;
				tail = before.addr;
				put(before.addr, before);                     //put it back into data file
			}else {                                           
				Node next = get(current.next);
				before.next = next.addr;
				put(before.addr, before);
			}
			
			ValueStorage.clear(current.valueArea);  //clear all the ids associated with this key
			Allocate.free(current.addr);
			size--;
			
		} catch(Exception e){
			System.out.println("Delete Node Failed!");
			return false;
		}
		return true;
	}
	
	//clear all the keyStorage area
	public static void clear() throws IOException{
		if(size == 0) return;
		Node current = get(head);
		while(current.next != -1){
			Node temp = get(current.next);
			Allocate.free(current.addr);
			current = temp;
		}
		Allocate.free(current.addr);
		head = tail = -1;
		size = 0;
	}
	
	//toString() for debugging
	public static String listAll() throws IOException{
		String s = "";
		if(size == 0) s = "[]";
		else {
		    Node current = get(head);
			while(current.next != -1){
				s += current.toString() + '\n';
				current = get(current.next);
			}
			s += current.toString();
		}
		return s;
	}
}
