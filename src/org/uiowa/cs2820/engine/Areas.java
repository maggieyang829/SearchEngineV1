package org.uiowa.cs2820.engine;

import java.io.Serializable;

public class Areas implements Serializable {

	private static final long serialVersionUID = 1L;
	int size;
	byte[] myValue;
	long next;
	long addr;
	
	Areas(byte[] b){
		this.size = b.length;
		this.next = -1;
		this.myValue = b;
		this.addr = -1;
	}
}
