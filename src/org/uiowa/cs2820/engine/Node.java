package org.uiowa.cs2820.engine;

import java.util.ArrayList;

public class Node {
  // Node is a basic unit in the database
  byte[] Key;  // Key of this node for lookup
  long valueArea; //starting point of the first identifier
  Node next;
  
  Node(byte[] f, String id) {
	this.Key = f;
	this.valueArea = Allocate.allocate();
	this.next = null;
    }
  
  public void add(String id) { 
	Identifiers.remove(id);  // does nothing if id not already there
	Identifiers.add(id);
    }
  
  public void del(String id) {
	Identifiers.remove(id);
    }
  
  }