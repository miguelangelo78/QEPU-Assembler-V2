package com.core.qassembler.memory.properties;

import java.util.HashMap;
import java.util.Map;

public class Variable {
	private Map<String,int[]> variable_list;
	private int variable_lastaddress;
	
	public Variable(){
		variable_list=new HashMap<String,int[]>();
		variable_lastaddress=0;
	}
	
	public Map<String, int[]> getVariableList() {
		return variable_list;
	}
	
	public int getVariableCount(){
		return variable_list.size();
	}
	
	public void declare(String variable_name,int variable_bytelength){
		variable_list.put(variable_name, new int[]{variable_lastaddress,variable_bytelength});
		variable_lastaddress+=variable_bytelength+1;
	}
}