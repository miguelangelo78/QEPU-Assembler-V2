package com.core.qassembler.translator.extractor;

import java.util.ArrayList;
import java.util.List;

import com.core.qassembler.constants.QConstants;

public class TypeExtractor implements QConstants{

	public TypeExtractor() { }
	
	public int extract(String operand){
		int type=-1;
		if(operand.charAt(0)=='[' && operand.charAt(1)=='{') return REGISTER_POINTER;
		
		switch(operand.charAt(0)){
			case '[': type=MEMORYCONTAINER; break;
			case 'M': case 'm': type=MEMORYCONTAINER; break;
			case 'R': case 'r': type=REGISTER; break;
			case '{': type=REGISTER; break;
			case '<': type=QUBIT; break;
			case '|': type=QUBIT_THETA; break;
			case '!': type=QUBIT_PHI; break;
			case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':case '-':type=CONSTANT; break;
		}
		return type;
	}
	
	public Integer[] extractAll(String[] ops){
		List<Integer> opTypes=new ArrayList<Integer>();
		for(int i=1;i<ops.length;i++) opTypes.add(extract(ops[i]));
		return opTypes.toArray(new Integer[opTypes.size()]);
	}
}