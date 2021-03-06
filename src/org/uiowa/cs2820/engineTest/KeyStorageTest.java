package org.uiowa.cs2820.engineTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.uiowa.cs2820.engine.*;

public class KeyStorageTest {

	@Test
	public void test() throws Exception {
		
		StartUp.init();
		
		Field f1 = new Field("country1", "China");
		Node a = new Node(f1);
		KeyStorage.add(a);
		
		Field f2 = new Field("country2", "US");
		Node b = new Node(f2);
		KeyStorage.add(b);
		
		//test getSize()
		assertEquals(KeyStorage.getSize(),2);
		
		//test add(Node n)
		assertTrue(KeyStorage.get(KeyStorage.head).getKey().getFieldName().equals("country1") );
		
		//test get() and put(), which are also part of add(Node n)
		assertTrue(KeyStorage.get(a.getAddr()).getKey().getFieldName().equals("country1"));
		
		//test delete(Node n)
		KeyStorage.delete(a);
		assertEquals(KeyStorage.getSize(),1);
		assertTrue(KeyStorage.get(KeyStorage.head).getKey().getFieldName().equals("country2"));
		
		//test clear()
		KeyStorage.clear();
		assertEquals(KeyStorage.getSize(),0);
		assertEquals(KeyStorage.head, -1);
		
	}

}
