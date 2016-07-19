package com.whc.SearchEngine;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;
import java.util.zip.GZIPInputStream;

public class MyFileReader {
	private ArrayList<FileInfo> fileList;
    
	public MyFileReader() {
		fileList = new ArrayList<FileInfo>();
	}

	public static void generateFiles() throws IOException {
		String fileIndexPath = "/Users/whaochen/Documents/workspaceJavaEE/SearchEngine/src/com/whc/searchEngine/Result/map.txt";
		MyCompare compare = new MyCompare();
		File mapName = new File(fileIndexPath);
		mapName.createNewFile();
		int total = 0;
		BufferedWriter mapOut = new BufferedWriter(new FileWriter(mapName));  
		
		BufferedWriter out = null;
		ArrayList<Tuples> results = new ArrayList<Tuples>();
		for(int i=0; i<= 82; i++){
		if(i%10==0){
			 if(out!=null){
				writeFile(results, out, compare);		 
			 }
			 out = buildFile(i/10);
		}
		
		MyFileReader fileReader = new MyFileReader();
		String indexFileName = "/Users/whaochen/Documents/workspaceJavaEE/SearchEngine/src/com/whc/searchEngine/nz2_merged/"+i+"_index";

		FileInputStream indexFr = new FileInputStream(indexFileName);
		GZIPInputStream indexGr = new GZIPInputStream(indexFr);

		Scanner ScIndex = new Scanner(indexGr);
		while (ScIndex.hasNext()) {

			String Indexline = ScIndex.nextLine();
			String[] contents = Indexline.split(" ");
			FileInfo fileInfo = new FileInfo();
			fileInfo.setFileName(contents[0]);
			fileInfo.setWordNumber(Integer.parseInt(contents[3]));
			fileReader.fileList.add(fileInfo);
		}

		String dataFileName = "/Users/whaochen/Documents/workspaceJavaEE/SearchEngine/src/com/whc/searchEngine/nz2_merged/"+i+"_data";
		File dataFile = new File(dataFileName);
		FileInputStream dataFr = new FileInputStream(dataFile);
		GZIPInputStream dataGr = new GZIPInputStream(dataFr);
		
        BufferedInputStream bis = new BufferedInputStream(dataGr);
        
	
			for (int j = 0; j < fileReader.fileList.size(); j++) {
				FileInfo file = new FileInfo();
				file = fileReader.fileList.get(j);
				int count = file.getWordNumber();
				String fileName = file.getFileName();
			//	String filePath = "/Users/whaochen/Documents/workspaceJavaEE/SearchEngine/src/com/whc/searchEngine/Result/"+ total + ".txt";
				
				//File writename = new File(fileIndexPath);
				//writename.createNewFile();
				
				byte[] bytes = new byte[count];
				
				bis.read(bytes, 0, count);
				String str = new String(bytes, 0, bytes.length);
				//ArrayList<Tuples> results = new ArrayList<Tuples>();
				//String str2 = parse.initDoc(str);
				mapOut.write(total+" "+fileName+" "+count);
				mapOut.write("\r\n");
				mapOut.flush();
				parse.parsePage(fileName, str, results, total);
				
										
				total++;				
			}
		}
		writeFile(results, out, compare);
		mapOut.close();
	}
	
	
	private static void writeFile(ArrayList<Tuples> results, BufferedWriter out, MyCompare compare) throws IOException{
		Collections.sort(results, compare);
		 for (Tuples tuples : results) {
				out.write(tuples.getWord()+" "+tuples.getDocid()+" "+tuples.getCount());
				out.write("\r\n");
				out.flush();
			}	
	 out.close();
	 results.clear();
	}
	
	private static BufferedWriter buildFile(int id) throws IOException{
        String fileIndexPath = "/Users/whaochen/Documents/workspaceJavaEE/SearchEngine/src/com/whc/searchEngine/Result/"+id+".txt";	
		File writename = new File(fileIndexPath);
		writename.createNewFile();
		BufferedWriter out = new BufferedWriter(new FileWriter(writename));  
		return out;
		
	}
	

	
}
