package com.core.qassembler.dictionary;

import java.util.HashMap;
import com.core.qassembler.constants.QConstants;

public class InstructionDictionary implements QConstants{
	@SuppressWarnings("serial")
	private static HashMap<String,Object[]> instructionDictionary=new HashMap<String,Object[]>(){{
        //INSTRUCTION SET AND OPERAND TYPES:
		
		//SINTAX:
		//put("function_name",new Object[]{new int[]{x,y},new int[]{z,a,b},new int[]{c,d},new int[]{e,f,g,h}});
		//FORMAT: OPERAND COUNT,FUNCTION CODE,OPERAND 1 TYPES,OPERAND 2 TYPES, OPERAND 3 TYPES (ALL 3 ARE OPTIONAL)
		
		put("",new Object[]{new int[]{0}}); // THIS IS AN EMPTY LINE AND NEEDS TO BE IGNORED
		put("MOV",new Object[]{new int[]{2,-1},new int[]{MEMORYCONTAINER,REGISTER,QUBIT},new int[]{MEMORYCONTAINER,REGISTER,QUBIT,CONSTANT}});
        put("MOQ",new Object[]{new int[]{2,1},new int[]{},new int[]{}});
        put("MOR",new Object[]{new int[]{2,2},new int[]{},new int[]{}});
        put("MOM",new Object[]{new int[]{2,3},new int[]{},new int[]{}});
        put("STORE",new Object[]{new int[]{2,4},new int[]{},new int[]{}});
        put("LOAD",new Object[]{new int[]{2,5},new int[]{},new int[]{}});
        put("CRW",new Object[]{new int[]{2,6},new int[]{},new int[]{}});
        put("CQW",new Object[]{new int[]{2,7},new int[]{},new int[]{}});
        put("POP",new Object[]{new int[]{1,8},new int[]{}});
        put("PUSH",new Object[]{new int[]{1,9},new int[]{}});
        put("CMTH",new Object[]{new int[]{2,10},new int[]{},new int[]{}});
        put("CMPH",new Object[]{new int[]{2,11},new int[]{},new int[]{}});
        put("CMP",new Object[]{new int[]{2,12},new int[]{},new int[]{}});
        put("SET",new Object[]{new int[]{1,13},new int[]{}});
        put("GET",new Object[]{new int[]{1,14},new int[]{}});
        put("BES",new Object[]{new int[]{1,15},new int[]{}});
        put("BLW",new Object[]{new int[]{1,16},new int[]{}});
        put("BLE",new Object[]{new int[]{1,17},new int[]{}});
        put("BEQ",new Object[]{new int[]{1,18},new int[]{}});
        put("BGE",new Object[]{new int[]{1,19},new int[]{}});
        put("BGR",new Object[]{new int[]{1,20},new int[]{}});
        put("BDI",new Object[]{new int[]{1,21},new int[]{}});
        put("BZE",new Object[]{new int[]{1,22},new int[]{}});
        put("BNZ",new Object[]{new int[]{1,23},new int[]{}});
        put("CALL",new Object[]{new int[]{1,24},new int[]{}});
        put("RET",new Object[]{new int[]{0,25}});
        put("JMP",new Object[]{new int[]{1,26},new int[]{}});
        put("ADD",new Object[]{new int[]{3,27},new int[]{},new int[]{},new int[]{}});
        put("SUB",new Object[]{new int[]{3,28},new int[]{},new int[]{},new int[]{}});
        put("MUL",new Object[]{new int[]{3,29},new int[]{},new int[]{},new int[]{}});
        put("DIV",new Object[]{new int[]{3,30},new int[]{},new int[]{},new int[]{}});
        put("AND",new Object[]{new int[]{3,31},new int[]{},new int[]{},new int[]{}});
        put("OR",new Object[]{new int[]{3,32},new int[]{},new int[]{},new int[]{}});
        put("NOR",new Object[]{new int[]{3,33},new int[]{},new int[]{},new int[]{}});
        put("XOR",new Object[]{new int[]{3,34},new int[]{},new int[]{},new int[]{}});
        put("NAND",new Object[]{new int[]{3,35},new int[]{},new int[]{},new int[]{}});
        put("NOT",new Object[]{new int[]{2,36},new int[]{},new int[]{},new int[]{}});
        put("SHL",new Object[]{new int[]{3,37},new int[]{},new int[]{},new int[]{}});
        put("SHR",new Object[]{new int[]{3,38},new int[]{},new int[]{},new int[]{}});
        put("INT",new Object[]{new int[]{1,39},new int[]{}});
        put("DELAY",new Object[]{new int[]{1,40},new int[]{}});
        put("NOP",new Object[]{new int[]{0,41}});
        put("HLT",new Object[]{new int[]{0,42}});
        put("X",new Object[]{new int[]{1,43},new int[]{}});
        put("Y",new Object[]{new int[]{1,44},new int[]{}});
        put("Z",new Object[]{new int[]{1,45},new int[]{}});
        put("H",new Object[]{new int[]{1,46},new int[]{}});
        put("S",new Object[]{new int[]{1,47},new int[]{}});
        put("T",new Object[]{new int[]{1,48},new int[]{}});
        put("ROX",new Object[]{new int[]{1,49},new int[]{}});
        put("ROY",new Object[]{new int[]{1,50},new int[]{}});
        put("ROZ",new Object[]{new int[]{1,51},new int[]{}});
        put("CNOT",new Object[]{new int[]{2,52},new int[]{},new int[]{}});
        put("CSIGN",new Object[]{new int[]{2,53},new int[]{},new int[]{}});
        put("SWAP",new Object[]{new int[]{2,54},new int[]{}});
        put("INC",new Object[]{new int[]{2,55},new int[]{},new int[]{}});
        put("DEC",new Object[]{new int[]{2,56},new int[]{},new int[]{}});
        put("SWAPQ",new Object[]{new int[]{2,57},new int[]{},new int[]{}});
        put("SWAPI",new Object[]{new int[]{2,58},new int[]{},new int[]{}});
        put("CSWAP",new Object[]{new int[]{3,59},new int[]{},new int[]{},new int[]{}});
        put("TOFF",new Object[]{new int[]{3,60},new int[]{},new int[]{},new int[]{}});
        put("DEU",new Object[]{new int[]{3,61},new int[]{},new int[]{},new int[]{}});
    }};
    
    public Object [] getInstructionByIndex(int index){
    	if(index>instructionDictionary.size() && index<instructionDictionary.size()) return null;
    	
    	for(String key:instructionDictionary.keySet()){
    		if(index<0) return instructionDictionary.get(key);
    		index--;
    	}
    	return null;
    }
    
    public boolean instructionExists(String function){
    	return instructionDictionary.containsKey(function);
    }
    
    public Object[] getInstruction(String function){
    	return instructionDictionary.get(function);
    }
    
    public int getInstructionWidth(String function){
    	return ((int[])instructionDictionary.get(function)[0])[0];
    }
    
    public int getInstructionCode(String function){
		return ((int[])instructionDictionary.get(function)[0])[1];
    }
}
