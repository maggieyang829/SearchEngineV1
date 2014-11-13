package org.uiowa.cs2820.engine;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DiskSpace {
	private static File file = LinearMemoryDatabase.getDataStorage();
	public static void write(long area, byte[] b) throws IOException{
		RandomAccessFile rafw = new RandomAccessFile(file, "rw"); //creates random access file
		rafw.seek(area); //sets pointer in file to area
		rafw.setLength(DiskSpace.size() + b.length); //pre-allocates space for writing
		rafw.write(b); //writes the byte array to the file
		rafw.close();
	}
	
	public static byte[] read(long area) throws IOException{
		RandomAccessFile rafr = new RandomAccessFile(file, "r");
		rafr.seek(area); //sets pointer in file to area needed
		byte[] B = new byte[1024]; //creates a new byte array
		//reads from pointer to end of area and writes to byte array
		for (int i=0; i < 1024; i++) {
			B[i] = rafr.readByte();
		}
		rafr.close();
		return B;
	}
	
	//returns the size of the file
	public static int size() throws IOException {
		@SuppressWarnings("resource")
		RandomAccessFile rafs = new RandomAccessFile(file, "r");
		int fileSize = (int) rafs.length();
		return fileSize;	
	}
	
	//checks if the file is empty, not sure if really needed
	public static boolean isEmpty() throws IOException {
		if (DiskSpace.size() == 0) return true;
		else return false;
	}
}
