package org.uiowa.cs2820.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.io.*;

/*Node is the basic unit in the database. It consists of 4 parts: 
 * 1> key is the Field object that is to be searched on; 
 * 2> valueArea is a pointer indicating the starting address of the identifiers that Indexer class attaches
 *    on the Field object when doing indexing on the file. 
 * 3> next simply points to the next Node object in the database.
 * 4> address is a number returned by Allocate.allocate(), and it is the address of the Node in the database.
 *
 * Constructor: 
 * When the Node constructor gets called, it initiates the node Field object, and allocate a number for its
 * first identifier. But the next and address is simply set to null. These attributes will be set later in
 * other method.
 * 
 * Methods:
 * 1> getIdentifier(long start): This is a getter that uses ValueStorage.load(), and returns an ArrayList 
 *    of identifiers attached with the node.
 * 2> getKey() is a getter that returns the Field object in the node.
 * 3> getAddr() is also a getter that returns the chunk number where the node is.
 * 4> add(String id) adds a new identifier to the current set of identifiers associating with the node.
 *    It uses ValueStorage.load() and ValueStorage.store() methods.
 * 5> delete(String id) deletes a specific id from the current set of identifiers.
 *    It uses ValueStorage.load() and ValueStorage.store() methods.*/

public class Node implements Serializable {

	private static final long serialVersionUID = 1L;
	// Node is a basic unit in the database
	  Field key;       // Key of this node for lookup
	  long valueArea;  //starting point of the first identifier
	  long next;      //next Node address
	  long addr;      //address of the node in the disk space (file)
	  ValueStorage vs;

	public Node(Field f) {
		this.key = f;
		this.valueArea = -1;
		this.next = -1;
		this.addr = Allocate.allocate();
	}
	
	public void setValueArea(long addr){
		this.valueArea = addr;
	}
	
	public ArrayList<String> getIdentifiers() throws IOException{
		if (valueArea == -1) return null;
		return vs.load(valueArea);                
	  }
	  
	  public Field getKey(){
		  return key;
	  }
	  
	  public long getAddr(){
		  return addr;
	  }

	  public boolean add(String id) throws Exception {
		  if(vs == null){
			  vs = new ValueStorage(this);		  
		  }
		  return vs.add(this,id); 
	  }
	   
	  public void delete(String id) throws IOException {
		  
	  }

  }