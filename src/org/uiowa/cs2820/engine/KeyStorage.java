package org.uiowa.cs2820.engine;

public class KeyStorage {
	
	public static long start;
	public static int size;
	public static Node head;
	public static Node tail;
		
	public static void init(){
		start = Allocate.allocate(); //return the address for the first node in the list
		size = 0;
		head = null;
		tail = head;
	}
	
	public Node get(long areaNum){
		byte[] byteResult = DiskSpace.read(areaNum);
		Node n = (Node) Field.revert(byteResult);
		return n;
	}
	
	public Boolean put(long areaNum, Node n) throws Exception {
		try{
			byte[] b = Field.convert(n);
			DiskSpace.write(areaNum, b);
		}catch (Exception e) {
			System.out.println("Put Node Failed!");
			return false;
		}
		
		return true;
	}
	
	public Boolean add(Node n) throws Exception {
		n.prev = null;
		n.next = null;	
		try{
			if(size == 0) {
				put(start, n);
				head = tail = n;
			} else {
				n.addr = Allocate.allocate();
				put(n.addr, n);
				n.prev = tail;
				tail.next = n;
				tail = n;
		      }
			size++;
		}
		catch(Exception e){
			System.out.println("Add Node Failed!");
			return false;
			}
		return true;
	}
	
	public Boolean delete(Node n) throws Exception{
		try{
			n.prev.next = n.next;
			n.next.prev = n.prev;
			Allocate.free(n.addr);
			size--;
		} catch(Exception e){
			System.out.println("Delete Node Failed!");
			return false;
		}
		return true;
	}
}
