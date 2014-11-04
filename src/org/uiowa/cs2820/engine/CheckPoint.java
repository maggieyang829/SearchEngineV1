package org.uiowa.cs2820.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.BitSet;
import java.io.IOException;

public class CheckPoint {
	
	public static void save(BitSet bitset){
		byte[] bfile = Field.convert(bitset);											//uses Field to turn bitset into a byte array
		File file = new File("bitSet.txt");												//creates a new file variable
		try{
			file.createNewFile();														//checks and sees if the file name is already created or not
			fileWrite("bitSet.txt", bfile);												//writes the byte array to file named "bitSet.txt"
		}catch(IOException ex){
			fileWrite("bitSet.txt", bfile);												//writes the byte array to file named "bitSet.txt"
		}
		    
	}
	
	public static BitSet restore(){
		
	}
	
	private static void fileWrite(String file, byte[] bfile){							
		try{				
			FileOutputStream fileOutputStream = new FileOutputStream(file);		//writes byte array to file
			fileOutputStream.write(bfile);
			fileOutputStream.close();
	    
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
