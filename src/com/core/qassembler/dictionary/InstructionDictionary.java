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
		
		put("",			new Object[]{new int[]{0}}); // THIS IS AN EMPTY LINE AND NEEDS TO BE IGNORED
		put("MOV",		new Object[]{new int[]{2,-1},new int[]{MEMORYCONTAINER,REGISTER,QUBIT},new int[]{MEMORYCONTAINER,REGISTER,QUBIT,CONSTANT}});
        put("MOQ",		new Object[]{new int[]{2,1},new int[]{QUBIT},new int[]{QUBIT}});
        put("MOR",		new Object[]{new int[]{2,2},new int[]{REGISTER},new int[]{REGISTER}});
        put("MOM",		new Object[]{new int[]{2,3},new int[]{MEMORYCONTAINER},new int[]{MEMORYCONTAINER}});
        put("STORE",	new Object[]{new int[]{2,4},new int[]{MEMORYCONTAINER},new int[]{REGISTER}});
        put("LOAD",		new Object[]{new int[]{2,5},new int[]{REGISTER},new int[]{MEMORYCONTAINER}});
        put("CMW",		new Object[]{new int[]{2,6},new int[]{MEMORYCONTAINER},new int[]{CONSTANT}});
        put("CRW",		new Object[]{new int[]{2,7},new int[]{REGISTER},new int[]{CONSTANT}});
        put("POP",		new Object[]{new int[]{1,8},new int[]{REGISTER}});
        put("PUSH",		new Object[]{new int[]{1,9},new int[]{REGISTER}});
        put("CMTH",		new Object[]{new int[]{2,10},new int[]{QUBIT_THETA},new int[]{CONSTANT}});
        put("CMPH",		new Object[]{new int[]{2,11},new int[]{QUBIT_PHI},new int[]{CONSTANT}});
        put("CMP",		new Object[]{new int[]{2,12},new int[]{REGISTER},new int[]{REGISTER}});
        put("SEA",		new Object[]{new int[]{1,13},new int[]{CONSTANT}});
        put("GEA",		new Object[]{new int[]{1,14},new int[]{CONSTANT}});
        put("BES",		new Object[]{new int[]{1,15},new int[]{CONSTANT}});
        put("BLW",		new Object[]{new int[]{1,16},new int[]{CONSTANT}});
        put("BLE",		new Object[]{new int[]{1,17},new int[]{CONSTANT}});
        put("BEQ",		new Object[]{new int[]{1,18},new int[]{CONSTANT}});
        put("BGE",		new Object[]{new int[]{1,19},new int[]{CONSTANT}});
        put("BGR",		new Object[]{new int[]{1,20},new int[]{CONSTANT}});
        put("BDI",		new Object[]{new int[]{1,21},new int[]{CONSTANT}});
        put("BZE",		new Object[]{new int[]{1,22},new int[]{CONSTANT}});
        put("BNZ",		new Object[]{new int[]{1,23},new int[]{CONSTANT}});
        put("CALL",		new Object[]{new int[]{1,24},new int[]{CONSTANT}});
        put("RET",		new Object[]{new int[]{0,25}});
        put("JMP",		new Object[]{new int[]{1,26},new int[]{CONSTANT}});
        put("ADD",		new Object[]{new int[]{3,-1},new int[]{REGISTER},new int[]{REGISTER,CONSTANT},new int[]{REGISTER,CONSTANT}});
        put("ADD_",		new Object[]{new int[]{3,27},new int[]{REGISTER},new int[]{REGISTER},new int[]{REGISTER}});
        put("ADD_RK",	new Object[]{new int[]{3,28},new int[]{REGISTER},new int[]{REGISTER},new int[]{CONSTANT}});
        put("SUB",		new Object[]{new int[]{3,-1},new int[]{REGISTER},new int[]{REGISTER,CONSTANT},new int[]{REGISTER,CONSTANT}});
        put("SUB_",		new Object[]{new int[]{3,29},new int[]{REGISTER},new int[]{REGISTER},new int[]{REGISTER}});
        put("SUB_RK",	new Object[]{new int[]{3,30},new int[]{REGISTER},new int[]{REGISTER},new int[]{CONSTANT}});
        put("SUB_KR",	new Object[]{new int[]{3,31},new int[]{REGISTER},new int[]{CONSTANT},new int[]{REGISTER}});
        put("MUL",		new Object[]{new int[]{3,-1},new int[]{REGISTER},new int[]{REGISTER,CONSTANT},new int[]{REGISTER,CONSTANT}});
        put("MUL_",		new Object[]{new int[]{3,32},new int[]{REGISTER},new int[]{REGISTER},new int[]{REGISTER}});
        put("MUL_RK",	new Object[]{new int[]{3,33},new int[]{REGISTER},new int[]{REGISTER},new int[]{CONSTANT}});
        put("DIV",		new Object[]{new int[]{3,-1},new int[]{REGISTER},new int[]{REGISTER,CONSTANT},new int[]{REGISTER,CONSTANT}});
        put("DIV_",		new Object[]{new int[]{3,34},new int[]{REGISTER},new int[]{REGISTER},new int[]{REGISTER}});
        put("DIV_RK",	new Object[]{new int[]{3,35},new int[]{REGISTER},new int[]{REGISTER},new int[]{CONSTANT}});
        put("DIV_KR",	new Object[]{new int[]{3,36},new int[]{REGISTER},new int[]{CONSTANT},new int[]{REGISTER}});
        put("AND",		new Object[]{new int[]{3,-1},new int[]{REGISTER},new int[]{REGISTER,CONSTANT},new int[]{REGISTER,CONSTANT}});
        put("AND_",		new Object[]{new int[]{3,37},new int[]{REGISTER},new int[]{REGISTER},new int[]{REGISTER}});
        put("AND_RK",	new Object[]{new int[]{3,38},new int[]{REGISTER},new int[]{REGISTER},new int[]{CONSTANT}});
        put("AND_KR",	new Object[]{new int[]{3,39},new int[]{REGISTER},new int[]{CONSTANT},new int[]{REGISTER}});
        put("OR",		new Object[]{new int[]{3,-1},new int[]{REGISTER},new int[]{REGISTER,CONSTANT},new int[]{REGISTER,CONSTANT}});
        put("OR_",		new Object[]{new int[]{3,40},new int[]{REGISTER},new int[]{REGISTER},new int[]{REGISTER}});
        put("OR_RK",	new Object[]{new int[]{3,41},new int[]{REGISTER},new int[]{REGISTER},new int[]{CONSTANT}});
        put("OR_KR",	new Object[]{new int[]{3,42},new int[]{REGISTER},new int[]{CONSTANT},new int[]{REGISTER}});
        put("NOR",		new Object[]{new int[]{3,-1},new int[]{REGISTER},new int[]{REGISTER,CONSTANT},new int[]{REGISTER,CONSTANT}});
        put("NOR_",		new Object[]{new int[]{3,43},new int[]{REGISTER},new int[]{REGISTER},new int[]{REGISTER}});
        put("NOR_RK",	new Object[]{new int[]{3,44},new int[]{REGISTER},new int[]{REGISTER},new int[]{CONSTANT}});
        put("NOR_KR",	new Object[]{new int[]{3,45},new int[]{REGISTER},new int[]{CONSTANT},new int[]{REGISTER}});
        put("XOR",		new Object[]{new int[]{3,-1},new int[]{REGISTER},new int[]{REGISTER,CONSTANT},new int[]{REGISTER,CONSTANT}});
        put("XOR_",		new Object[]{new int[]{3,46},new int[]{REGISTER},new int[]{REGISTER},new int[]{REGISTER}});
        put("XOR_RK",	new Object[]{new int[]{3,47},new int[]{REGISTER},new int[]{REGISTER},new int[]{CONSTANT}});
        put("XOR_KR",	new Object[]{new int[]{3,48},new int[]{REGISTER},new int[]{CONSTANT},new int[]{REGISTER}});
        put("NAND",		new Object[]{new int[]{3,-1},new int[]{REGISTER},new int[]{REGISTER,CONSTANT},new int[]{REGISTER,CONSTANT}});
        put("NAND_",	new Object[]{new int[]{3,49},new int[]{REGISTER},new int[]{REGISTER},new int[]{REGISTER}});
        put("NAND_RK",	new Object[]{new int[]{3,50},new int[]{REGISTER},new int[]{REGISTER},new int[]{CONSTANT}});
        put("NAND_KR",	new Object[]{new int[]{3,51},new int[]{REGISTER},new int[]{CONSTANT},new int[]{REGISTER}});
        put("NOT",		new Object[]{new int[]{2,52},new int[]{REGISTER},new int[]{REGISTER,CONSTANT},new int[]{REGISTER,CONSTANT}});
        put("SHL",		new Object[]{new int[]{3,-1},new int[]{REGISTER},new int[]{REGISTER,CONSTANT},new int[]{REGISTER,CONSTANT}});
        put("SHL_",		new Object[]{new int[]{3,53},new int[]{REGISTER},new int[]{REGISTER},new int[]{REGISTER}});
        put("SHL_RK",	new Object[]{new int[]{3,54},new int[]{REGISTER},new int[]{REGISTER},new int[]{CONSTANT}});
        put("SHL_KR",	new Object[]{new int[]{3,55},new int[]{REGISTER},new int[]{CONSTANT},new int[]{REGISTER}});
        put("SHR",		new Object[]{new int[]{3,-1},new int[]{REGISTER},new int[]{REGISTER,CONSTANT},new int[]{REGISTER,CONSTANT}});
        put("SHR_",		new Object[]{new int[]{3,56},new int[]{REGISTER},new int[]{REGISTER},new int[]{REGISTER}});
        put("SHR_RK",	new Object[]{new int[]{3,57},new int[]{REGISTER},new int[]{REGISTER},new int[]{CONSTANT}});
        put("SHR_KR",	new Object[]{new int[]{3,58},new int[]{REGISTER},new int[]{CONSTANT},new int[]{REGISTER}});
        put("INT",		new Object[]{new int[]{1,59},new int[]{CONSTANT}});
        put("DELAY",	new Object[]{new int[]{1,60},new int[]{CONSTANT}});
        put("NOP",		new Object[]{new int[]{0,61}});
        put("HLT",		new Object[]{new int[]{0,62}});
        put("X",		new Object[]{new int[]{1,63},new int[]{QUBIT}});
        put("Y",		new Object[]{new int[]{1,64},new int[]{QUBIT}});
        put("Z",		new Object[]{new int[]{1,65},new int[]{QUBIT}});
        put("H",		new Object[]{new int[]{1,66},new int[]{QUBIT}});
        put("S",		new Object[]{new int[]{1,67},new int[]{QUBIT}});
        put("T",		new Object[]{new int[]{1,68},new int[]{QUBIT}});
        put("ROX",		new Object[]{new int[]{2,69},new int[]{QUBIT},new int[]{CONSTANT}});
        put("ROY",		new Object[]{new int[]{2,70},new int[]{QUBIT},new int[]{CONSTANT}});
        put("ROZ",		new Object[]{new int[]{2,71},new int[]{QUBIT},new int[]{CONSTANT}});
        put("CNOT",		new Object[]{new int[]{2,72},new int[]{QUBIT},new int[]{QUBIT}});
        put("CSIGN",	new Object[]{new int[]{2,73},new int[]{QUBIT},new int[]{QUBIT}});
        put("SWAP",		new Object[]{new int[]{2,74},new int[]{QUBIT},new int[]{QUBIT}});
        put("INC",		new Object[]{new int[]{2,75},new int[]{QUBIT},new int[]{QUBIT}});
        put("DEC",		new Object[]{new int[]{2,76},new int[]{QUBIT},new int[]{QUBIT}});
        put("SWAPQ",	new Object[]{new int[]{2,77},new int[]{QUBIT},new int[]{QUBIT}});
        put("SWAPI",	new Object[]{new int[]{2,78},new int[]{QUBIT},new int[]{QUBIT}});
        put("CSWAP",	new Object[]{new int[]{3,79},new int[]{QUBIT},new int[]{QUBIT},new int[]{QUBIT}});
        put("TOFF",		new Object[]{new int[]{3,80},new int[]{QUBIT},new int[]{QUBIT},new int[]{QUBIT}});
        put("DEU",		new Object[]{new int[]{3,81},new int[]{QUBIT},new int[]{QUBIT},new int[]{QUBIT}});
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
