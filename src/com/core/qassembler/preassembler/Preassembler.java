package com.core.qassembler.preassembler;

import java.util.List;
import java.util.regex.Pattern;

import com.core.qassembler.constants.QConstants;
import com.core.qassembler.file.MainProgramFile;
import com.core.qassembler.file.ProgramFileHandler;
import com.core.qassembler.memory.Memory;
import com.core.qassembler.memory.properties.Includer;
import com.core.qassembler.preassembler.expression.ExpressionResolver;
import com.core.qassembler.preassembler.replacements.ConstantReplacements;
import com.utils.regex.RegexHandler;

public class Preassembler implements QConstants{
	private Memory assemblerMemory;
	private ExpressionResolver expResolver;
	private ConstantReplacements constReplace;

	public Preassembler(String mainFilePath){
		assemblerMemory=new Memory(mainFilePath);
		expResolver=new ExpressionResolver();
		constReplace=new ConstantReplacements();
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
		return constReplace.replaceAll(mainFile, assemblerMemory);
	}
	
	private MainProgramFile handleOffsets(MainProgramFile mainFile){
		//DECLARE VARIABLES AND HANDLE STRINGS AND INTERVALS
		
		return mainFile;
	}
	
	private MainProgramFile handleIntervals(MainProgramFile mainFile){
		
		return mainFile;
	}
	
	private MainProgramFile handleLabels(MainProgramFile mainFile){
		
		return mainFile;
	}
	
	private MainProgramFile handleCommentsAndEmptyLines(MainProgramFile mainFile){
		// CLEAN SINGLE LINE COMMENTS, MULTILINE COMMENTS AND EMPTY LINES:
		String assembly=mainFile.getFile().getAssemblyCode();
		List<Object> commentsMatch=RegexHandler.match(PATT_COMMENT, assembly, Pattern.MULTILINE, null);
		for(int i=0;i<commentsMatch.size();i++) assembly=assembly.replaceAll((String)commentsMatch.get(i), "");
		mainFile.getFile().setAssemblyCode(assembly.replaceAll(PATT_EMPTYLINE, "")); //REMOVE EMPTY LINES:
		return mainFile;
	}
	
	public MainProgramFile preAssemble(MainProgramFile mainFile) throws Exception{
		mainFile=handleIncluding(mainFile);
		mainFile=handleCommentsAndEmptyLines(mainFile);
		mainFile=handleOffsets(mainFile);
		mainFile=handleConstantReplacements(mainFile);
		mainFile=handleExpressions(mainFile);
		mainFile=handleIntervals(mainFile);
		mainFile=handleLabels(mainFile);
		System.out.println(mainFile.getFile().getAssemblyCode());
		return mainFile;
	}
}