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
 *    It uses ValueStorage.load() and ValueStorage.store() methods.
 * 6> toString() is only written for debugging purpose.*/

public class Node implements Serializable {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Node is a basic unit in the database
	  Field key;       // Key of this node for lookup
	  long valueArea;  //starting point of the first identifier
	  long next;      //next Node address
	  long addr;      //address of the node in the disk space (file)

	public Node(Field f) {
		this.key = f;
		this.valueArea = -1;  //return the address of free space for the first value
		this.next = -1;
		this.addr = -1;
	}

	public ArrayList<String> getIdentifiers(long start) throws IOException{
			return ValueStorage.load(start);                
	  }
	  
	  public Field getKey(){
		  return key;
	  }
	  
	  public long getAddr(){
		  return addr;
	  }
	  
	  public void add(String id) throws IOException {	
		  ArrayList<String> idList;
		  if(valueArea == -1){
			  idList = new ArrayList<String>();
			  idList.add(id);
			  valueArea = Allocate.allocate();
		  }else{
		  idList = ValueStorage.load(valueArea);
		  idList.add(id);
		  }
		  		 
		  ValueStorage.store(idList, valueArea);
		  
	  }
	  
	  public void delete(String id) throws IOException {
		  ArrayList<String> idList = ValueStorage.load(valueArea);
		  idList.remove(id);
		  ValueStorage.store(idList);
	  }
  

  }