package com.whc.SearchEngine;

import java.util.Comparator;

public class MyCompare implements Comparator<Tuples>{
	
	
	@Override
	public int compare(Tuples o1, Tuples o2) {
		if(o1.getWord().compareTo(o2.getWord())>0){
			return 1;
		}else if(o1.getWord().compareTo(o2.getWord())<0){
			return -1;
		}else{
			if(o1.getDocid()>o2.getDocid()){
				return 1;
			}else if(o1.getDocid()<o2.getDocid()){
				return -1;
			}else{
				if(o1.getCount()>o2.getCount()){
					return 1;
				}else{
					return -1;
				}
			}
		}
	}
}
