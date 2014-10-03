package com.core.qassembler.dictionary;

import java.util.HashMap;
import com.core.qassembler.constants.QConstants;

public class InstructionDictionary implements QConstants{
	@SuppressWarnings("serial")
	private static HashMap<String,int[]> instructionDictionary=new HashMap<String,int[]>(){{
        //INSTRUCTION SET AND OPERAND TYPES:
        put("",new int[]{FUNC,0}); // THIS IS AN EMPTY LINE AND NEEDS TO BE IGNORED
        put("MOV",new int[]{FUNC,2,-1});
        put("MOQ",new int[]{FUNC,2,1});
        put("MOR",new int[]{FUNC,2,2});
        put("MOM",new int[]{FUNC,2,3});
        put("STORE",new int[]{FUNC,2,4});
        put("LOAD",new int[]{FUNC,2,5});
        put("CRW",new int[]{FUNC,2,6});
        put("CQW*",new int[]{FUNC,2,7});
        put("POP",new int[]{FUNC,1,8});
        put("PUSH",new int[]{FUNC,1,9});
        put("CMTH",new int[]{FUNC,2,10});
        put("CMPH",new int[]{FUNC,2,11});
        put("CMP",new int[]{FUNC,2,12});
        put("SET",new int[]{FUNC,1,13});
        put("GET",new int[]{FUNC,1,14});
        put("BES",new int[]{FUNC,1,15});
        put("BLW",new int[]{FUNC,1,16});
        put("BLE",new int[]{FUNC,1,17});
        put("BEQ",new int[]{FUNC,1,18});
        put("BGE",new int[]{FUNC,1,19});
        put("BGR",new int[]{FUNC,1,20});
        put("BDI",new int[]{FUNC,1,21});
        put("BZE",new int[]{FUNC,1,22});
        put("BNZ",new int[]{FUNC,1,23});
        put("CALL",new int[]{FUNC,1,24});
        put("RET",new int[]{FUNC,0,25});
        put("JMP",new int[]{FUNC,1,26});
        put("ADD",new int[]{FUNC,3,27});
        put("SUB",new int[]{FUNC,3,28});
        put("MUL",new int[]{FUNC,3,29});
        put("DIV",new int[]{FUNC,3,30});
        put("AND",new int[]{FUNC,3,31});
        put("OR",new int[]{FUNC,3,32});
        put("NOR",new int[]{FUNC,3,33});
        put("XOR",new int[]{FUNC,3,34});
        put("NAND",new int[]{FUNC,3,35});
        put("NOT",new int[]{FUNC,2,36});
        put("SHL",new int[]{FUNC,3,37});
        put("SHR",new int[]{FUNC,3,38});
        put("INT",new int[]{FUNC,1,39});
        put("DELAY",new int[]{FUNC,1,40});
        put("NOP",new int[]{FUNC,0,41});
        put("HLT",new int[]{FUNC,0,42});
        put("X",new int[]{FUNC,1,43});
        put("Y",new int[]{FUNC,1,44});
        put("Z",new int[]{FUNC,1,45});
        put("H",new int[]{FUNC,1,46});
        put("S",new int[]{FUNC,1,47});
        put("T",new int[]{FUNC,1,48});
        put("ROX",new int[]{FUNC,1,49});
        put("ROY",new int[]{FUNC,1,50});
        put("ROZ",new int[]{FUNC,1,51});
        put("CNOT",new int[]{FUNC,2,52});
        put("CSIGN",new int[]{FUNC,2,53});
        put("SWAP",new int[]{FUNC,2,54});
        put("INC",new int[]{FUNC,2,55});
        put("DEC",new int[]{FUNC,2,56});
        put("SWAPQ",new int[]{FUNC,2,57});
        put("SWAPI",new int[]{FUNC,2,58});
        put("CSWAP",new int[]{FUNC,3,59});
        put("TOFF",new int[]{FUNC,3,60});
        put("DEU",new int[]{FUNC,3,61});
        put("R",new int[]{REGISTER,0,-1});
        put("Q",new int[]{QUBIT,0,-1});
        put("M",new int[]{MEMORY,0,-1});
        put("@",new int[]{FUNC,1,-1});  // LABELS
        put("$",new int[]{FUNC,3,-1});  // VARIABLES ($varname content size)
        put("K",new int[]{CONSTANT,0}); // MAYBE WILL USE THIS*/
    }};
    
    public int [] getInstructionByIndex(int index){
    	if(index>instructionDictionary.size() && index<instructionDictionary.size()) return null;
    	
    	for(String key:instructionDictionary.keySet()){
    		if(index<0) return instructionDictionary.get(key);
    		index--;
    	}
    	return null;
    }
    
    public int[] getInstruction(String instruction){
    	return instructionDictionary.get(instruction);
    }
    
    public int getInstructionType(String instruction){
    	return instructionDictionary.get(instruction)[0];
    } 
    
    public int getInstructionWidth(String instruction){
    	return instructionDictionary.get(instruction)[1];
    }
    
    public int getInstructionCode(String instruction){
		return instructionDictionary.get(instruction)[2];
    }
}
