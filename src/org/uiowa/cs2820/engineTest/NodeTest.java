package org.uiowa.cs2820.engineTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.uiowa.cs2820.engine.*;
import org.junit.Test;

public class NodeTest {

/*The getters are too obvious to have a test on. 
 *add(), delete() rely heavily on the ValueStorage.load and store methods. Doing the test for these two
 *methods are like testing on the ValueStorage load and store methods*/
	@Test
	public void test() throws Exception {
	StartUp.init();
	Field f1 = new Field("country1", "China");
	Field f2 = new Field("country2", "US");
	
	Node n = new Node(f1);
	n.add("beijing");	
	ArrayList<String> lst1 = n.getIdentifiers();
	
	Node p = new Node(f2);
	
	p.add("gaoba");
	n.add("luzhou");
	
	ArrayList<String> lst3 = n.getIdentifiers();
	
	assertEquals(lst1.size(),1);
	assertEquals(lst3.size(),2);
	assertTrue(lst3.get(0).equals("beijing"));
	assertTrue(lst3.get(1).equals("luzhou"));		
    }
}
