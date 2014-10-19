package com.core.qassembler.translator;

import com.core.qassembler.constants.QConstants;
import com.core.qassembler.dictionary.InstructionDictionary;
import com.core.qassembler.translator.extractor.Extractor;

public class InstructionManualTranslator implements QConstants{
	
	private Extractor extractor;
	private InstructionDictionary ins_dictionary;
	
	public InstructionManualTranslator(){
		extractor=new Extractor();
		ins_dictionary=new InstructionDictionary();
	}
	
	public int translate(String [] ops,String instruction,int programcounter) throws Exception{
		int functionCode=-1;
		/*Integer [] types=extractor.getTypeExtractor().extractAll(ops);
		switch(ops[0]){
			case "MOV": 
				if(types[0]==MEMORYCONTAINER && types[1]==MEMORYCONTAINER)
					functionCode=ins_dictionary.getInstructionCode("MOM");
				else 
				if(types[0]==MEMORYCONTAINER && types[1]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("STORE");
				else
				if(types[0]==REGISTER && types[1]==MEMORYCONTAINER)
					functionCode=ins_dictionary.getInstructionCode("LOAD");
				else
				if(types[0]==REGISTER && types[1]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("MOR");
				else
				if(types[0]==MEMORYCONTAINER && types[1]==CONSTANT)
					functionCode=ins_dictionary.getInstructionCode("CMW");
				else
				if(types[0]==REGISTER && types[1]==REGISTER_POINTER)
					functionCode=ins_dictionary.getInstructionCode("MORI");
				else
				if(types[0]==REGISTER_POINTER && types[1]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("MORD");
				else
				if(types[0]==REGISTER && types[1]==CONSTANT)
					functionCode=ins_dictionary.getInstructionCode("CRW");
				else
				if(types[0]==QUBIT && types[1]==CONSTANT){
					// function not created yet
				}else
				if(types[0]==QUBIT && types[1]==QUBIT)
					functionCode=ins_dictionary.getInstructionCode("MOQ");
				else
				if(types[0]==QUBIT_THETA && types[1]==QUBIT_PHI){
					// function not created yet
				}else
				if(types[0]==QUBIT_PHI && types[1]==QUBIT_THETA){
					// function not created yet
				}else
				if(types[0]==QUBIT_THETA && types[1]==QUBIT_THETA){
					// function not created yet
				}else
				if(types[0]==QUBIT_PHI && types[1]==QUBIT_PHI){
					// function not created yet
				}else
				if(types[0]==QUBIT_THETA && types[1]==CONSTANT)
					functionCode=ins_dictionary.getInstructionCode("CMTH");
				else
				if(types[0]==QUBIT_PHI && types[1]==CONSTANT)
					functionCode=ins_dictionary.getInstructionCode("CMPH");
				else throw new Exception("Instruction '"+instruction+"' (line "+programcounter+"). The operand sizes do not match/are incompatible with each other.");
				break;
			case "ADD": 
				if(types[1]==REGISTER && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("ADD_");
				else
				if(types[1]==REGISTER && types[2]==CONSTANT)
					functionCode=ins_dictionary.getInstructionCode("ADD_RK");
				else
				if(types[1]==CONSTANT && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("ADD_RK");
				break;
			case "MOD":
				if(types[1]==REGISTER && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("MOD_");
				else
				if(types[1]==REGISTER && types[2]==CONSTANT)
					functionCode=ins_dictionary.getInstructionCode("MOD_RK");
				else
				if(types[1]==CONSTANT && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("MOD_KR");
				break;
			case "ROL":
				if(types[1]==REGISTER && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("ROL_");
				else
				if(types[1]==REGISTER && types[2]==CONSTANT)
					functionCode=ins_dictionary.getInstructionCode("ROL_RK");
				else
				if(types[1]==CONSTANT && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("ROL_KR");
				break;
			case "ROR":
				if(types[1]==REGISTER && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("ROR_");
				else
				if(types[1]==REGISTER && types[2]==CONSTANT)
					functionCode=ins_dictionary.getInstructionCode("ROR_RK");
				else
				if(types[1]==CONSTANT && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("ROR_KR");
				break;
			case "SUB": 
				if(types[1]==REGISTER && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("SUB_");
				else
				if(types[1]==REGISTER && types[2]==CONSTANT)
					functionCode=ins_dictionary.getInstructionCode("SUB_RK");
				else
				if(types[1]==CONSTANT && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("SUB_KR");
				break;
			case "MUL": 
				if(types[1]==REGISTER && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("MUL_");
				else
				if(types[1]==REGISTER && types[2]==CONSTANT)
					functionCode=ins_dictionary.getInstructionCode("MUL_RK");
				else
				if(types[1]==CONSTANT && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("MUL_KR");
				break;
			case "DIV": 
				if(types[1]==REGISTER && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("DIV_");
				else
				if(types[1]==REGISTER && types[2]==CONSTANT)
					functionCode=ins_dictionary.getInstructionCode("DIV_RK");
				else
				if(types[1]==CONSTANT && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("DIV_KR");
				break;
			case "AND": 
				if(types[1]==REGISTER && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("AND_");
				else
				if(types[1]==REGISTER && types[2]==CONSTANT)
					functionCode=ins_dictionary.getInstructionCode("AND_RK");
				else
				if(types[1]==CONSTANT && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("AND_KR");
				break;
			case "OR":  
				if(types[1]==REGISTER && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("OR_");
				else
				if(types[1]==REGISTER && types[2]==CONSTANT)
					functionCode=ins_dictionary.getInstructionCode("OR_RK");
				else
				if(types[1]==CONSTANT && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("OR_KR");
				break;
			case "NOR": 
				if(types[1]==REGISTER && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("NOR_");
				else
				if(types[1]==REGISTER && types[2]==CONSTANT)
					functionCode=ins_dictionary.getInstructionCode("NOR_RK");
				else
				if(types[1]==CONSTANT && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("NOR_KR");
				break;
			case "XOR": 
				if(types[1]==REGISTER && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("XOR_");
				else
				if(types[1]==REGISTER && types[2]==CONSTANT)
					functionCode=ins_dictionary.getInstructionCode("XOR_RK");
				else
				if(types[1]==CONSTANT && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("XOR_KR");
				break;
			case "NAND": 
				if(types[1]==REGISTER && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("NAND_");
				else
				if(types[1]==REGISTER && types[2]==CONSTANT)
					functionCode=ins_dictionary.getInstructionCode("NAND_RK");
				else
				if(types[1]==CONSTANT && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("NAND_KR");
				break;
			case "SHL": 
				if(types[1]==REGISTER && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("SHL_");
				else
				if(types[1]==REGISTER && types[2]==CONSTANT)
					functionCode=ins_dictionary.getInstructionCode("SHL_RK");
				else
				if(types[1]==CONSTANT && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("SHL_KR");
				break;
			case "SHR":
				if(types[1]==REGISTER && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("SHR_");
				else
				if(types[1]==REGISTER && types[2]==CONSTANT)
					functionCode=ins_dictionary.getInstructionCode("SHR_RK");
				else
				if(types[1]==CONSTANT && types[2]==REGISTER)
					functionCode=ins_dictionary.getInstructionCode("SHR_KR");
				break;
			default:
		}*/
		return 0;
		//return functionCode;
	}
}