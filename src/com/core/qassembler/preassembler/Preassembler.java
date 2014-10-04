package com.core.qassembler.preassembler;

import java.util.List;
import java.util.regex.Pattern;

import com.core.qassembler.constants.QConstants;
import com.core.qassembler.file.MainProgramFile;
import com.core.qassembler.file.ProgramFileHandler;
import com.core.qassembler.includer.Includer;
import com.core.qassembler.memory.Memory;
import com.core.regex.RegexHandler;

public class Preassembler implements QConstants{
	private Memory assemblerMemory; 

	public Preassembler(String mainFilePath){
		assemblerMemory=new Memory(mainFilePath);
	}
	
	public Includer getIncluder() {
		return assemblerMemory.getIncluder();
	}
	
	public Memory getAssemblerMemory() {
		return assemblerMemory;
	}
	
	private MainProgramFile handleIncluding(MainProgramFile mainFile) throws Exception{
		return assemblerMemory.addLibraries(mainFile);
	}
	
	private MainProgramFile handleExpressions(MainProgramFile mainFile){
		
		return mainFile;
	}
	
	private MainProgramFile handleOffsets(MainProgramFile mainFile){
		//DECLARE VARIABLES AND HANDLE STRINGS AND INTERVALS
		
		return mainFile;
	}
	
	private MainProgramFile handleIntervals(MainProgramFile mainFile){
		
		return mainFile;
	}
	
	private MainProgramFile handleLabels(MainProgramFile mainFile){
		
		return mainFile;
	}
	
	private MainProgramFile handleCommentsAndEmptyLines(MainProgramFile mainFile){
		// CLEAN SINGLE LINE COMMENTS, MULTILINE COMMENTS AND EMPTY LINES
		return mainFile;
	}
	
	public MainProgramFile preAssemble(MainProgramFile mainFile) throws Exception{
		mainFile=handleIncluding(mainFile);
		mainFile=handleCommentsAndEmptyLines(mainFile);
		mainFile=handleExpressions(mainFile);
		mainFile=handleIntervals(mainFile);
		mainFile=handleOffsets(mainFile);
		mainFile=handleLabels(mainFile);
		return mainFile;
	}
}