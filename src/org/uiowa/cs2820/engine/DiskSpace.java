package org.uiowa.cs2820.engine;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DiskSpace {
	private static File file = LinearMemoryDatabase.getDataStorage();
	public static void write(long area, byte[] b) throws IOException{
		RandomAccessFile rafw = new RandomAccessFile(file, "rw"); //creates random access file
		byte[] L = new byte[2];
		// Following two lines save length of byte array to first two bytes of area chunk
		L[0] = (byte) (b.length/2);
		L[1] = (byte) b.length;
		rafw.seek(area); //sets pointer in file to area
		rafw.setLength(1024);
		rafw.write(L); //writes the byte array to the file
		rafw.write(b);
		rafw.close();
	}
	
	public static byte[] read(long area) throws IOException{
		RandomAccessFile rafr = new RandomAccessFile(file, "r");
		rafr.seek(area); //sets pointer in file to area needed
		byte[] L = new byte[2];
		rafr.read(L); //reads first two bytes to get length needed
		int N = (int)L[0];
		if(N < 0) N = N + 256;
		N = N * 256;
		int M = (int)L[1];
		if(M < 0) M = M + 256;
		N = N + M;
		byte[] B2 = new byte[N]; //sets length two read to length needed
		//reads from pointer to end of area and writes to byte array
		for(int i=0; i < N; i++) {
			B2[i] = (byte) rafr.read();
		}
		rafr.close();
		return B2;
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
	
	public static void reset() throws IOException{
		@SuppressWarnings("resource")
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		raf.setLength(0);
	}
}
