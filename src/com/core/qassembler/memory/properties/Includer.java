package com.core.qassembler.memory.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import com.core.qassembler.constants.Global_Constants;
import com.core.qassembler.file.MainProgramFile;
import com.core.qassembler.file.ProgramFileHandler;
import com.utils.regex.RegexHandler;

public class Includer implements Global_Constants{
	
	private ArrayList<ProgramFileHandler> includeList;
	private String basePath;
	
	public Includer(String mainFilePath,String basePath){
		includeList=new ArrayList<ProgramFileHandler>();
		this.basePath=basePath;
	}
	
	public ProgramFileHandler getProgramFile(int index){
		return includeList.get(index);
	}
	
	public ArrayList<ProgramFileHandler> getAllProgramFiles(){
		return includeList;
	}
	
	public int getLibCount(){
		return includeList.size();
	}
	
	public boolean isIncluded(String include_filename){
		for(int i=0;i<includeList.size();i++) if(includeList.get(i).getPreAssembledName().equals(include_filename)) return true;
		return false;
    }
	
	public MainProgramFile include(MainProgramFile mainFile) throws Exception{
		boolean including_done=false;
		String assembly=mainFile.getFile().getAssemblyCode();
		while(!including_done){
			List<Object> includeMatches=RegexHandler.match(PATT_INCLUDING, assembly, Pattern.MULTILINE|Pattern.CASE_INSENSITIVE, new int[]{0,1});
			int libs_included=0;
			for(int i=0;i<includeMatches.size();i++){
				String include_line=(String)((String[])includeMatches.get(i))[0];
				String include_name=(String)((String[])includeMatches.get(i))[1];
				String include_filepath=basePath+include_name;
				
				if(include_name.equals(mainFile.getFile().getPreAssembledName()))
					throw new Exception("Cannot include the file itself ("+include_name+")! Exiting...");
				else if(!isIncluded(include_name)){
					ProgramFileHandler libFile=new ProgramFileHandler(include_filepath);
					System.out.println("Linking '"+libFile.getPreAssembledName()+"' ...");
					includeList.add(libFile);
					assembly=assembly.replace(include_line,libFile.getAssemblyCode());
				}else assembly=assembly.replace((String)((String[])includeMatches.get(i))[0], "");
				libs_included++;
			}
			if(libs_included==0){ including_done=true; break; }
		}
		if(getLibCount()>0){
			//SEE IF MAIN LABEL HAS BEEN DECLARED - BEGIN
	    	List<Object> mainLabelMatch=RegexHandler.match(PATT_MAINLABEL, assembly, Pattern.MULTILINE, new int[]{1});
	    	if(mainLabelMatch.size()==0) throw new Exception("The label '"+FILEMAIN_ENTRYPOINT+":' has not been declared in the main file!");
	    	if(mainLabelMatch.size()>1) throw new Exception("The label '@"+FILEMAIN_ENTRYPOINT+"' cannot be declared multiple times!");
	    	assembly="jmp @main\n"+assembly;
	    	mainFile.createLinkedFile(assembly);
		}
		mainFile.getFile().setAssemblyCode(assembly);
		return mainFile;
	}
}