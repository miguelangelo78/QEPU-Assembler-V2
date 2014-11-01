package com.core.qassembler.dictionary;

import java.util.HashMap;
import com.core.qassembler.constants.Global_Constants;

public class InstructionDictionary implements Global_Constants{
	@SuppressWarnings("serial")
	private static HashMap<String,Object[]> instructionDictionary=new HashMap<String,Object[]>(){{
        //INSTRUCTION SET AND OPERAND TYPES:
		
		//SINTAX:
		//put("function_name",new Object[]{new int[]{x,y},new int[]{z,a,b},new int[]{c,d},new int[]{e,f,g,h}});
		//FORMAT: OPERAND COUNT,FUNCTION CODE,OPERAND 1 TYPES,OPERAND 2 TYPES, OPERAND 3 TYPES (ALL 3 ARE OPTIONAL)
		
		put("NOP",		new Object[]{new int[]{0,0}});
        put("MOV",		new Object[]{new int[]{2,1},new int[]{MEMORYCONTAINER,QREGISTER,CREGISTER,CREGISTER_POINTER,QREGISTER_POINTER,QUBIT,QUBIT_THETA,QUBIT_PHI,STACK_HEAD,STACK_BASE},new int[]{MEMORYCONTAINER,QREGISTER,CREGISTER,CREGISTER_POINTER,QREGISTER_POINTER,QUBIT,QUBIT_THETA,QUBIT_PHI,CONSTANT,STACK_HEAD,STACK_BASE}});
        put("POP",		new Object[]{new int[]{1,2},new int[]{MEMORYCONTAINER,QREGISTER,CREGISTER,CREGISTER_POINTER,QREGISTER_POINTER}});
        put("POPA",		new Object[]{new int[]{0,3}});
        put("POPAQ",	new Object[]{new int[]{0,4}});
        put("PUSH",		new Object[]{new int[]{1,5},new int[]{MEMORYCONTAINER,QREGISTER,CREGISTER,CONSTANT,CREGISTER_POINTER,QREGISTER_POINTER}});
        put("PUSHA",	new Object[]{new int[]{0,6}});
        put("PUSHAQ",	new Object[]{new int[]{0,7}});
        put("CMP",		new Object[]{new int[]{2,8},new int[]{QREGISTER,CREGISTER,MEMORYCONTAINER,CONSTANT},new int[]{QREGISTER,CREGISTER,MEMORYCONTAINER,CONSTANT}});
        put("SET",		new Object[]{new int[]{1,9},new int[]{CONSTANT}});
        put("GET",		new Object[]{new int[]{1,10},new int[]{CONSTANT}});
        put("BRC",		new Object[]{new int[]{1,11},new int[]{CONSTANT,QREGISTER,CREGISTER}});
        put("BLW",		new Object[]{new int[]{1,12},new int[]{CONSTANT,QREGISTER,CREGISTER}});
        put("BLE",		new Object[]{new int[]{1,13},new int[]{CONSTANT,QREGISTER,CREGISTER}});
        put("BEQ",		new Object[]{new int[]{1,14},new int[]{CONSTANT,QREGISTER,CREGISTER}});
        put("BGE",		new Object[]{new int[]{1,15},new int[]{CONSTANT,QREGISTER,CREGISTER}});
        put("BGR",		new Object[]{new int[]{1,16},new int[]{CONSTANT,QREGISTER,CREGISTER}});
        put("BDI",		new Object[]{new int[]{1,17},new int[]{CONSTANT,QREGISTER,CREGISTER}});
        put("BZE",		new Object[]{new int[]{1,18},new int[]{CONSTANT,QREGISTER,CREGISTER}});
        put("BNZ",		new Object[]{new int[]{1,19},new int[]{CONSTANT,QREGISTER,CREGISTER}});
        put("CALL",		new Object[]{new int[]{1,20},new int[]{CONSTANT,QREGISTER,CREGISTER}});
        put("RET",		new Object[]{new int[]{0,21}});
        put("JMP",		new Object[]{new int[]{1,22},new int[]{CONSTANT,QREGISTER,CREGISTER}});
        put("ADD",		new Object[]{new int[]{3,23},new int[]{QREGISTER,CREGISTER},new int[]{QREGISTER,CREGISTER,CONSTANT},new int[]{QREGISTER,CREGISTER,CONSTANT}});
        put("SUB",		new Object[]{new int[]{3,24},new int[]{QREGISTER,CREGISTER},new int[]{QREGISTER,CREGISTER,CONSTANT},new int[]{QREGISTER,CREGISTER,CONSTANT}});
        put("MUL",		new Object[]{new int[]{3,25},new int[]{QREGISTER,CREGISTER},new int[]{QREGISTER,CREGISTER,CONSTANT},new int[]{QREGISTER,CREGISTER,CONSTANT}});
        put("DIV",		new Object[]{new int[]{3,26},new int[]{QREGISTER,CREGISTER},new int[]{QREGISTER,CREGISTER,CONSTANT},new int[]{QREGISTER,CREGISTER,CONSTANT}});
        put("INC",		new Object[]{new int[]{1,27},new int[]{QREGISTER,CREGISTER}});
        put("DEC",		new Object[]{new int[]{1,28},new int[]{QREGISTER,CREGISTER}});
        put("ABS",		new Object[]{new int[]{2,29},new int[]{QREGISTER,CREGISTER},new int[]{QREGISTER,CREGISTER,CONSTANT}});
        put("MOD",		new Object[]{new int[]{3,30},new int[]{QREGISTER,CREGISTER},new int[]{QREGISTER,CREGISTER,CONSTANT},new int[]{QREGISTER,CREGISTER,CONSTANT}});
        put("AND",		new Object[]{new int[]{3,31},new int[]{QREGISTER,CREGISTER},new int[]{QREGISTER,CREGISTER,CONSTANT},new int[]{QREGISTER,CREGISTER,CONSTANT}});
        put("OR",		new Object[]{new int[]{3,32},new int[]{QREGISTER,CREGISTER},new int[]{QREGISTER,CREGISTER,CONSTANT},new int[]{QREGISTER,CREGISTER,CONSTANT}});
        put("NOR",		new Object[]{new int[]{3,33},new int[]{QREGISTER,CREGISTER},new int[]{QREGISTER,CREGISTER,CONSTANT},new int[]{QREGISTER,CREGISTER,CONSTANT}});
        put("XOR",		new Object[]{new int[]{3,34},new int[]{QREGISTER,CREGISTER},new int[]{QREGISTER,CREGISTER,CONSTANT},new int[]{QREGISTER,CREGISTER,CONSTANT}});
        put("NAND",		new Object[]{new int[]{3,35},new int[]{QREGISTER,CREGISTER},new int[]{QREGISTER,CREGISTER,CONSTANT},new int[]{QREGISTER,CREGISTER,CONSTANT}});
        put("NOT",		new Object[]{new int[]{2,36},new int[]{QREGISTER,CREGISTER},new int[]{QREGISTER,CREGISTER,CONSTANT},new int[]{QREGISTER,CREGISTER,CONSTANT}});
        put("SHL",		new Object[]{new int[]{3,37},new int[]{QREGISTER,CREGISTER},new int[]{QREGISTER,CREGISTER,CONSTANT},new int[]{QREGISTER,CREGISTER,CONSTANT}});
        put("SHR",		new Object[]{new int[]{3,38},new int[]{QREGISTER,CREGISTER},new int[]{QREGISTER,CREGISTER,CONSTANT},new int[]{QREGISTER,CREGISTER,CONSTANT}});
        put("NEG",		new Object[]{new int[]{2,39},new int[]{QREGISTER,CREGISTER},new int[]{QREGISTER,CREGISTER,CONSTANT}});
        put("ROL",		new Object[]{new int[]{3,40},new int[]{QREGISTER,CREGISTER},new int[]{QREGISTER,CREGISTER,CONSTANT},new int[]{QREGISTER,CREGISTER,CONSTANT}});
        put("ROR",		new Object[]{new int[]{3,41},new int[]{QREGISTER,CREGISTER},new int[]{QREGISTER,CREGISTER,CONSTANT},new int[]{QREGISTER,CREGISTER,CONSTANT}});
        put("INT",		new Object[]{new int[]{1,42},new int[]{CONSTANT}});
        put("DELAY",	new Object[]{new int[]{1,43},new int[]{CONSTANT}});
        put("HALT",		new Object[]{new int[]{0,44}});
        put("X",		new Object[]{new int[]{1,45},new int[]{QUBIT}});
        put("Y",		new Object[]{new int[]{1,46},new int[]{QUBIT}});
        put("Z",		new Object[]{new int[]{1,47},new int[]{QUBIT}});
        put("H",		new Object[]{new int[]{1,48},new int[]{QUBIT}});
        put("S",		new Object[]{new int[]{1,49},new int[]{QUBIT}});
        put("T",		new Object[]{new int[]{1,50},new int[]{QUBIT}});
        put("ROX",		new Object[]{new int[]{2,51},new int[]{QUBIT},new int[]{CONSTANT}});
        put("ROY",		new Object[]{new int[]{2,52},new int[]{QUBIT},new int[]{CONSTANT}});
        put("ROZ",		new Object[]{new int[]{2,53},new int[]{QUBIT},new int[]{CONSTANT}});
        put("CNOT",		new Object[]{new int[]{2,54},new int[]{QUBIT},new int[]{QUBIT}});
        put("CSIGN",	new Object[]{new int[]{2,55},new int[]{QUBIT},new int[]{QUBIT}});
        put("SWAP",		new Object[]{new int[]{2,56},new int[]{QUBIT},new int[]{QUBIT}});
        put("QINC",		new Object[]{new int[]{2,57},new int[]{QUBIT},new int[]{QUBIT}});
        put("QDEC",		new Object[]{new int[]{2,58},new int[]{QUBIT},new int[]{QUBIT}});
        put("SWAPQ",	new Object[]{new int[]{2,59},new int[]{QUBIT},new int[]{QUBIT}});
        put("SWAPI",	new Object[]{new int[]{2,60},new int[]{QUBIT},new int[]{QUBIT}});
        put("CSWAP",	new Object[]{new int[]{3,61},new int[]{QUBIT},new int[]{QUBIT},new int[]{QUBIT}});
        put("TOFF",		new Object[]{new int[]{3,62},new int[]{QUBIT},new int[]{QUBIT},new int[]{QUBIT}});
        put("DEU",		new Object[]{new int[]{3,63},new int[]{QUBIT},new int[]{QUBIT},new int[]{QUBIT}});
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
