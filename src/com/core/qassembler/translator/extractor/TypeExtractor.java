package com.core.qassembler.translator.extractor;

import java.util.ArrayList;
import java.util.List;

import com.core.qassembler.constants.QConstants;

public class TypeExtractor implements QConstants{

	public TypeExtractor() { }
	
	public int extract(String operand){
		int type=-1;
		if(operand.charAt(0)=='[' && operand.charAt(1)=='{') return CREGISTER_POINTER;
		if(operand.charAt(0)=='[' && operand.charAt(1)=='%') return QREGISTER_POINTER;
		
		switch(operand.charAt(0)){
			case '[': type=MEMORYCONTAINER; break;
			case 'M': case 'm': type=MEMORYCONTAINER; break;
			case 'R': case 'r': type=CREGISTER; break;
			case 'Q': case 'q': type=QREGISTER; break;
			case '{': type=CREGISTER; break;
			case '%': type=QREGISTER; break;
			case '<': type=QUBIT; break;
			case '|': type=QUBIT_THETA; break;
			case '!': type=QUBIT_PHI; break;
			case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':case '-':type=CONSTANT; break;
		}
		return type;
	}
	
	public byte getOperandMetadata(String operand){
		byte meta=0x0;

		if(operand.charAt(0)=='[' && operand.charAt(1)=='{') return CREGISTER_POINTER;
		if(operand.charAt(0)=='[' && operand.charAt(1)=='%') return QREGISTER_POINTER;
		
		switch(operand.charAt(0)){
			case '[': meta=META_MEM_CONTAINER; break;
			case 'M': case 'm': meta=META_MEM_CONTAINER; break;
			case 'R': case 'r': meta=META_CREGISTER; break;
			case 'Q': case 'q': meta=META_QREGISTER; break;
			case '{': meta=META_CREGISTER; break;
			case '%': meta=META_QREGISTER; break;
			case '<': meta=META_QUBIT; break;
			case '|': meta=META_QUBIT_THETA; break;
			case '!': meta=META_QUBIT_PHI; break;
			case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':case '-': meta=META_CONSTANT; break;
		}
		
		return meta;
	}
	
	public Integer[] extractAll(String[] ops){
		List<Integer> opTypes=new ArrayList<Integer>();
		for(int i=1;i<ops.length;i++) opTypes.add(extract(ops[i]));
		return opTypes.toArray(new Integer[opTypes.size()]);
	}
}