package com.whc.SearchEngine;


public class Tuples {
	private String word;
	private int docid;
	private int count;
	private int indexNum;
	
	public Tuples(String word, int docid, int count){
		this.word = word;
		this.docid = docid;
		this.count = count;
	}
	
	public Tuples(String line, int num){
		String[] strs = line.split(" ");
		this.word = strs[0];
		this.docid = Integer.parseInt(strs[1]);
		this.count = Integer.parseInt(strs[2]);
		this.indexNum = num;
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getDocid() {
		return docid;
	}
	public void setDocid(int docid) {
		this.docid = docid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public int getIndexNum() {
		return indexNum;
	}

	public void setIndexNum(int indexNum) {
		this.indexNum = indexNum;
	}
	
	
	 
	
}
