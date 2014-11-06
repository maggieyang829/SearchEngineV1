package org.uiowa.cs2820.engine;


public class Areas {
	//ArrayList<String> Identifiers;
	int size;
	byte[] myValue;
	long next;
	long prev;
	long addr;
	
	Areas(Node n){
		this.size = 0;
		this.next = -1;
		this.prev = -1;
	}
}
