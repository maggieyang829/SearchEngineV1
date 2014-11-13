package org.uiowa.cs2820.engineTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.uiowa.cs2820.engine.Database;
import org.uiowa.cs2820.engine.Field;
import org.uiowa.cs2820.engine.FieldSearch;
import org.uiowa.cs2820.engine.Indexer;
import org.uiowa.cs2820.engine.LinearMemoryDatabase;

public class FieldTest {

	@Test
	public void test0() throws IOException {
	  Database D = new LinearMemoryDatabase();
	  FieldSearch F = new FieldSearch(D);
	  Field F1 = new Field("1",new Integer(45));
	  assertEquals(F.findEquals(F1).length,0);
	  }
	
	@Test
	public void test1() throws IOException {
	  Database D = new LinearMemoryDatabase();
	  FieldSearch F = new FieldSearch(D);
	  Indexer I = new Indexer("abc");
	  Field F1 = new Field("1",new Integer(45));
	  Field F2 = new Field("part","bolt");
	  Field F3 = new Field("part","bolt");
	  I.addField(F1);
	  I.addField(F2);
	  I = new Indexer("def");
	  I.addField(F3);
	  String[] S = F.findEquals(F3);
	  assertEquals(S.length,2);
	  assertEquals(S[0],"abc");
	  assertEquals(S[1],"def");
	  }
	
	@Test(expected = IllegalArgumentException.class)
	public void test2() throws IOException {
	  Database D = new LinearMemoryDatabase();
	  Indexer I = new Indexer("data");
	  Field F = new Field("Iowa",
		"some very long string that should not" +
	    "be allowed as part of a lookup value" + 
		"because there is a size limit in the" +
	    "code for creating a Field");
		I.addField(F); 
	  }
    }