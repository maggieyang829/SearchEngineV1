package org.uiowa.cs2820.engineTest;

import java.io.File;

import static org.junit.Assert.*;

import org.junit.Test;
import org.uiowa.cs2820.engine.CheckPoint;

import java.util.BitSet;

public class CheckPointTest {

	@Test
	public void initTest() {
		CheckPoint.init();
		File f = new File("bitSet.txt");
		assertTrue(f.exists());
		
	}
	
	@Test
	public void saveRestoreTest() {
		//assuming Field unit test will cover convert
		//implicit testing of fileWrite
		BitSet bitset = new BitSet(5);
		BitSet testBitset;
		bitset.set(2);
		CheckPoint.save(bitset);
		testBitset = CheckPoint.restore();
		assertEquals(testBitset.get(1), bitset.get(1));
		assertEquals(testBitset.get(2), bitset.get(2));
		assertEquals(testBitset.nextClearBit(0), 0);
		assertEquals(testBitset.nextSetBit(0), 2);
		
	}

}
