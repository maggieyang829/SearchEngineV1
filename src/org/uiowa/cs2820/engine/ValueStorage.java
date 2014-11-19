package org.uiowa.cs2820.engine;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class ValueStorage implements Serializable{

	private static final long serialVersionUID = 1L;
	public long head;
	public long tail;

	
	public ValueStorage(Node n){
		tail = head = -1;
	}

	public Areas get(long areaNum) throws IOException{
		byte[] byteResult = DiskSpace.read(areaNum);
		Areas a = (Areas) Field.revert(byteResult);
		return a;
	}
	
	public boolean put(long areaNum, Areas a) throws Exception {
		try{
			byte[] b = Field.convert(a);
			DiskSpace.write(areaNum, b);
		}catch (Exception e) {
			System.out.println("Put Areas Failed!");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public ArrayList<String> load(long start) throws IOException{
		if(start == -1) return null;
		
		ArrayList<String> Identifiers = new ArrayList<String>();
		Areas myArea = get(start);
		
		while(myArea.next != -1){
			byte[] name = myArea.myValue;
			String name1 = (String) Field.revert(name);
			Identifiers.add(name1);		
			myArea = get(myArea.next);
		}
		
		byte[] byteValue = myArea.myValue;
		String name2 = (String) Field.revert(byteValue);
		Identifiers.add(name2);
		
		return Identifiers;
	}
    
    public boolean add(Node n, String id) throws Exception{
    	byte[] b = Field.convert(id);
    	Areas a = new Areas(b);
    	
    	if (n.valueArea == -1){
    		
    		a.addr = Allocate.allocate();
    		tail = head = a.addr;
    		n.setValueArea(head);
    		KeyStorage.put(n.addr, n);
    		put(a.addr, a);
    		
    	} else {
    
    		Areas last = get(tail);
    		a.addr = Allocate.allocate();
    		last.next = a.addr;
    		put(a.addr, a);
    		put(tail,last);
    		tail = a.addr;
    	}

    	return true;
    }

    
    public void clear(long startpoint) throws IOException{
    	if(head == -1) return;
		Areas current = get(head);
		while(current.next != -1){
			Areas temp = get(current.next);
			Allocate.free(current.addr);
			current = temp;
		}
		Allocate.free(current.addr);		
		head = tail = -1;

    }
    
    
    public boolean delete(String id){
    	
    	return true;
    }
    

}
