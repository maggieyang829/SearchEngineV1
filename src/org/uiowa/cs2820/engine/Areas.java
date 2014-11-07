package org.uiowa.cs2820.engine;

public class Areas {
	int size;
	byte[] myValue;
	long next;
	long addr;
	
	Areas(int s, long next1, byte[] b){
		this.size = s;
		this.next = next1;
		this.myValue = b;
		this.addr = Allocate.allocate();
	}
}
