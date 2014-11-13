package org.uiowa.cs2820.engineTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.uiowa.cs2820.engine.Allocate;

public class AllocateTest {

	@Test
	public void allocateTest() {
		long t;
		long s;
		t = Allocate.allocate();
		assertEquals(t, 0);		
		s = Allocate.allocate();
		assertEquals(s, 1);
		Allocate.clearAll();
	}
	
	@Test
	public void freeTest() {
		long t;
		long s;
		long u;
		boolean r;
		Allocate.allocate();
		Allocate.allocate();
		t = Allocate.allocate();
		assertEquals(t, 2);
		Allocate.free(1);
		s = Allocate.allocate();
		assertEquals(s, 1);
		r = Allocate.free(5);
		assertEquals(r, false);
		Allocate.clearAll();
		
		
	}
	
	@Test
	public void clearAllTest() {
		long t;
		long s;
		Allocate.allocate();
		Allocate.allocate();
		Allocate.allocate();
		t = Allocate.allocate();
		assertEquals(t,3);
		Allocate.clearAll();
		s = Allocate.allocate();
		assertEquals(s,0);
		Allocate.clearAll();
		
	}

}
