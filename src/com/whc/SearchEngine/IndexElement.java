package com.whc.SearchEngine;

import java.util.ArrayList;

public class IndexElement {
	private String word;
	private ArrayList<IndexNode> nodes;
	
	public IndexElement(String word){
		this.word = word;
		nodes = new ArrayList<IndexNode>();
	}
    
	public void AddNode(int docId, int pos){
		
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public ArrayList<IndexNode> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<IndexNode> nodes) {
		this.nodes = nodes;
	}
	
	public String toString(){
		StringBuilder br = new StringBuilder();
		br.append(this.word+" ");
		for (IndexNode Node : this.nodes) {
			br.append(Node.getDocId()+","+Node.getPos()+" ");
		}
		return br.toString();
	}
	
}
