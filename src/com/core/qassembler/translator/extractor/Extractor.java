package com.core.qassembler.translator.extractor;

public class Extractor {

	private TypeExtractor typeExtractor;
	private OperandExtractor operandExtractor;
	
	public Extractor() {
		typeExtractor=new TypeExtractor();
		operandExtractor=new OperandExtractor();
	}
	
	public TypeExtractor getTypeExtractor(){
		return typeExtractor;
	}
	
	public OperandExtractor getOperandExtractor(){
		return operandExtractor;
	}
}