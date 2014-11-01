package org.uiowa.cs2820.engine;

import java.util.ArrayList;

public class Node {
	  // Node is a basic unit in the database
	  Field key;  // Key of this node for lookup
	  long valueArea; //starting point of the first identifier
	  long next;
	  long prev;
	  long addr;  //address of the node in the disk space (file)
	  
	  Node(Field f) {
		this.key = f;
		this.valueArea = Allocate.allocate();  //return the address of free space for the first value
		this.next = -1;
		this.prev = -1;
		this.addr = Allocate.allocate(); //return the address for this node
	  }
	  
	  public ArrayList<String> getIdentifiers(long start){
		  return ValueStorage.load(start);
	  }
	  
	  public Field getKey(){
		  return key;
	  }
	  
	  public void add(String id) {		  
		  ArrayList<String> idList = ValueStorage.load(valueArea);
		  idList.add(id);
		  ValueStorage.store(idList);
	  }
	  
	  public void delete(String id) {
		  ArrayList<String> idList = ValueStorage.load(valueArea);
		  idList.remove(id);
		  ValueStorage.store(idList);
	  }
  
  }