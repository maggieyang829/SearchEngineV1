package org.uiowa.cs2820.engineTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.uiowa.cs2820.engine.DiskSpace;
import org.uiowa.cs2820.engine.Field;

public class DiskSpaceTest {

	@Test
	/* Test for write and read method. Checks if pre-written byte array
	 * is the same as the read byte array by reverting them to objects
	 * and comparing them.
	 */
	public void test1() throws IOException {
		byte[] b = new byte[1024];
		DiskSpace.write(1, b);
		byte[] b2 = DiskSpace.read(1);
		Object B = Field.revert(b);
		Object B2 = Field.revert(b2);
		assertEquals(B, B2);
	}

	@Test
	/* Test for write and read method but with area number 2. Checks if 
	 * pre-written byte array is the same as the read byte array by 
	 * reverting them to objects and comparing them.
	 */
	public void test2() throws IOException {
		byte[] b = new byte[1024];
		DiskSpace.write(2, b);
		byte[] b2 = DiskSpace.read(2);
		Object B = Field.revert(b);
		Object B2 = Field.revert(b2);
		assertEquals(B, B2);
	}
}
