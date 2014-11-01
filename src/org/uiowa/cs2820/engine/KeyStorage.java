package org.uiowa.cs2820.engine;

public class KeyStorage {
	
	public static long head;
	public static int size;
	public static long tail;
		
	public static void init(){
		head = tail = -1; 
		size = 0;
	}
	
	public static Node get(long areaNum){
		byte[] byteResult = DiskSpace.read(areaNum);
		Node n = (Node) Field.revert(byteResult);
		return n;
	}
	
	public static Boolean put(long areaNum, Node n) throws Exception {
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
	public static Boolean add(Node n) throws Exception { 
		n.prev = -1;
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
				n.prev = last.addr;
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
	public static Boolean delete(Node n) throws Exception{ 
		try{
			if(n.addr == head) head = tail = -1;   //n is the only node. list becomes empty
			else if(n.addr == tail){               //if n is the tail
				Node before = get(n.prev);
				before.next = -1;                  //set the previous node to be tail
				tail = before.addr;
				put(tail, before);
			} else {							   //n is in the middle, re-link is done
					Node before = get(n.prev);     
					Node after = get(n.next);
					before.next = after.addr;
					after.prev = before.addr;
					put(before.addr, before);
					put(after.addr, after);
			}
			ValueStorage.clear(n.valueArea);  //clear all the ids associated with this key
			Allocate.free(n.addr);
			size--;
		} catch(Exception e){
			System.out.println("Delete Node Failed!");
			return false;
		}
		return true;
	}
	
	//clear all the keyStorage area
	public static void clear(){
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
	public static String listAll(){
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
