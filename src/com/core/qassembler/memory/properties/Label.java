package com.core.qassembler.memory.properties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.core.qassembler.constants.QConstants;
import com.core.qassembler.file.MainProgramFile;
import com.utils.regex.RegexHandler;

public class Label implements QConstants{
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
	
	public MainProgramFile handleLabels(MainProgramFile mainFile) throws Exception{
		// DECLARE LABELS AND SUBSTITUTE THEIR DECLARATIONS BY THE LINE DIRECTLY BELOW IT
		String assembly=mainFile.getFile().getAssemblyCode();
		String[]assembly_splitted=assembly.split("\\n");
		for(int line=0;line<assembly_splitted.length;line++){
			List<Object> labelDeclList=RegexHandler.match(PATT_LABEL, assembly_splitted[line], 0, new int[]{0,1});
			for(int i=0;i<labelDeclList.size();i++){
				String labelName=(String)((String[])labelDeclList.get(i))[1];
				String wholeMatch=(String)((String[])labelDeclList.get(i))[0];
				declare(labelName, line);
				assembly=assembly.replace(wholeMatch, "");
			}
		}
		
		List<Object> labelRefList=RegexHandler.match(PATT_LABELREF, assembly, Pattern.MULTILINE, new int[]{1});
		for(int i=0;i<labelRefList.size();i++){
			String labelName=(String)((String[])labelRefList.get(i))[0];
			if(!isLabelDeclared(labelName)) throw new Exception("The label '"+labelName+"' was not previously declared.");
			else assembly=assembly.replace("@"+labelName, ""+getAddress(labelName));
		}
		
		mainFile.getFile().setAssemblyCode(assembly);
		return mainFile;
	}
}