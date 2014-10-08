package com.core.qassembler.translator;

import java.util.ArrayList;
import java.util.List;
import com.core.qassembler.constants.QConstants;
import com.core.qassembler.dictionary.InstructionDictionary;
import com.core.qassembler.translator.extractor.Extractor;
import com.utils.Misc;

public class InstructionTranslator implements QConstants{
	
	private InstructionDictionary ins_dictionary;
	private Extractor extractor;
	
	public InstructionTranslator(){
		ins_dictionary=new InstructionDictionary();
		extractor=new Extractor();
	}
	
	private boolean typesValid(String [] ops){
		try{
			int typeSize=TYPES_SIZES[extractor.getTypeExtractor().extract(ops[1])][1];
			for(int i=0;i<ops.length-1;i++){
				int opSize=TYPES_SIZES[extractor.getTypeExtractor().extract(ops[i+1])][1];
				if(typeSize!=opSize) return false;
				else typeSize=opSize;
			}
		}catch(Exception e){}
		return true;
	}
	
	public List<Object> isInstructionValid(String instruction,int programcounter){
		List<Object> validate=new ArrayList<Object>();
		validate.add(false);
		validate.add("Instruction '"+instruction+"' (line "+programcounter+") not valid!");
		
		// USE CLASS INSTRUTION DICTIONARY TO DETERMINE WHETHER OR NOT THE INSTRUCTION IS VALID
		String ops[]=extractor.getOperandExtractor().extract(instruction);
		if(ins_dictionary.instructionExists(ops[0])){ // DOES THE FUNCTION EXIST?
			int operandCountNecessary=ins_dictionary.getInstructionWidth(ops[0]);
			int operandCountGiven=ops.length-1;
			if(operandCountNecessary==operandCountGiven) // DOES THE SIZE MATCH?
				if(typesValid(ops)){ //TODO: DO THE TYPES AND TYPE SIZES ALSO MATCH?
					validate.set(0,true);
					validate.set(1,"Instruction '"+instruction+"' (line "+programcounter+") validated!");
				}else validate.set(1,"Instruction '"+instruction+"' (line "+programcounter+"). The operand sizes do not match each other.");
			else validate.set(1,"Function '"+ops[0]+"' (line "+programcounter+") has invalid size. You gave "+operandCountGiven+" operand"+(operandCountGiven==1?"":"s")+". It needs "+operandCountNecessary+".");
		}else validate.set(1,"Function '"+ops[0]+"' (line "+programcounter+") is unrecognizable!");
		return validate;
	}
	
	public Integer[] translate(String instruction,int programcounter) throws Exception{
		// DO RAD STUFF HERE
		System.out.println(instruction);
		return new Integer[]{0};
	}
	
	public Integer[] getEOFoperands(){
		return new Integer[]{(Integer)ins_dictionary.getInstructionCode("HLT"),0,0,0};
	}
}