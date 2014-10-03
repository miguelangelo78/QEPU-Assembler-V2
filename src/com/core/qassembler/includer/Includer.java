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
	
	public void include(){
		// DO LOTS OF STUFF HERE
	}
}