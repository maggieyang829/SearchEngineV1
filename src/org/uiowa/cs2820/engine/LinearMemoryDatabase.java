package org.uiowa.cs2820.engine;

import java.io.File;
import java.io.IOException;

public class LinearMemoryDatabase implements Database {
  private static File dataStorage;
  
  public LinearMemoryDatabase(){}
  
  public static void init(){
	  dataStorage = new File("dataspace.txt");                    
		try {
			dataStorage.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
  
  public static File getDataStorage() {
      return dataStorage;
  }
  
  public Node fetch(Field key) throws IOException {
	  
	Node current = KeyStorage.get(KeyStorage.head);
	while(current.next != -1){
		if (current.key.equals(key)) return current;
		current = KeyStorage.get(current.next);				
	}
	if (current.key.equals(key)){
		return current;
	} else return null;

  }
  
  public void store(Field key, String id)  {
	try {
	    Node n = fetch(key);
		if(n != null){                                      //when the node is in the keystorage
		   n.add(id);
	    } else {
	    	Node newNode = new Node(key);						//when node is not in keystorage, create new one
	    	newNode.add(id);
	    	KeyStorage.add(n);
	      }	
	} catch (Exception e) {
		System.out.println("store key and id failed");
		e.printStackTrace();
	}
}
  
  
  public void delete(Field key, String id) {
	try {
	  Node n = fetch(key);
	  if (n != null) n.delete(id);
	} catch (Exception e){
		System.out.println("delete key and id failed");
		e.printStackTrace();
	}
  }

}