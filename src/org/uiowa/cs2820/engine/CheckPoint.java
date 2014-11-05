package org.uiowa.cs2820.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.BitSet;
import java.io.IOException;

public class CheckPoint {
	
	/*
	 * This class works exclusively with Allocate to store the BitSet object that keeps track of where memory is free
	 *in the diskspace. 
	 *Questions I would still like to ask Prof/TA:
	 *	Is it alright to save it to a file in the working directory or should it also be saved to the diskspace?
	 *	How should files be tested with java unit tests?
	*/
	public static void save(BitSet bitset){
		byte[] bfile = Field.convert(bitset);										//uses Field to turn bitset into a byte array
		File file = new File("bitSet.txt");											//creates a new file variable
		try{
			file.createNewFile();													//checks and sees if the file name is already created or not
			fileWrite("bitSet.txt", bfile);											//writes the byte array to file named "bitSet.txt"
		}catch(IOException ex){
			fileWrite("bitSet.txt", bfile);											//writes the byte array to file named "bitSet.txt"
		}
		    
	}
	
	public static BitSet restore(){
		FileInputStream fileInputStream=null;										//initialize file input stream
		File file = new File("bitSet.txt");											//create file object for bitSet.txt						
		byte[] bFile = new byte[(int) file.length()];								//initialize byte array the size of the file
		
		try {

		    fileInputStream = new FileInputStream(file);							//link stream with file
		    fileInputStream.read(bFile);											//update bFile with info from stream
		    fileInputStream.close();
	 

	 
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
		 
		 BitSet bitSet;																//initialize BitSet object bitSet
		 bitSet = (BitSet) Field.revert(bFile);										//revert byte array to a BitSet object
		 
		 return(bitSet);															//return BitSet object bitSet
		
	}
	
	private static void fileWrite(String file, byte[] bfile){							
		try{				
			FileOutputStream fileOutputStream = new FileOutputStream(file);			//writes byte array to file
			fileOutputStream.write(bfile);
			fileOutputStream.close();
	    
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
