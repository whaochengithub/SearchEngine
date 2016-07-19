package com.whc.SearchEngine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

public class FileMerge {
	private String fileIndexPath = "/Users/whaochen/Documents/workspaceJavaEE/SearchEngine/src/com/whc/searchEngine/Result/invertedIndex.txt";
	private HashMap<Integer, BufferedReader> readers;
	private PriorityQueue<Tuples> queue;
	private IndexElement tmpRecord;
	private File resultFile;
	private BufferedWriter out;
	
	public FileMerge() throws IOException{
		this.readers = new HashMap<Integer, BufferedReader>();
		MyCompare myCompare = new MyCompare();
		this.queue = new PriorityQueue<Tuples>(9,myCompare);
		this.tmpRecord = null;
		this.resultFile = new File(fileIndexPath);
		this.resultFile.createNewFile();
	    this.out = new BufferedWriter(new FileWriter(resultFile));  
	}
	
	public void  readFiles() throws IOException{
		for(int i=0; i<9; i++){
			String fileName = "/Users/whaochen/Documents/workspaceJavaEE/SearchEngine/src/com/whc/searchEngine/Result/"+i+".txt";
			File file = new File(fileName);
			FileReader filerd = new FileReader(file);
			BufferedReader br = new BufferedReader(filerd);
		    readers.put(i, br);
		}
		
	}
	
	
	
	public void FileMerge(Tuples tuple) throws IOException {
		if (this.tmpRecord != null
				&& this.tmpRecord.getWord().equals(tuple.getWord())) {
			IndexNode node = new IndexNode(tuple.getDocid(), tuple.getCount());
			tmpRecord.getNodes().add(node);
		} else {
			if (this.tmpRecord != null) {
				out.write(this.tmpRecord.toString());
				out.write("\r\n");
				out.flush();
			}
			this.tmpRecord = new IndexElement(tuple.getWord());
			IndexNode node = new IndexNode(tuple.getDocid(), tuple.getCount());
			tmpRecord.getNodes().add(node);
		}
	}
	
	public boolean addNewElement(int id) throws IOException{
		String line = null;
		line = this.readers.get(id).readLine();
		if (line!=null) {
			this.queue.add(new Tuples(line,id));
			return true;
		}
		return false;
		
	}
	public static void main(String[] args) throws IOException{
		//MyFileReader.generateFiles();
		
		FileMerge mrf = new FileMerge();
		mrf.readFiles();
		int Open_file_Num = 9;
		int mark_num = 0;
		for(int i=0; i<9; i++){
			mrf.addNewElement(i);
		}
		while(Open_file_Num>0){
			Tuples tp = mrf.queue.poll();
			mark_num = tp.getIndexNum();
			mrf.FileMerge(tp);
			boolean mark = mrf.addNewElement(mark_num);
			if(mark==false){
				Open_file_Num--;
			}
		}
		while(!mrf.queue.isEmpty()){
			Tuples tp = mrf.queue.poll();
			mark_num = tp.getIndexNum();
			mrf.FileMerge(tp);
		}
		
	}
	
	
}
