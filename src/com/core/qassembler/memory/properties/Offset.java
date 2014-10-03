package com.core.qassembler.memory.properties;

import java.util.HashMap;
import java.util.Map;

public class Offset {
	private Map<Integer,Integer> offset_list;

	public Offset(){
		offset_list=new HashMap<Integer,Integer>();
	}
	
	public int getJumpOffset(int jump_address){
		int new_jump_address=jump_address;
		for(Integer key:offset_list.keySet()) if(jump_address>key) new_jump_address+=offset_list.get(key)-1;
	    return new_jump_address-1;
	}
	
	public Map<Integer, Integer> getOffsetList() {
		return offset_list;
	}
	
	public void addOffset(int start,int length){
		offset_list.put(start,length);
	}
}