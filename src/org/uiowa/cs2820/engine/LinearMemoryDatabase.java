package org.uiowa.cs2820.engine;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class LinearMemoryDatabase implements Database {
  public static File datastorage;
  
  public static void init(){
	  datastorage = new File("dataspace.txt");                    
		try {
			datastorage.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
  
  /*the code below needs refactoring*/
  public Node fetch(byte[] key) {
	for (Node e: Memory)
	  if (Arrays.equals(e.Key,key)) return e;
	return null;
    }
  
  public void store(byte[] key, String id) {
	for (Node e: Memory) 
	  if (Arrays.equals(e.Key,key)) {
		e.add(id);
		return;
	    }
    Node p = new Node(key,id);
    Memory.add(p);
    }
  
  public void delete(byte[] key, String id) {
	for (Node e: Memory)
	  if (Arrays.equals(e.Key, key)) {
		e.Identifiers.remove(id);
		return;
	    }
    }
  }