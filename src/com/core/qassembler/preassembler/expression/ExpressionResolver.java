package com.core.qassembler.preassembler.expression;

import org.nfunk.jep.JEP;
import java.util.List;
import com.core.qassembler.constants.QConstants;
import com.core.qassembler.file.MainProgramFile;
import com.core.qassembler.memory.Memory;
import com.utils.regex.RegexHandler;

public class ExpressionResolver implements QConstants{
	
	private JEP expressionParser;
	
	public ExpressionResolver(){
		expressionParser=new JEP();
	}
	
	public MainProgramFile resolve(MainProgramFile mainFile,Memory assemblerMemory){
		String assembly=mainFile.getFile().getAssemblyCode();
		List<Object> expressionMatcher=RegexHandler.match(PATT_EXPRESSION_V3, assembly, 0, null);
		for(int i=0;i<expressionMatcher.size();i++){
			String expression=(String)expressionMatcher.get(i);
			expressionParser.parseExpression(expression);
			expression=expression.replace("+","\\+").replace("*", "\\*").replace("/", "\\/").replace("(", "\\(").replace(")", "\\)");
			assembly=assembly.replaceFirst(expression, Integer.toString((int)expressionParser.getValue()));
		}
		mainFile.getFile().setAssemblyCode(assembly);
		return mainFile;
	}
}