package org.uiowa.cs2820.engine;

import java.io.IOException;

public class Indexer {
	private Database D;
	private String id;
	  
	Indexer(Database d, String id) {
	// constructor does nothing now, but someday
	// may need to set up database for doing things
	this.D = d;
	this.id = id;
	}
	  
	public void addField(Field f) throws IOException {
	// Field has (name,value) which is used as key for
	// the database operations
	D.store(f, id);
	}
}
