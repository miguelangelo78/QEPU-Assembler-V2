package com.core.qassembler.preassembler.replacements;

import com.core.qassembler.file.MainProgramFile;
import com.core.qassembler.memory.Memory;

public class ConstantReplacements {
	public ConstantReplacements(){ }
	
	public MainProgramFile replaceAll(MainProgramFile mainFile,Memory assemblerMemory){
		String assembly=mainFile.getFile().getAssemblyCode();
		// replace binary, hexadecimal and octal numbers (in strings) into decimal numbers (in strings)
		
		// change the escaped caracters into ints and into chars (\0->'0'->0,\n->13)
		
		// change the variables to constants (their addresses)
		
		// replace the constant registers into their address
		
		mainFile.getFile().setAssemblyCode(assembly);
		return mainFile;
	}
}