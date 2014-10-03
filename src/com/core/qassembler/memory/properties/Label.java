package com.core.qassembler.memory.properties;

import java.util.HashMap;
import java.util.Map;

public class Label {
	private Map<String,Integer> label_list;
	
	public Label(){
		label_list=new HashMap<String,Integer>();
	}
	
	public Map<String,Integer> getLabelList(){
		return label_list;
	}
	
	public boolean isLabelDeclared(String labelName){
		return label_list.containsKey(labelName);
	}
	
	public Integer getAddress(String labelName){
		return label_list.get(labelName);
	}
	
	public void declare(String labelName,int address){
		label_list.put(labelName, address);
	}
}