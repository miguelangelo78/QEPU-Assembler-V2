package com.core.qassembler.preassembler;

import com.core.qassembler.file.MainProgramFile;
import com.core.qassembler.includer.Includer;
import com.core.qassembler.memory.Memory;

public class Preassembler {
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
	
	public MainProgramFile preAssemble(MainProgramFile mainFile){
		// DO SH*T LOADS OF THINGS HERE
		// DECLARE VARIABLES, OFFSETS, LABELS, EXPRESSIONS, INTERVALS , HANDLE COMMENTS, CHANGE ASSEMBLY CODE AND EVERYTHING ELSE BEFORE ASSEMBLING
		return mainFile;
	}
}