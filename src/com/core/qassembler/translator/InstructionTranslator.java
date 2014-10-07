package com.core.qassembler.translator;

import com.core.qassembler.constants.QConstants;
import com.core.qassembler.dictionary.InstructionDictionary;
import com.core.qassembler.memory.Memory;
import com.core.qassembler.translator.extractor.Extractor;
import com.utils.Misc;

public class InstructionTranslator implements QConstants{
	
	private InstructionDictionary ins_dictionary;
	private Memory asmMem;
	private Extractor extractor;
	
	public InstructionTranslator(Memory assemblerMemory){
		ins_dictionary=new InstructionDictionary();
		asmMem=assemblerMemory;
		extractor=new Extractor();
	}
	
	public boolean isInstructionValid(String instruction){
		boolean valid=false;
		// USE CLASS INSTRUTION DICTIONARY TO DETERMINE WHETHER OR NOT THE INSTRUCTION IS VALID
		String ops[]=extractor.getOperandExtractor().extract(instruction);
		
		System.out.println(instruction+">"+Misc.arrToString(ops));
		return valid;
	}
	
	public Integer[] translate(String instruction,int programcounter) throws Exception{
		// DO RAD STUFF HERE
		
		return null;
	}
	
	public Integer[] getEOFoperands(){
		return new Integer[]{(Integer)ins_dictionary.getInstructionCode("HLT"),0,0,0};
	}
}