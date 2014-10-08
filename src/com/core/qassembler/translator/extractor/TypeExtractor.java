package com.core.qassembler.translator.extractor;

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
			default: type=CONSTANT;
		}
		return type;
	}
}