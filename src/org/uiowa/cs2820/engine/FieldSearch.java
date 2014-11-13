package org.uiowa.cs2820.engine;

import java.io.IOException;
import java.util.ArrayList;

public class FieldSearch {
	  private Database D;
	  
	  public FieldSearch(Database d) {
		this.D = d;  
	    }
		
	  public String[] findEquals(Field f) throws IOException {
		Node p = D.fetch(f);
		if (p == null) return new String[0];
		ArrayList<String> lst = p.getIdentifiers(p.valueArea);
		String[] R = new String[lst.size()];
		R = lst.toArray(R);
		return R;
	    }
}
