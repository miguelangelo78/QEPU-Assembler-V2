package com.core.qassembler.memory.properties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.core.qassembler.constants.QConstants;
import com.core.qassembler.file.MainProgramFile;
import com.utils.regex.RegexHandler;

public class Variable implements QConstants{
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
	
	public String getVarNameByIndex(int index){
		for(String entry:variable_list.keySet()) if(index<=0) return entry;	else index--;
		return null;
	}
	
	public int getVarAddress(String variableName){
		return variable_list.get(variableName)[0];
	}
	
	public int getVarSize(String variableName){
		return variable_list.get(variableName)[1];
	}
	
	private void declare(String variable_name,int variable_bytelength){
		variable_list.put(variable_name, new int[]{variable_lastaddress,variable_bytelength-1});
		variable_lastaddress+=variable_bytelength+1;
	}
	
	public MainProgramFile handleVariables(MainProgramFile mainFile){
		String assembly=mainFile.getFile().getAssemblyCode();
		List<Object> variableMatches=RegexHandler.match(PATT_VARIABLEDECL, assembly, Pattern.MULTILINE, null);
		for(int i=0;i<variableMatches.size();i++){
			//DECLARE VARIABLES
			String[] variableDeclaration=((String)variableMatches.get(i)).split(",");
			String variableName=((String)((String[])RegexHandler.match(PATT_VARIABLENAME, variableDeclaration[0], Pattern.MULTILINE, new int[]{1}).get(0))[0]).trim();
			int extraBytes=0;
			
			if(variableDeclaration.length>2){
				extraBytes=Integer.parseInt(variableDeclaration[2].trim());
				declare(variableName, extraBytes);
			}
			else
				if(variableDeclaration[1].contains("\"")) declare(variableName, (variableDeclaration[1].replace("\"","").trim()).length());
				else declare(variableName,1);
			
			//SUBSTITUTE DECLARATIONS INTO MOV'S AND INTO CONSTANTS:
			String variableReplacement="mov ["+getVarAddress(variableName)+"],"+variableDeclaration[1].trim();
			if(variableDeclaration.length>2) variableReplacement+=","+extraBytes;
			assembly=assembly.replace(((String)variableMatches.get(i)),variableReplacement);
		}
		//SUBSTITUTE ALL THE REST (VARS) INTO CONSTANTS (THEIR ADDRESSES)
		for(int i=0;i<variable_list.size();i++){
			String varName=getVarNameByIndex(i);
			assembly=assembly.replace("$"+varName, ""+variable_list.get(varName)[0]);
		}
		mainFile.getFile().setAssemblyCode(assembly);
		return mainFile;
	}
}