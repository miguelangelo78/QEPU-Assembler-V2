package com.core.qassembler.preassembler.expression;

import org.nfunk.jep.JEP;

import java.util.List;
import java.util.regex.Pattern;

import com.core.qassembler.constants.QConstants;
import com.core.qassembler.file.MainProgramFile;
import com.core.qassembler.memory.Memory;
import com.utils.regex.RegexHandler;

public class ExpressionResolver implements QConstants{
	
	private JEP expressionParser;
	
	public ExpressionResolver(){
		expressionParser=new JEP();
		expressionParser.addStandardFunctions();
		expressionParser.addStandardConstants();
		expressionParser.addComplex();
	}
	
	public MainProgramFile resolve(MainProgramFile mainFile,Memory assemblerMemory){
		String assembly=mainFile.getFile().getAssemblyCode();
		List<Object> expressionMatcher=RegexHandler.match(PATT_EXPRESSION_V4, assembly, Pattern.MULTILINE, null);
		for(int i=0;i<expressionMatcher.size();i++){
			String expression=((String)expressionMatcher.get(i)).replaceAll("[\\],{}<>|%]","").trim();
			expressionParser.parseExpression(expression);
			expression=expression.replace("+","\\+").replace("*", "\\*").replace("/", "\\/").replace("(", "\\(").replace(")", "\\)");
			assembly=assembly.replaceFirst(expression, Integer.toString((int)expressionParser.getValue()));
		}
		mainFile.getFile().setAssemblyCode(assembly);
		return mainFile;
	}
}