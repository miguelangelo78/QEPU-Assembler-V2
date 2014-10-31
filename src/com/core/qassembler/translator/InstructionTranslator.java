package com.core.qassembler.translator;

import java.util.ArrayList;
import java.util.List;

import com.core.qassembler.constants.Global_Constants;
import com.core.qassembler.dictionary.InstructionDictionary;
import com.core.qassembler.preassembler.replacements.CastCapper;
import com.core.qassembler.translator.extractor.Extractor;
import com.utils.Misc;

public class InstructionTranslator implements Global_Constants{
	
	private InstructionDictionary ins_dictionary;
	private Extractor extractor;
	
	public InstructionTranslator(){
		ins_dictionary=new InstructionDictionary();
		extractor=new Extractor();
	}
	
	private boolean typesValid(String [] ops){
		// CHECK IF THE SIZES ARE COMPATIBLE:
		try{
			int typeSize=0;
			String first_op=ops[1];
			if(CastCapper.usingCast(first_op)) typeSize=CastCapper.getCastSize(first_op); // APPLY CAST TO THE OPERAND
			else typeSize=TYPES_SIZES[extractor.getTypeExtractor().extract(ops[1])][1]; // FETCH THE SIZE OF THE OPERAND 1
			
			for(int i=1;i<ops.length-1;i++){
				String op_next=ops[i+1];
				int op_next_size=0;
				if(CastCapper.usingCast(op_next)) op_next_size=CastCapper.getCastSize(op_next);  // APPLY CAST TO THE OPERAND
				else op_next_size=TYPES_SIZES[extractor.getTypeExtractor().extract(ops[i+1])][1];
				
				//SEE IF THE OPERAND 1 IS COMPATIBLE WITH ALL THE OTHER OPERANDS
				if(typeSize!=op_next_size) return false;
				else typeSize=op_next_size;
			}
		}catch(Exception e){}
		
		// REMOVE CASTS ONLY FOR THE VALIDATION:
		for(int i=1;i<ops.length;i++) ops[i]=CastCapper.removeCast(ops[i]); // REMOVE CASTS FROM THE OPERAND:
		
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
		Object []extraction_ofops=extractor.getOperandExtractor().extract(instruction); // FETCH OPERANDS
		String ops[]=(String[]) extraction_ofops[0]; // FETCH OPERANDS
		int ops_realcount=(int) extraction_ofops[1]; // FETCH REAL OPERAND COUNT
		
		if(ins_dictionary.instructionExists(ops[0])){ // DOES THE FUNCTION EXIST?
			int operandCountNecessary=ins_dictionary.getInstructionWidth(ops[0]);
			int operandCountGiven=ops_realcount;
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
		Object []extraction_ofops=extractor.getOperandExtractor().extract(instruction); // FETCH OPERANDS
		String ops[]=(String[]) extraction_ofops[0]; // FETCH OPERANDS
		int ops_realcount=(int) extraction_ofops[1]; // FETCH REAL OPERAND COUNT
		
		// REMOVE CASTS AND CAP THE OPERANDS:
		try{ ops[1]=CastCapper.removeCast(ops[1]); }catch(Exception e){} // THE 1ST OPERAND DOESN'T GET CAPPED, IF IT'S USING CAST
		for(int i=2;i<ops_realcount;i++) ops[i]=CastCapper.cap(ops[i]);
		
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
}