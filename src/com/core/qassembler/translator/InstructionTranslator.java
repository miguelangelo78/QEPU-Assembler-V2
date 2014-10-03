package com.core.qassembler.translator;

import com.core.qassembler.constants.QConstants;
import com.core.qassembler.dictionary.InstructionDictionary;
import com.core.qassembler.memory.Memory;

public class InstructionTranslator implements QConstants{
	
	private InstructionDictionary ins_dictionary;
	private Memory asmMem;
	
	public InstructionTranslator(Memory assemblerMemory){
		ins_dictionary=new InstructionDictionary();
		this.asmMem=assemblerMemory;
	}
	
	public boolean isInstructionValid(String instruction){
		boolean valid=false;
		// USE CLASS INSTRUTION DICTIONARY TO DETERMINE WHETHER OR NOT THE INSTRUCTION IS VALID
		return valid;
	}
	
	public String[] extractOperands(String instruction){
		
		return null;
	}
	
	public Integer[] translate(String instruction,int programcounter) throws Exception{
		// DO RAD STUFF HERE
		
		return null;
	}
	
	public Integer[] getEOFoperands(){
		return new Integer[]{(Integer)ins_dictionary.getInstructionCode("HLT"),0,0,0};
	}
}