package com.core.qassembler.preassembler.expression;

import org.nfunk.jep.JEP;

import java.util.List;
import java.util.regex.Pattern;

import com.core.qassembler.constants.Global_Constants;
import com.core.qassembler.file.MainProgramFile;
import com.core.qassembler.memory.Memory;
import com.utils.regex.RegexHandler;

public class ExpressionResolver implements Global_Constants{
	
	private static JEP expressionParser;
	
	public ExpressionResolver(){
		expressionParser=new JEP();
		expressionParser.addStandardFunctions();
		expressionParser.addStandardConstants();
		expressionParser.addComplex();
	}
	
	public MainProgramFile resolve(MainProgramFile mainFile,Memory assemblerMemory){
		String assembly=mainFile.getFile().getAssemblyCode();
		List<Object> expressionMatcher=RegexHandler.match(PATT_EXPRESSION_V6, assembly, Pattern.MULTILINE, new int[]{0,1});
		for(int i=0;i<expressionMatcher.size();i++){
			String expression_wholematch=((String[])expressionMatcher.get(i))[0];
			String expression=((String[])expressionMatcher.get(i))[1];
			expressionParser.parseExpression(expression);
			assembly=assembly.replaceFirst(RegexHandler.sanitizeString(expression_wholematch), 
										   Long.toString((long)expressionParser.getValue()));
		}
		mainFile.getFile().setAssemblyCode(assembly);
		return mainFile;
	}
	
	private static boolean is_expression(String expression){
		return RegexHandler.match(PATT_EXPRESSION_V6, expression,0,null).size()>0;
	}
	
	public static Object resolve(String expression){
		if(!is_expression(expression)) return expression;
		expressionParser.parseExpression(expression.replace("_",""));
		return (long)expressionParser.getValue();
	}
}