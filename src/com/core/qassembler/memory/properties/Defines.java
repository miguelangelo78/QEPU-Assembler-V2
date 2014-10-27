package com.core.qassembler.memory.properties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.core.qassembler.constants.QConstants;
import com.core.qassembler.file.MainProgramFile;
import com.utils.regex.RegexHandler;

public class Defines implements QConstants{
	private Map<String,Object> define_list;
	
	public Defines(){
		define_list=new HashMap<String,Object>();
	}
	
	public Map<String,Object> get_define_list(){
		return define_list;
	}
	
	public void declare(String define_name,Object content){
		define_list.put(define_name, content);
	}
	
	public String getDefineContent(String define_name){
		return (String)define_list.get(define_name);
	}
	
	public String getDefineNameByIndex(int index){
		for(String entry:define_list.keySet()) if(index<=0) return entry; else index--;
		return null;
	}
	
	public boolean isDefined(String define_name){
		return define_list.containsKey(define_name);
	}
	
	public String getDefineContentByIndex(int index){
		for(String entry:define_list.keySet()) if(index<=0) return (String) define_list.get(entry); else index--;
		return null;
	}
	
	public void dump_defines(){
		System.out.println(" *********** MEMORY DUMP: ");
		for(int i=0;i<define_list.size();i++)
			System.out.println("Name: "+getDefineNameByIndex(i)+". Content: "+getDefineContentByIndex(i));
	}
	
	public MainProgramFile handleDefines(MainProgramFile mainFile) throws Exception{
		String assembly=mainFile.getFile().getAssemblyCode();
		// TODO: DECLARE DEFINE BY MATCHING THE DECLARATION
		List<Object> define_matchlist=RegexHandler.match(PATT_MACRO_DEFINE_DECL, assembly, Pattern.MULTILINE, new int[]{0,1,2});
		for(int i=0;i<define_matchlist.size();i++){
			String define_name=((String[])define_matchlist.get(i))[1];
			if(!isDefined(define_name)){
				Object define_content=((String[])define_matchlist.get(i))[2];
				declare(define_name, define_content);
				String define_wholematch=((String[])define_matchlist.get(i))[0];
				//FIX: ADD SLICES TO [] BECAUSE OF REGEX
				define_wholematch=define_wholematch.replace("[", "\\[").replace("]","\\]");
				assembly=assembly.replaceFirst(define_wholematch, "");
			}else throw new Exception("***** ERROR: Define macro '"+define_name+"' has been previously defined!");
		}
		// TODO: SUBSTITUTE THE REFERENCES OF THE DECLARED DEFINES INTO ITS CONTENTS
		for(int i=0;i<define_list.size();i++){
			String define_name=getDefineNameByIndex(i);
			boolean define_referenced=RegexHandler.match("\\b"+define_name+"\\b", assembly, 0, null).size()>0;
			if(define_referenced)
				assembly=assembly.replaceAll("\\b"+define_name+"\\b", (String) getDefineContent(define_name));
		}
		mainFile.getFile().setAssemblyCode(assembly);
		return mainFile;
	}
}
