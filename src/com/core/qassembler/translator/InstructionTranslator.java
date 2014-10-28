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
		// CHECK IF THE SIZES ARE COMPATIBLE:
		try{
			int typeSize=TYPES_SIZES[extractor.getTypeExtractor().extract(ops[1])][1];
			for(int i=0;i<ops.length-1;i++){
				int opSize=TYPES_SIZES[extractor.getTypeExtractor().extract(ops[i+1])][1];
				if(typeSize!=opSize) return false;
				else typeSize=opSize;
			}
		}catch(Exception e){}
		// CHECK IF THE TYPES ARE COMPATIBLE:
		try{
			Object[] instructionObject=ins_dictionary.getInstruction(ops[0]);
			for(int i=1;i<instructionObject.length;i++) //SEE IF THE OPERAND TYPE 'I' IS INSIDE ARRAY VALIDTYPES
				if(!Misc.arrayContains((int[])instructionObject[i],extractor.getTypeExtractor().extract(ops[i]))) return false;
		}catch(Exception e){ }
		// EVERYTHING IS VALID AT THIS POINT
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
				}else validate.set(1,"Instruction '"+instruction+"' (line "+programcounter+"). The operand sizes do not match/are incompatible with each other.");
			else validate.set(1,"Function '"+ops[0]+"' (line "+programcounter+") has invalid size. You gave "+operandCountGiven+" operand"+(operandCountGiven==1?"":"s")+". It needs "+operandCountNecessary+".");
		}else validate.set(1,"Function '"+ops[0]+"' (line "+programcounter+") is unrecognizable!");
		return validate;
	}
	
	public long[] translate(String instruction,int programcounter) throws Exception{
		long functionCode=0,op1=0,op2=0,op3=0,meta1=0,meta2=0,meta3=0;
		String [] ops=extractor.getOperandExtractor().extract(instruction); // FETCH OPERANDS
		
		//FETCH OPERAND METADATA (CURRENTLY IT'S JUST THEIR TYPE)
		try{ meta1=extractor.getTypeExtractor().getOperandMetadata(ops[1]); }catch(Exception e){}
		try{ meta2=extractor.getTypeExtractor().getOperandMetadata(ops[2]); }catch(Exception e){}
		try{ meta3=extractor.getTypeExtractor().getOperandMetadata(ops[3]); }catch(Exception e){}
		
		// TURN THEM INTO OPERANDS
		functionCode=ins_dictionary.getInstructionCode(ops[0]);
		try{ op1=Misc.extractNumber(ops[1]); }catch(Exception e){}
		try{ op2=Misc.extractNumber(ops[2]); }catch(Exception e){}
		try{ op3=Misc.extractNumber(ops[3]); }catch(Exception e){}
		
		return new long[]{functionCode,op1,meta1,op2,meta2,op3,meta3};
	}
	
	public long[] getEOFoperands(){
		return new long[]{(long)ins_dictionary.getInstructionCode("HALT"),0,0,0,0,0,0};
	}
}