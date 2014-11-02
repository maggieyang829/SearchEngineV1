package org.uiowa.cs2820.engine;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DiskSpace {
	private static File file;
	
	public static void write(long area, byte[] b) throws IOException{
		if(file.exists()) {
			RandomAccessFile rafw = new RandomAccessFile(file, "rw"); //creates random access file
			rafw.seek(area); //sets pointer in file to area
			rafw.setLength(DiskSpace.size() + b.length); //pre-allocates space for writing
			rafw.write(b); //writes the byte array to the file
			rafw.close();
		}
		//if file doesn't exist, create file and recurse
		else {
			file = new File("byteArray.txt");
			write(area, b);
		}	
	}
	
	public static byte[] read(long area) throws IOException{
		RandomAccessFile rafr = new RandomAccessFile(file, "rw");
		byte[] B = new byte[DiskSpace.size()]; //creates a new byte array
		rafr.seek(area); //sets pointer in file to area needed
		rafr.readFully(B); //reads from pointer to end of file and writes to byte array
		rafr.close();
		return B;
	}
	
	//returns the size of the file
	public static int size() {
		int fileSize = (int) file.length();
		return fileSize;	
	}
	
	//checks if the file is empty, not sure if really needed
	public static boolean isEmpty() {
		if (DiskSpace.size() == 0) return true;
		else return false;
	}
}
