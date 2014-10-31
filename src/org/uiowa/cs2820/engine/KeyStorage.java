package org.uiowa.cs2820.engine;

import java.util.LinkedList;

public class KeyStorage {
	public static LinkedList<Node> storage = new LinkedList<Node>();
	
	public Node get(long areaNum){
		byte[] byteResult = DiskSpace.read(areaNum);
		Node n = Field.revert(byteResult);
		return n;
	}
	
	public void put(long areaNum, Node n){
		byte[] b = Field.convert(n);
		DiskSpace.write(areaNum, b);
	}
	
	public void add(Node n){
		
	}
	
	public void delete(Node n){
		
	}
}
