package com.core.qassembler.includer;

import java.util.ArrayList;
import com.core.qassembler.file.ProgramFileHandler;

public class Includer {
	
	private ArrayList<ProgramFileHandler> includeList;
	
	public Includer(String mainFilePath){
		includeList=new ArrayList<ProgramFileHandler>();
	}
	
	public ProgramFileHandler getProgramFile(int index){
		return includeList.get(index);
	}
	
	public ArrayList<ProgramFileHandler> getAllProgramFiles(){
		return includeList;
	}
	
	public boolean isIncluded(String include_filename){
		for(int i=0;i<includeList.size();i++) if(includeList.get(i).getName().equals(include_filename)) return true;
		return false;
    }
	
	public void include(ProgramFileHandler lib_toinclude){
		// DO LOTS OF STUFF HERE
		includeList.add(lib_toinclude);
	}
}