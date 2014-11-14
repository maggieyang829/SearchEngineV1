package org.uiowa.cs2820.engine;

import java.io.IOException;

/*KeyStorage is one of the essential part of the database. It is composed of a singly linked list of
 *Node objects in the database file. In this class, head and tail keep track of the start and the end
 *of the list, and size indicates how many node objects are there in the linked list. Whenever user 
 *do indexing on a file, the indexer class will put a new Field into the KeyStorage in the form of a 
 *node. When the user wants to delete a Field, the node associated with the Field will be deleted from
 *the keyStorage.
 *
 *Constructor:
 *This class only has one unique instance and has only static methods, so it's not necessary to build 
 *a constructor.
 *
 *Methods:
 *1> init(): this method initiates the head, tail and the size of the key storage. Because there is no data
 *   in the key storage yet, the head and tail point to null area and the size is zero.
 *2> getSize(): it returns the size of the keystorage, that is the number of nodes.
 *3> Node get(long areaNum): it uses DiskSpace.read() to read from a specific chunk in the database (file)
 *   and uses Field.revert to return a Node object.
 *4> put(long areaNum, Node n): this method also uses DiskSpace to write a node in a specific area in the
 *   database file.
 *5> add(Node n): it adds a node into the current linked list in the key storage. Head and tail pointers are
 *   adjusted accordingly. If succeed, it returns true, false otherwise.
 *6> delete(Node): it delete a node from the current linked list in the key storage. Head and tail pointers 
 *   are adjusted accordingly. If succeed, it returns true, false otherwise.
 *7> clear(): this method clear all the node objects in the keystorage, so the size will be zero.
 *8> listAll(): this is only used for debugging convenience.*/

public class KeyStorage {
	
	public static long head;
	private static int size;
	public static long tail;
		
	public static void init(){
		head = tail = -1; 
		size = 0;
	}
	
	public static int getSize(){
		return size;
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
				head = tail = Allocate.allocate();      //init a chunk number to store the first node
				n.addr = head;
				put(head, n);	
			} else {
				n.addr = Allocate.allocate();
				Node last = get(tail);                 //get the tail node from file to modify
				last.next = n.addr;
				put(tail, last);                       //put the modified node back in file
				tail = n.addr;
				put(n.addr, n);
		      }
			size++;
		}
		catch(Exception e){
			System.out.println("Add Node Failed!");
			e.printStackTrace();
			return false;
			}
		return true;
	}
	
	//delete a node from storage, Node n may not be in the list.
	public static boolean delete(Node n) throws Exception{ 
		Node before = null;
		try{
			if(size == 0) return true;                         //delete from empty list
			
			Node current = get(head);
			while(current.next != -1){
				if (current.key.getFieldName().equals(n.key.getFieldName())) break;
				before = current;
				current = get(current.next);				
			}
			
			if (current.addr == tail){
				if(!current.key.getFieldName().equals(n.key.getFieldName())) return false;    
				//Node n is not in the storage
			}
			
			//found node n, re-link pointer
			if(current.addr == head) {						  //delete the head
				if (size > 1) { head = get(current.next).addr;}	
				
			} else if(current.addr == tail){	              //delete the tail
				before.next = -1;
				tail = before.addr;
				put(before.addr, before);                     
			}else {                                           //delete node in the middle		
				Node next = get(current.next);
				before.next = next.addr;
				put(before.addr, before);
			}
			
			ValueStorage.clear(current.valueArea);           //clear all the ids associated with this key
			Allocate.free(current.addr);
			size--;
			
			if(size == 0){								     //if delete the only node, reset list
				head = tail = -1;
			}
			
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
			ValueStorage.clear(current.valueArea);
			Allocate.free(current.addr);
			current = temp;
		}
		
		ValueStorage.clear(current.valueArea);
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
				s += current.key.getFieldName() + '\n';
				current = get(current.next);
			}
			s += current.key.getFieldName();
		}
		return s;
	}
}
