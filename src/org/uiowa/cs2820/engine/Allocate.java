package org.uiowa.cs2820.engine;

import java.util.BitSet;

public class Allocate {
	
	/*
	 * The Allocate class' main methods are .allocate() and .free(area): 
	 * 
	 * The .allocate() class works by returning a long value of where a free bit is located that can be passed to the DiskSpace class 
	 * where that information is written in the proper place. This long value should be saved to the node through the addr method in the 
	 * Node class for KeyStorage. ValueStorage TBD. The method takes no input.
	 * 
	 * The .free(area) class works by taking in the long value of where objects were stored in DiskSpace and updating the BitSet array so
	 * that bit will be marked free. This method returns true if a successful clear was completed and false otherwise. 
	 * 
	 * Questions for Prof/TA:
	 * 	How do you do unit tests for this class?
	 * 	Why does everything need to be static for it to work?
	 */
	

	private static boolean firstTime = true;
	
	public static long allocate(){											
		BitSet bitSet;														//initialization of BitSet object
		if(firstTime){														//checks to make sure a file will be present to call restore
																			//if not just uses a new BitSet
			bitSet = new BitSet();
			firstTime = false;												//flips firstTime so a new BitSet won't be created again
		}else{
			bitSet = CheckPoint.restore();									//loads the bitset from memory
		}
		long freeBit;														//initialize freeBit: bit to return as free
		freeBit = bitSet.nextClearBit(0);									//call to find the free bit
		bitSet.set((int) freeBit); 
		CheckPoint.save(bitSet);											//saves the new BitSet object
		return(freeBit);
		
	}
	
	public static boolean free(long area){
		BitSet bitSet;														//initialization of BitSet object
		try{
			bitSet = CheckPoint.restore();									//tries to get BitSet object CheckPoint
		}catch(Exception na){
			System.out.println("file does not exist");			
			return(false);
		}
		int clearBit = (int) area;											//cast as int to use with indexing
		if(clearBit > bitSet.length()){										//checks to make sure index is within bitSet bounds
			System.out.println("area out of bounds");
			return(false);
		}
		try{
			bitSet.clear(clearBit);											//sets the specified bit to unused
			CheckPoint.save(bitSet);										//saves the new BitSet object
			return(true);													
		}catch(ArrayIndexOutOfBoundsException e){							//checks to make sure index is non-negative
			System.out.println("area is a negative index");
			return(false);
		}
	}
}
