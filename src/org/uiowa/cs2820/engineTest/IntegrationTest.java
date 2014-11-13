package org.uiowa.cs2820.engineTest;

import static org.junit.Assert.*;

import org.uiowa.cs2820.engine.*;
import org.junit.Test;

/* The following code borrows from Professor Herman
 * This is the main test for the Search Engine project. It uses Field, Indexer and FieldSearch classes to
 * simulate the user interface methods to communicate with other classes in the package*/

public class IntegrationTest {

	 @Test
	 public void test() {
		 
	     CheckPoint.reset(); // I made these two reset() methods to erase old contents of file
	     DiskSpace.reset();
	     
	     Field f = new Field("citizen",true);
	     Indexer I = new Indexer("first.db");
	     I.addField(f);
	     Field g = new Field("two",2);
	     I.addField(g);
	     I = new Indexer("second.db");
	     I.addField(f);
	     I.addField(new Field("three",3));
	     FieldSearch F = new FieldSearch(f);
	     String[] S = F.findEquals();
	     assertEquals(S.length,2);
	     assertEquals(S[0],"second.db");
	     assertEquals(S[1],"first.db");
	     I.remove();  // students do not need to do this part
	     S = F.findEquals();
	     assertEquals(S.length,1);
	     }

}
