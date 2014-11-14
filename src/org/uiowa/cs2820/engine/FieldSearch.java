package org.uiowa.cs2820.engine;

import java.io.IOException;
import java.util.ArrayList;

public class FieldSearch {

	public FieldSearch() {}
		
	public String[] findEquals(Field f) throws IOException {
		Node p = new LinearMemoryDatabase().fetch(f);
		if (p == null) return new String[0];
		ArrayList<String> lst = p.getIdentifiers();
		String[] R = new String[lst.size()];
		R = lst.toArray(R);
		return R;
	}
}
