package com.core.qassembler.translator.extractor;

import java.util.ArrayList;
import java.util.List;

import com.core.qassembler.constants.QConstants;

public class TypeExtractor implements QConstants{

	public TypeExtractor() { }
	
	public int extract(String operand){
		int type=-1;
		switch(operand.charAt(0)){
			case '[': type=MEMORYCONTAINER; break;
			case 'M': case 'm': type=MEMORYCONTAINER; break;
			case 'R': case 'r': type=REGISTER; break;
			case '{': type=REGISTER; break;
			case '<': type=QUBIT; break;
			case '|': type=QUBIT_THETA; break;
			case '!': type=QUBIT_PHI; break;
			case '#': type=REGISTER_POINTER; break;
			default: type=CONSTANT;
		}
		return type;
	}
	
	public Integer[] extractAll(String[] ops){
		List<Integer> opTypes=new ArrayList<Integer>();
		for(int i=1;i<ops.length;i++) opTypes.add(extract(ops[i]));
		return opTypes.toArray(new Integer[opTypes.size()]);
	}
}