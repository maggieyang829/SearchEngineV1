package org.uiowa.cs2820.engine;

import java.util.BitSet;

public class Allocate {
	private static BitSet bitset = new BitSet();
	
	public static long allocate(){
		try{
			int nextBit = bitset.previousClearBit(bitset.length() - 1);		//a free bit index in bitset
			bitset.set(nextBit);											//sets the free bit to being 'in use'
			return(nextBit);												
		}catch(ArrayIndexOutOfBoundsException e){							//no free bit index in bitset (return of -1)
			int nextBit = bitset.length();									//nextBit set to last index of bitset + 1			
			bitset.set(nextBit);											//sets the newly created bit to being 'in use'
			return(nextBit);
			
		}
	}
	
	public static boolean free(long area){									
		int clearBit = (int) area;											//cast as int to use with indexing
		if(clearBit > bitset.length() - 1){									//checks to make sure index is within bitset bounds
			System.out.println("area out of bounds");
			return(false);
		}
		try{
			bitset.clear(clearBit);											//sets the specified bit to unused
			return(true);													
		}catch(ArrayIndexOutOfBoundsException e){							//checks to make sure index is non-negative
			System.out.println("area is a negative index");
			return(false);
		}
	}
}
