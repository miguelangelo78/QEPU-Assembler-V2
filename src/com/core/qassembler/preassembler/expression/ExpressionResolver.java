package com.core.qassembler.preassembler.expression;

import org.nfunk.jep.JEP;

import java.util.List;
import java.util.regex.Pattern;

import com.core.qassembler.constants.Global_Constants;
import com.core.qassembler.file.MainProgramFile;
import com.core.qassembler.memory.Memory;
import com.utils.regex.RegexHandler;

public class ExpressionResolver implements Global_Constants{
	
	private JEP expressionParser;
	
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
			// ADD SLASHES SO THE REPLACEFIRST FUNCTION DOESN'T CRASH:
			expression_wholematch=expression_wholematch.replace("+","\\+").replace("*", "\\*").replace("/", "\\/").replace("(", "\\(").replace(")", "\\)");
			assembly=assembly.replaceFirst(expression_wholematch, Long.toString((long)expressionParser.getValue()));
		}
		mainFile.getFile().setAssemblyCode(assembly);
		return mainFile;
	}
}