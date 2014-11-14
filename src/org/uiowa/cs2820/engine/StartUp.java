package org.uiowa.cs2820.engine;

public class StartUp {
	public static void init(){
		CheckPoint.init();
		KeyStorage.init();
		ValueStorage.init();
		LinearMemoryDatabase.init();
	}
}
