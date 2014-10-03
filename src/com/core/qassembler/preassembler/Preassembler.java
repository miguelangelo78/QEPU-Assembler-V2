package com.core.qassembler.preassembler;

import com.core.qassembler.file.MainProgramFile;
import com.core.qassembler.includer.Includer;
import com.core.qassembler.memory.Memory;

public class Preassembler {
	private Includer includer; // INCLUDES FILES
	private Memory assemblerMemory; 

	public Preassembler(String mainFilePath){
		// DECLARE VARIABLES, OFFSETS, LABELS, EXPRESSIONS AND EVERYTHING ELSE BEFORE ASSEMBLING
		assemblerMemory=new Memory();
		includer=new Includer(mainFilePath);
	}
	
	public Includer getIncluder() {
		return includer;
	}
	
	public Memory getAssemblerMemory() {
		return assemblerMemory;
	}
	
	public MainProgramFile preAssemble(MainProgramFile mainFile){
		return mainFile; // CHANGE MAINFILE ASSEMBLY CODE
	}
}