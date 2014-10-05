package com.core.qassembler.memory.properties;

import java.util.HashMap;
import java.util.Map;

import com.core.qassembler.file.MainProgramFile;

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
	
	private void declare(String labelName,int address){
		label_list.put(labelName, address);
	}
	
	public MainProgramFile handleLabels(MainProgramFile mainFile){
		// DECLARE LABELS AND SUBSTITUTE THEIR DECLARATIONS BY THE LINE DIRECTLY BELOW IT
		
		return mainFile;
	}
}