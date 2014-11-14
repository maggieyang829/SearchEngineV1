package org.uiowa.cs2820.engine;

public class Areas {
	int size;
	byte[] myValue;
	long next;
	long addr;
	
	Areas(long next1, byte[] b){
		this.size = b.length;
		this.next = next1;
		this.myValue = b;
		this.addr = Allocate.allocate();
	}
}
