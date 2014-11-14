package org.uiowa.cs2820.engineTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.uiowa.cs2820.engine.*;
import org.junit.Test;

/* The following code borrows from Professor Herman
 * This is the main test for the Search Engine project. It uses Field, Indexer and FieldSearch classes to
 * simulate the user interface methods to communicate with other classes in the package*/

public class IntegrationTest {

	 @Test
	 public void test() throws IOException {
		 
	     StartUp.init();
	     
	     Field f = new Field("citizen",true);
	     Indexer I = new Indexer("first.db");
	     I.addField(f);
	     Field g = new Field("two",2);
	     I.addField(g);
	     I = new Indexer("second.db");
	     I.addField(f);
	     I.addField(new Field("three",3));
	     FieldSearch F = new FieldSearch();
	     String[] S = F.findEquals(f);
	     assertEquals(S.length,2);
	     assertEquals(S[0],"second.db");
	     assertEquals(S[1],"first.db");

	     }

}
