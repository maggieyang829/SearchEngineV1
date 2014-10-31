package org.uiowa.cs2820.engine;

import java.util.LinkedList;

public class KeyStorage {
public static LinkedList<Node> storage;
	
	public KeyStorage(){}
	
	public void init(){
		storage = new LinkedList<Node>();
	}
	
	public Node get(long areaNum){
		byte[] byteResult = DiskSpace.read(areaNum);
		Node n = (Node) Field.revert(byteResult);
		return n;
	}
	
	public Boolean put(long areaNum, Node n){
		byte[] b = Field.convert(n);
		DiskSpace.write(areaNum, b);
		return true;
	}
	
	public Boolean add(Node n) throws Exception {
		n.next = null;
		long place = Allocate.allocate();
		n.addr = place;
		try{
			if(storage.add(n)) put(place, n);
		}catch(Exception e){
			System.out.println("Add Node Failed!");
			return false;
			}
		return true;
	}
	
	public Boolean delete(Node n) throws Exception{
		try{
			if(storage.remove(n)) Allocate.free(n.addr);
		} catch(Exception e){
			System.out.println("Delete Node Failed!");
			return false;
		}
		return true;
	}
}
