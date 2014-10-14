package com.core.qassembler.preassembler;

import java.util.List;
import java.util.regex.Pattern;

import com.core.qassembler.constants.QConstants;
import com.core.qassembler.file.MainProgramFile;
import com.core.qassembler.memory.Memory;
import com.core.qassembler.memory.properties.Includer;
import com.core.qassembler.preassembler.expression.ExpressionResolver;
import com.core.qassembler.preassembler.replacements.ConstantReplacements;
import com.core.qassembler.preassembler.replacements.Interval;
import com.core.qassembler.preassembler.replacements.StringInterval;
import com.utils.regex.RegexHandler;

public class Preassembler implements QConstants{
	private Memory assemblerMemory;
	private ExpressionResolver expResolver;
	private Interval intervals;
	private StringInterval stringIntervals;

	public Preassembler(String mainFilePath){
		assemblerMemory=new Memory(mainFilePath);
		expResolver=new ExpressionResolver();
		intervals=new Interval();
		stringIntervals=new StringInterval();
		for(char c='A';c<='Z';c++) REGISTER_LAYOUT.put(c+"X", "{"+(c-'A')+"}"); // INICIALIZE REGISTER LAYOUT
	}
	
	public Includer getIncluder() {
		return assemblerMemory.getIncluder();
	}
	
	public Memory getAssemblerMemory() {
		return assemblerMemory;
	}
	
	private MainProgramFile handleIncluding(MainProgramFile mainFile) throws Exception{
		return assemblerMemory.addLibraries(mainFile);
	}
	
	private MainProgramFile handleExpressions(MainProgramFile mainFile){
		return expResolver.resolve(mainFile, assemblerMemory);
	}
	
	private MainProgramFile handleConstantReplacements(MainProgramFile mainFile){
		return ConstantReplacements.replaceAll(mainFile, assemblerMemory);
	}
	
	private MainProgramFile handleLabels(MainProgramFile mainFile) throws Exception{
		return assemblerMemory.handleLabels(mainFile);
	}
	
	private MainProgramFile handleCommentsAndEmptyLines(MainProgramFile mainFile){
		// CLEAN SINGLE LINE COMMENTS, MULTILINE COMMENTS AND EMPTY LINES:
		String assembly=mainFile.getFile().getAssemblyCode();
		List<Object> commentsMatch=RegexHandler.match(PATT_COMMENT, assembly, Pattern.MULTILINE, null);
		for(int i=0;i<commentsMatch.size();i++) assembly=assembly.replace((String)commentsMatch.get(i), "");
		mainFile.getFile().setAssemblyCode(assembly.replaceAll(PATT_EMPTYLINE, "")); //REMOVE EMPTY LINES:
		return mainFile;
	}
	
	private MainProgramFile handleOffsets(MainProgramFile mainFile) throws Exception{
		assemblerMemory.handleOffsets(mainFile);
		mainFile=assemblerMemory.handleVariables(mainFile);
		mainFile=stringIntervals.replaceAll(mainFile);
		mainFile=intervals.handleIntervals(mainFile);
		mainFile=handleLabels(mainFile);
		mainFile=handleConstantReplacements(mainFile);
		return mainFile;
	}
	
	public MainProgramFile preAssemble(MainProgramFile mainFile) throws Exception{
		mainFile=handleCommentsAndEmptyLines(mainFile);
		mainFile=handleIncluding(mainFile);
		mainFile=handleCommentsAndEmptyLines(mainFile);
		mainFile=handleOffsets(mainFile);
		mainFile=handleCommentsAndEmptyLines(mainFile);
		mainFile=handleExpressions(mainFile);
		return mainFile;
	}
}