package com.core.qassembler.memory.properties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import com.core.qassembler.constants.Global_Constants;
import com.core.qassembler.file.MainProgramFile;
import com.utils.regex.RegexHandler;

public class Variable implements Global_Constants{
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
	
	public int getVarSizeByIndex(int index){
		for(String entry:variable_list.keySet()) if(index<=0) return variable_list.get(entry)[1];	else index--;
		return -1;
	}
	
	public int getVarAddressByIndex(int index){
		for(String entry:variable_list.keySet()) if(index<=0) return variable_list.get(entry)[0];	else index--;
		return -1;
	}
	
	public int getVarAddress(String variableName){
		return variable_list.get(variableName)[0];
	}
	
	public int getVarSize(String variableName){
		return variable_list.get(variableName)[1];
	}
	
	private void declare(String variable_name,int variable_bytelength) throws Exception{
		if(variable_bytelength<=0) throw new Exception(" **** ASSEMBLER ERROR: The variable '"+variable_name+"' must have more than 0 bytes of size.");
		variable_list.put(variable_name, new int[]{variable_lastaddress,variable_bytelength});
		variable_lastaddress+=variable_bytelength;
	}
	
	public void dump_memory(){
		System.out.println(" *********** MEMORY DUMP: ");
		for(int i=0;i<variable_list.size();i++)
			System.out.println("Name: "+getVarNameByIndex(i)+". Address: "+getVarAddressByIndex(i)+". Size: "+getVarSizeByIndex(i)+".");
	}
	
	public MainProgramFile handleVariables(MainProgramFile mainFile) throws Exception{
		String assembly=mainFile.getFile().getAssemblyCode();
		List<Object> variableMatches=RegexHandler.match(PATT_VARIABLEDECL, assembly, Pattern.MULTILINE, null);
		for(int i=0;i<variableMatches.size();i++){
			// DECLARE VARIABLES
			String[] variableDeclaration=((String)variableMatches.get(i)).split(",");
			String variableName=((String)((String[])RegexHandler.match(PATT_VARIABLENAME, variableDeclaration[0], Pattern.MULTILINE, new int[]{1}).get(0))[0]).trim();
			int extraBytes=0;
			int extraBytesOffset=variableDeclaration[1].replaceAll("[^\\\\]", "").length(); // FIX FOR ESCAPED CHARACTERS INCREASING THE REAL SIZE OF THE VARIABLES
			
			if(variableDeclaration.length>2){
				extraBytes=Integer.parseInt(variableDeclaration[2].trim());
				declare(variableName, extraBytes); // FOR FIXED SIZED VARIABLES
			}
			else // FOR STRINGS (NON FIXED VARIABLES)
				if(variableDeclaration[1].contains("\""))
					declare(variableName, (variableDeclaration[1].replace("\"","").trim()).length()-extraBytesOffset);
				else declare(variableName,1);
			// SUBSTITUTE DECLARATIONS INTO MOV'S AND INTO CONSTANTS:
			String variableReplacement="mov ["+getVarAddress(variableName)+"],"+variableDeclaration[1].trim();
			if(variableDeclaration.length>2)
				if(!variableDeclaration[1].contains("\"")) // FIX FOR FIXED SIZED CONSTANTS:
					for(int j=1;j<extraBytes;j++) variableReplacement+="\nmov ["+(getVarAddress(variableName)+j)+"],0";
				else variableReplacement+=","+(extraBytes+1);
			assembly=assembly.replace(((String)variableMatches.get(i)),variableReplacement);
		}
		// SUBSTITUTE ALL THE REST (VARS REFS) INTO CONSTANTS (THEIR ADDRESSES)
		for(int i=0;i<variable_list.size();i++)
			assembly=assembly.replaceAll("\\b"+getVarNameByIndex(i)+"\\b", ""+getVarAddressByIndex(i));

		mainFile.getFile().setAssemblyCode(assembly);
		return mainFile;
	}
}