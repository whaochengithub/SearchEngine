package com.whc.SearchEngine;

import java.util.ArrayList;

public class IndexNode {
	private int docId;
	private int pos;
	
	public IndexNode(int docId, int pos){
		this.docId = docId;
		this.pos = pos;
	}
	
	
	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}


	public int getPos() {
		return pos;
	}


	public void setPos(int pos) {
		this.pos = pos;
	}

	
	
	
}
